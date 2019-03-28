package edu.hunau.base.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.MenuDetailsScanRecords;
import edu.hunau.base.model.User;
import edu.hunau.base.model.UserCollectionOrShare;
import edu.hunau.base.service.MenuDetailsScanRecordsService;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.service.UserCollectionOrShareService;
import edu.hunau.base.support.JsResult;
import edu.hunau.base.support.PageInfo;

@Controller
@RequestMapping(value = "/healthyRecipes")
public class HealthyRecipesAction {

	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService menuDetailsService;
	
	@Autowired
	@Qualifier("userCollectionOrShareService")
	private UserCollectionOrShareService userCollectionOrShareService;
	
	@Autowired
	@Qualifier("menuDetailsScanRecordsService")
	private MenuDetailsScanRecordsService menuDetailsScanRecordsService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = index(request, session, 1);
		return mav;
	}

	@RequestMapping(value = "/index/{pageNum}")
	public ModelAndView index(HttpServletRequest request, HttpSession session, @PathVariable int pageNum) {
		ModelAndView mav = new ModelAndView("healthyRecipes");
		String type = request.getParameter("type");
		if (type == "") {
			type = null;
		}
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser != null) {
			mav.addObject("sessionUser", sessionUser);
		}
		// 获得页面显示信息
		int pageSize = 12;
		PageInfo<MenuDetails> page = menuDetailsService.getEntityPage(pageNum, pageSize, type);
		mav.addObject("page", page);
		mav.addObject("type", type);
		return mav;
	}

	@RequestMapping(value = "/details/{id}")
	public ModelAndView details(HttpServletRequest request, HttpSession session, @PathVariable String id) {
		ModelAndView mav = new ModelAndView("recipesDetails");
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser != null) {
			mav.addObject("sessionUser", sessionUser);
		}
		MenuDetails entity = menuDetailsService.getEntityById(id);
		if (entity != null) {
			mav.addObject("entity", entity);
		}
		//添加一条浏览记录，并增加一次访问量
		MenuDetailsScanRecords records=new MenuDetailsScanRecords();
		records.setCpId(entity.getId());
		records.setCpname(entity.getCpname());
		records.setCreateTime(new Date());
		if(sessionUser!=null){
			records.setUserId(sessionUser.getId());
			records.setUsername(sessionUser.getUsername());
		}else{
			records.setUserId(null);
			records.setUsername(null);
		}
		menuDetailsScanRecordsService.addRecord(records);
		
		//更新点击量
		menuDetailsService.updateRecipesClickcountById(id);
		
		//获得菜谱的收藏量
		int collectionCount = userCollectionOrShareService.getCollectionCountByCpId(id);
		mav.addObject("collectionCount", collectionCount);
		return mav;
	}

	@RequestMapping(value = "/collection")
	@ResponseBody
	public JsResult recipesCollection(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String cpId) throws Exception {
		User user = (User) session.getAttribute("user");
		JsResult result=new JsResult();
		if (user == null) {
			result.setItem("请先登录再操作！");
			return result;
		}
		try {
			MenuDetails menuDetails = menuDetailsService.getEntityById(cpId);
			UserCollectionOrShare entity=new UserCollectionOrShare();
			entity.setCpId(menuDetails.getId());
			entity.setCpname(menuDetails.getCpname());
			entity.setUserId(user.getId());
			entity.setUsername(user.getUsername());
			entity.setType(1);
			entity.setCreateTime(new Date());
			entity.setClickCount(menuDetails.getClickCount());
			entity.setCpImg_url(menuDetails.getImg_src());
			entity.setCpImg_name(menuDetails.getImg_name());
			userCollectionOrShareService.addRecords(entity);
			result.setItem("菜谱收藏成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setItem("系统繁忙，请稍后再试！");
		}
		return result;
	}

}
