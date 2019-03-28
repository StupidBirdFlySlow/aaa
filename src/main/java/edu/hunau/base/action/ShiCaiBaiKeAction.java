package edu.hunau.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.model.User;
import edu.hunau.base.service.ShiCaiBaiKeService;
import edu.hunau.base.support.PageInfo;

@Controller
@RequestMapping(value = "/shicaibaike")
public class ShiCaiBaiKeAction {

	@Autowired
	@Qualifier("shiCaiBaiKeService")
	private ShiCaiBaiKeService shiCaiBaiKeService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpSession session) {
		return index(request, session, 1);
	}

	/**
	 * 食材百科首页处理
	 * @param request
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "/index/{pageNum}")
	public ModelAndView index(HttpServletRequest request, HttpSession session, @PathVariable Integer pageNum) {
		ModelAndView mav = new ModelAndView("shicaibaike");
		String type=request.getParameter("type");
		if(type==""){
			type=null;
		}
		mav.addObject("type", type);
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser != null) {
			mav.addObject("sessionUser", sessionUser);
		}
		// 获取页面显示信息
		int pageSize = 8;
		PageInfo<ShiCaiBaiKe> pageInfo = shiCaiBaiKeService.getPageInfo(pageSize, pageNum, type);
		mav.addObject("page", pageInfo);
		return mav;
	}

	/**
	 * 从食材百科主页点击跳转到详细页
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/details/{id}")
	public ModelAndView details(HttpServletRequest request, HttpSession session, @PathVariable String id) {
		return details(request,session,id,1);
	}
	
	/**
	 * 食材百科详细页面
	 * @param request
	 * @param session
	 * @param id
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "/details/{id}/{pageNum}")
	public ModelAndView details(HttpServletRequest request, HttpSession session, @PathVariable String id, @PathVariable int pageNum) {
		ModelAndView mav = new ModelAndView("shicai");
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser != null) {
			mav.addObject("sessionUser", sessionUser);
		}
		ShiCaiBaiKe entity = shiCaiBaiKeService.getEntity(id);
		mav.addObject("entity", entity);
		List<MenuDetails> menuDetailList = shiCaiBaiKeService.getMenuDetailsByReferMaterial(entity.getName(), 9, pageNum);
		mav.addObject("menuList", menuDetailList);
		return mav;
	}

}
