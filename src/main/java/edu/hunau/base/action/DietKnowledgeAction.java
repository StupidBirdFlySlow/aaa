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

import edu.hunau.base.model.DietKnowledge;
import edu.hunau.base.model.User;
import edu.hunau.base.service.DietKnowledgeService;
import edu.hunau.base.support.PageInfo;

@Controller
@RequestMapping(value="/knowledge")
public class DietKnowledgeAction {

	@Autowired
	@Qualifier("dietKnowledgeService")
	private DietKnowledgeService dietKnowledgeService;
	
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,HttpSession session){
		return index(request,session,1);
	}
	
	/**
	 * 饮食知识首页处理
	 * @param request
	 * @param session
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value="/index/{pageNum}")
	public ModelAndView index(HttpServletRequest request,HttpSession session,@PathVariable int pageNum){
		ModelAndView mav=new ModelAndView("healthyKnowledge");
		String type=request.getParameter("type");
		if(type==""){
			type=null;
		}
		User sessionUser=(User) session.getAttribute("user");
		if(sessionUser!=null){
			mav.addObject("sessionUser", sessionUser);
		}
		//获取显示数据
		int pageSize=12;
		PageInfo<DietKnowledge> pageInfo = dietKnowledgeService.getPageInfo(pageSize, pageNum, type);
		mav.addObject("page", pageInfo);
		return mav;
	}
	
	/**
	 * 饮食知识详细查看页面处理
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/details/{id}")
	public ModelAndView details(HttpServletRequest request,HttpSession session,@PathVariable String id){
		ModelAndView mav=new ModelAndView("knowledgeDetails");
		User sessionUser=(User) session.getAttribute("user");
		if(sessionUser!=null){
			mav.addObject("sessionUser", sessionUser);
		}
		DietKnowledge entity = dietKnowledgeService.getEntityById(id);
		if(entity!=null){
			mav.addObject("entity", entity);
		}
		return mav;
		
	}
}
