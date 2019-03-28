package edu.hunau.base.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hunau.base.service.UserService;
import edu.hunau.base.support.JsResult;
import edu.hunau.base.utils.ImageUtils2;
import edu.hunau.base.utils.JsonUtils;

@Controller
public class SupportController {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping(value="/checkEmail")
	@ResponseBody
	public String checkEmail(String email) throws Exception{
		boolean bool = userService.checkEmail(email);
		String result = JsonUtils.obj2json(bool);
		return result;
	}
	
	@RequestMapping(value="/checkPhone")
	@ResponseBody
	public String checkPhone(String phone) throws Exception{
		System.out.println(phone);
		boolean bool = userService.checkPhone(phone);
		String result = JsonUtils.obj2json(bool);
		return result;
	}
	
	@RequestMapping(value="/changeYzm")
	@ResponseBody
	public JsResult changeYzm() throws Exception{
		String yzm = ImageUtils2.getYzm();
		System.out.println(yzm);
		JsResult result=new JsResult();
		result.setItem(yzm);
		return result;
	}
}
