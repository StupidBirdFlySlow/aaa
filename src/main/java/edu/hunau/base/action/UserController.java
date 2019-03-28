package edu.hunau.base.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hunau.base.model.User;
import edu.hunau.base.model.UserCollectionOrShare;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.service.ProvinceAndCityService;
import edu.hunau.base.service.UserCollectionOrShareService;
import edu.hunau.base.service.UserService;
import edu.hunau.base.support.JsResult;
import edu.hunau.base.utils.ImageUtils2;
import edu.hunau.base.utils.JsonUtils;
import edu.hunau.base.utils.MD5Utils;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("provinceAndCityService")
	private ProvinceAndCityService provinceAndCityService;

	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService menuDetailsService;

	@Autowired
	@Qualifier("userCollectionOrShareService")
	private UserCollectionOrShareService userCollectionOrShareService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		String yzm = ImageUtils2.getYzm();
		mav.addObject("code", yzm);
		return mav;
	}

	@RequestMapping(value = "/pwd_login")
	@ResponseBody
	public JsResult pwdLogin(HttpServletRequest request, String username, String pwd, HttpSession session)
			throws Exception {
		User user = new User();
		user.setUsername(username);
		user.setPwd(MD5Utils.md5(pwd));
		User result = userService.getUser(user);
		if (result != null) {
			session.setAttribute("user", result);
		}
		JsResult item = new JsResult();
		if (result != null) {
			item.setItem(true);
		} else {
			item.setItem("�û��������벻��ȷ��");
		}
		return item;
	}

	@RequestMapping(value = "phone_login")
	public ModelAndView phoneLogin(HttpServletRequest request, String phone, String dtm, HttpSession session) {
		System.out.println("phone=" + phone + " dtm=" + dtm);
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "register")
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping(value = "phone_register")
	@ResponseBody
	public String phoneRegister(HttpServletRequest request, String phone, String pwd, HttpSession session)
			throws Exception {
		User user = new User();
		user.setPhone(phone);
		user.setUsername(phone);
		String md5 = MD5Utils.md5(pwd);
		user.setPwd(md5);// ��������м���
		setDefault(user);
		userService.addUser(user);
		JsResult result = new JsResult();
		result.setItem("ע��ɹ�");
		String string = JsonUtils.obj2json(result);
		return string;
	}

	@RequestMapping(value = "mail_register")
	@ResponseBody
	public String mailRegister(HttpServletRequest request, String email, String pwd, HttpSession session)
			throws Exception {
		User user = new User();
		user.setEmail(email);
		user.setUsername(email);
		String md5 = MD5Utils.md5(pwd);
		user.setPwd(md5);// ��������м���
		setDefault(user);
		userService.addUser(user);
		JsResult result = new JsResult();
		result.setItem("ע��ɹ�");
		String string = JsonUtils.obj2json(result);
		return string;
	}

	private void setDefault(User user) {
		user.setJifen(0);
		user.setDegree(1);
		user.setGender("��");
		user.setCreateTime(new Date());
		user.setProvince("����");
		user.setCity("��ɳ");
		user.setHead_img("http://localhost/images/users/");
		user.setHead_img_name("myDesignLogo.jpg");
	}

	@RequestMapping(value = "/personalCenter/{userId}")
	public ModelAndView personalCenter(HttpServletRequest request, HttpSession session, @PathVariable String userId) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("user") != null) {
			User user = userService.queryUserById(userId);
			mav.addObject("user", user);
			mav.setViewName("personalCenter");
			// ���ʡ��
			List<String> provinceList = provinceAndCityService.getProvinceList();
			mav.addObject("provinceList", provinceList);
			if (user.getProvince() != null) {
				List<String> cityList = provinceAndCityService.getCityListByProvince(user.getProvince());
				mav.addObject("countryList", cityList);
			}
			// ����û��ղز��� ��ʱ���ܲ���
			int type = 1;// ��ʾ�û��ղ�
			List<UserCollectionOrShare> collectionList = userCollectionOrShareService
					.getUserCollectionOrShareList(userId, type);
			mav.addObject("collectionList", collectionList);
			//// ����û��������

		} else {
			mav.setViewName("redirect:/login.html");
		}
		return mav;
	}

	@RequestMapping(value = "/user/update")
	@ResponseBody
	public JsResult updateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user)
			throws IOException, ServletException {
		JsResult result = new JsResult();
		// 1�����Ү�ǰ�Ñ�,�޸��û���Ϣ
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser != null) {
			try {
				User entity = userService.queryUserById(sessionUser.getId());
				entity.setUsername(user.getUsername());
				entity.setEmail(user.getEmail());
				entity.setPhone(user.getPhone());
				entity.setGender(user.getGender());
				entity.setProvince(user.getProvince());
				entity.setCity(user.getCity());
				entity.setModifyTime(new Date());
				userService.updateUser(entity);
				result.setItem("��Ϣ���³ɹ�...");
			} catch (Exception e) {
				result.setItem("����ʧ��...");
			}
		} else {
			request.getRequestDispatcher("http://localhost/design/login.html").forward(request, response);
		}
		return result;
	}

	@RequestMapping(value = "/user/loadCity")
	@ResponseBody
	public JsResult loadCitys(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String province) throws Exception {
		JsResult result = new JsResult();
		if (session.getAttribute("user") == null) {
			request.getRequestDispatcher("http://localhost/design/login.html").forward(request, response);
		}
		List<String> cityList = provinceAndCityService.getCityListByProvince(province);
		result.setItem(cityList);
		return result;
	}

	@RequestMapping(value = "/user/changePwd")
	@ResponseBody
	public JsResult changePwd(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			String oldpwd, String newpwd, String snewpwd) throws Exception {
		if (session.getAttribute("user") == null) {
			request.getRequestDispatcher("http://localhost/design/login.html").forward(request, response);
		}
		JsResult result = new JsResult();
		User user = (User) session.getAttribute("user");
		User entity = userService.queryUserById(user.getId());
		String pwd = user.getPwd();
		if (!pwd.equals(MD5Utils.md5(oldpwd))) {
			result.setItem("ԭʼ���벻��ȷ��");
		} else {
			if (!newpwd.equals(snewpwd)) {
				result.setItem("�������������벻һ�£�");
			} else {
				entity.setPwd(MD5Utils.md5(newpwd));
				userService.updateUser(entity);
				session.setAttribute("user", entity);
				result.setItem("�����޸ĳɹ���");
			}
		}
		return result;
	}

	@RequestMapping(value = "/user/cancelCollection")
	public String cancelCollection(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		// idΪ�ղر��
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		if (session.getAttribute("user") == null) {
			request.getRequestDispatcher("http://localhost/design/login.html").forward(request, response);
		}
		ModelAndView mav = new ModelAndView();
		try {
			// 1��ɾ���ղر��е�����
			userCollectionOrShareService.deleteById(id);
			// 2�������ղر��е�����
			return "redirect:http://localhost/design/personalCenter/" + userId + ".html";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/changeHeadImg")
	public ModelAndView changeHeadImg(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("file") MultipartFile file) throws Exception {
		User sessionUser = (User) session.getAttribute("user");
		ModelAndView mav=new ModelAndView();
		if (sessionUser == null) {
			mav.setViewName("login");
		}
		//�ļ�����·��
		String rootPath="D:/��ҵ����ļ���/ͼƬ�ز�/users/";
		if(!file.isEmpty()){
			//����uuid��Ϊ�ļ�����  
            String uuid = UUID.randomUUID().toString().replaceAll("-","");  
            //����ļ�����
            String contentType = file.getContentType();
            //����ļ���׺
            String type = contentType.substring(contentType.indexOf("/")+1);
            file.transferTo(new File(rootPath+uuid+"."+type));
            User user = userService.queryUserById(sessionUser.getId());
            user.setHead_img("http://localhost/images/users/");
            user.setHead_img_name(uuid+"."+type);
            user.setModifyTime(new Date());
            userService.updateUser(user);
		}
		return personalCenter(request, session, sessionUser.getId());
	}

	@RequestMapping(value="/tuichu")
	public ModelAndView tuichu(HttpServletRequest request,HttpSession session){
		session.removeAttribute("user");
		ModelAndView mav=new ModelAndView("login");
		return mav;
	}

}
