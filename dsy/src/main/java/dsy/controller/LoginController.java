package dsy.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.log.SystemLog;
import dsy.module.SecUser;
import dsy.service.LoginService;
import dsy.service.MenuService;
import net.sf.json.JSONObject;
import util.Response2JSON;

@Controller
public class LoginController {

	@Autowired
	private MenuService mService;
	
	@Autowired
	private LoginService lService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("login.html")
	public String login(){
		return "login";
	}
	
	@SuppressWarnings("unused")
	@SystemLog(log = "用户登录")
	@RequestMapping("logon.html")
	public String logon(SecUser user,HttpSession session, ModelMap model,Locale locale){
		try {
			SecUser secUser=this.lService.LoginSystem(user);
			String html="";
			String noUser = messageSource.getMessage("noUser", null, locale);
			String isLock = messageSource.getMessage("isLock", null, locale);
			String prompt = messageSource.getMessage("prompt", null, locale);
			System.out.println(secUser.getName());
			if(secUser == null){
					html = "<div class='alert alert-danger'><a href='#' class='close' data-dismiss='alert'>&times;</a> <strong>"
							+ prompt + ":<>" + noUser + "</div>";
					model.addAttribute("msg", html);
					return "login";
			}else if(!"1".equals(secUser.getStatus())){
				html = "<div class='alert alert-warning'><a href='#' class='close' data-dismiss='alert'>&times;</a> <strong>"
						+ prompt + ":<>" + isLock + "</div>";
				model.addAttribute("msg", html);
				return "login";
			}else{
				session.setAttribute("user", secUser);
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	

	@RequestMapping(value = "menu.html")
	@ResponseBody
	public JSONObject getMenu(HttpServletRequest request) throws Exception {
		List<JSONObject> returnData=null;
		HttpSession session = request.getSession(false);
		try {
				SecUser user = (SecUser) session.getAttribute("user");
				Integer userid = Integer.parseInt(user.getId());
				returnData = mService.getMenuByUser(userid);
				return Response2JSON.toJSON(returnData, "000");
		} catch (Exception e) {
			e.printStackTrace();
			return Response2JSON.toJSON("", "001");
		}
	}
	@SystemLog(log = "用户下线")
	@RequestMapping("loginOut.html")
	public String LoginOut(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		// 移除用户
		if (session != null && session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return "login";
	}
	
}
