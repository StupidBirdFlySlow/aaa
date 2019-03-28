package edu.hunau.base.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.hunau.base.model.DietKnowledge;
import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.model.User;
import edu.hunau.base.service.DietKnowledgeService;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.service.ShiCaiBaiKeService;
import edu.hunau.base.support.JsResult;

@Controller
public class HomeController {

	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService menuDetailsService;

	@Autowired
	@Qualifier("dietKnowledgeService")
	private DietKnowledgeService dietKnowledgeService;

	@Autowired
	@Qualifier("shiCaiBaiKeService")
	private ShiCaiBaiKeService shiCaiBaiKeService;

	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("home");
		User sessionUser = (User) session.getAttribute("user");
		mav.addObject("sessionUser", sessionUser);
		Random r=new Random();
		// Part I
		List<MenuDetails> zaocan = menuDetailsService.getEntityList(r.nextInt(20)+1, 4, "zaocan");
		mav.addObject("zaocan", zaocan);
		List<MenuDetails> zhongcan = menuDetailsService.getEntityList(r.nextInt(20)+1, 4, "zhongcan");
		mav.addObject("zhongcan", zhongcan);
		List<MenuDetails> xiawucha = menuDetailsService.getEntityList(r.nextInt(20)+1, 4, "tpdx");
		mav.addObject("xiawucha", xiawucha);
		List<MenuDetails> wancan = menuDetailsService.getEntityList(r.nextInt(20)+1, 4, "recai");
		mav.addObject("wancan", wancan);
		List<MenuDetails> xiaoye = menuDetailsService.getEntityList(r.nextInt(20)+1, 4, "xiaoye");
		mav.addObject("xiaoye", xiaoye);

		// Part II
		List<MenuDetails> mostNewRecipes = menuDetailsService.getMostNewRecipes(4, 1);
		mav.addObject("mostNewRecipes", mostNewRecipes);
		List<MenuDetails> thisWeek = menuDetailsService.getMostHotRecipesOfThisWeek(new Date(), 4, 4);
		mav.addObject("thisWeek", thisWeek);
		List<MenuDetails> thisMonth = menuDetailsService.getMostHotRecipesOfThisMonth(new Date(), 4, 5);
		mav.addObject("thisMonth", thisMonth);

		// Part III
		List<ShiCaiBaiKe> shucai = shiCaiBaiKeService.getEntityList(8, r.nextInt(11)+1, "shucai");
		mav.addObject("shucai", shucai);
		List<ShiCaiBaiKe> shuiguo = shiCaiBaiKeService.getEntityList(8, r.nextInt(11)+1, "shuiguo");
		mav.addObject("shuiguo", shuiguo);
		List<ShiCaiBaiKe> gulei = shiCaiBaiKeService.getEntityList(8, r.nextInt(11)+1, "gulei");
		mav.addObject("gulei", gulei);
		List<ShiCaiBaiKe> yxbx = shiCaiBaiKeService.getEntityList(8, r.nextInt(11)+1, "yxbx");
		mav.addObject("yxbx", yxbx);

		// Part IV
		List<DietKnowledge> knowledge = dietKnowledgeService.getEntityList(4, r.nextInt(11)+1, null);
		mav.addObject("knowledge", knowledge);

		return mav;
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public JsResult search(HttpServletRequest request, HttpServletResponse response, String keywords) {
		JsResult result = new JsResult();
		List<String> idList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		idList = menuDetailsService.getRecipesIdByKeywords(keywords);
		nameList = menuDetailsService.getRecipesNameByKeywords(keywords);
		if (nameList.size() == 0) {
			Random r = new Random();
			int pageNum = r.nextInt(10)+1;
			List<MenuDetails> entityList = menuDetailsService.getEntityList(pageNum, 10, null);
			for (MenuDetails entity : entityList) {
				idList.add(entity.getId());
				nameList.add(entity.getCpname());
				result.put("idList", idList);
				result.put("nameList", nameList);
			}
			return result;
		}
		result.put("idList", idList);
		result.put("nameList", nameList);
		return result;
	}
}
