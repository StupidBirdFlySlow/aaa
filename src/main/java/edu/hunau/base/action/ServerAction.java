package edu.hunau.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.User;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.service.UserService;
import edu.hunau.base.support.JsResult;
import edu.hunau.base.utils.MD5Utils;

@Controller
@RequestMapping(value="/server")
public class ServerAction {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService menuDetailsService;
	
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,HttpSession session){
		ModelAndView mav=new ModelAndView();
		User sessionUser =(User) session.getAttribute("user");
		if(sessionUser==null){
			mav.setViewName("login");
			return mav;
		}
		mav.addObject("user", sessionUser);
		mav.setViewName("userBaseInfo");
		return mav;
	}
	
	/**
	 * 加载用户信息
	 * @param request
	 * @param session
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userinfo")
	@ResponseBody
	public JsResult userInfo(HttpServletRequest request,HttpSession session,int page, int limit) throws Exception{
		System.out.println(page+"   :   "+limit);
		List<User> entityList = userService.getEntityList(page, limit);
		int count = userService.getTotalCountOfUser();
		JsResult result=new JsResult();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		result.put("data", entityList);
		System.out.println(result.toString());
		return result;
	}
	
	/**
	 * 用户基本信息页面，即首页
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/userBaseInfo")
	public ModelAndView userBaseInfo(HttpServletRequest request,HttpSession session){
		return index(request, session);
	}
	
	/**
	 * 跳转到修改用户信息界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/userInfoChange")
	public ModelAndView userInfoChange(HttpServletRequest request,HttpSession session){
		ModelAndView mav=new ModelAndView("userInfoChange");
		User sessionUser =(User) session.getAttribute("user");
		if(sessionUser==null){
			mav.setViewName("login");
			return mav;
		}
		mav.addObject("user", sessionUser);
		return mav;
	}
	
	/**
	 * 跳转菜谱基本信息页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/recipesBaseInfo")
	public ModelAndView recipesBaseInfo(HttpServletRequest request,HttpSession session){
		ModelAndView mav=new ModelAndView("recipesBaseInfo");
		User sessionUser =(User) session.getAttribute("user");
		if(sessionUser==null){
			mav.setViewName("login");
			return mav;
		}
		mav.addObject("user", sessionUser);
		return mav;
	}
	

	/**
	 * 加载菜谱基本信息的数据
	 * @param request
	 * @param session
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/recipesInfo")
	@ResponseBody
	public JsResult recipesInfo(HttpServletRequest request,HttpSession session,int page, int limit) throws Exception{
		System.out.println(page+"   :   "+limit);
		List<MenuDetails> entityList = menuDetailsService.getEntityList(page, limit, null);
		int count = menuDetailsService.getTotalRecords(null);
		JsResult result=new JsResult();
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", count);
		result.put("data", entityList);
		System.out.println(result.toString());
		return result;
	}
	
	/**
	 * 弹出编辑页面
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/userInfoEdit")
	public ModelAndView userInfoEdit(HttpServletRequest request,HttpSession session){
		ModelAndView mav=new ModelAndView("userInfoEdit");
		String action = request.getParameter("action");
		String userId=request.getParameter("userId");
		if("view".equals(action)){
			mav.addObject("action", "view");
		}else{
			mav.addObject("action", "edit");
			
		}
		User user = userService.queryUserById(userId);
		mav.addObject("editUser", user);
		return mav;
	}
	
	@RequestMapping(value="/edit")
	@ResponseBody
	public JsResult edit(HttpServletRequest request,HttpSession session,User user){
		JsResult result=new JsResult();
		String userId=request.getParameter("userId");
		try {
			User entity = userService.queryUserById(userId);
			entity.setUsername(user.getUsername());
			entity.setPhone(user.getPhone());
			entity.setEmail(user.getEmail());
			entity.setPwd(MD5Utils.md5(user.getPwd()));
			entity.setCity(user.getCity());
			entity.setProvince(user.getProvince());
			userService.updateUser(entity);
			result.setItem("更新成功！");
		} catch (Exception e) {
			result.setItem("系统繁忙,请稍后再试！");
		}
		return result;
	}
	
	@RequestMapping(value="/recipesInfoChange")
	public ModelAndView recipesInfoChange(HttpServletRequest request,HttpSession session){
		ModelAndView mav= new ModelAndView("recipesInfoChange");
		User sessionUser =(User) session.getAttribute("user");
		if(sessionUser==null){
			mav.setViewName("login");
			return mav;
		}
		mav.addObject("user", sessionUser);
		return mav;
	}
	
	@RequestMapping(value="/recipesEdit")
	public ModelAndView recipesEdit(HttpServletRequest request,HttpSession session){
		ModelAndView mav=new ModelAndView("recipesEdit");
		String action = request.getParameter("action");
		String id=request.getParameter("id");
		if("view".equals(action)){
			mav.addObject("action", "view");
		}else{
			mav.addObject("action", "edit");
		}
		MenuDetails entity = menuDetailsService.getEntityById(id);
		mav.addObject("menuDetails", entity);
		return mav;
	}
	
	@RequestMapping(value="/editRecipes")
	@ResponseBody
	public JsResult editRecipes(HttpServletRequest request,HttpSession session,MenuDetails menuDetails){
		JsResult result=new JsResult();
		try {
			String id=request.getParameter("id");
			MenuDetails entity = menuDetailsService.getEntityById(id);
			entity.setCpname(menuDetails.getCpname());
			entity.setGy(menuDetails.getGy());
			entity.setNd(menuDetails.getNd());
			entity.setYcrs(menuDetails.getYcrs());
			entity.setPrsj(menuDetails.getPrsj());
			entity.setZbsj(menuDetails.getZbsj());
			entity.setKw(menuDetails.getKw());
			menuDetailsService.updateMenuDetails(entity);
			result.setItem("更新成功！");
		} catch (Exception e) {
			result.setItem("系统繁忙,请稍后再试！");
		}
		return result;
	}
}
