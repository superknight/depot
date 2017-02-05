package dsy.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dsy.module.SecUser;
import dsy.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService lService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("login.html")
	public String login(){
		return "login";
	}
	
	@RequestMapping("logon.html")
	public String logon(SecUser user,HttpSession session, ModelMap model,Locale locale){
		try {
			System.out.println(user.getUsername());
			SecUser secUser=this.lService.LoginSystem(user);
			String html="";
			String noUser = messageSource.getMessage("noUser", null, locale);
			String isLock = messageSource.getMessage("isLock", null, locale);
			String prompt = messageSource.getMessage("prompt", null, locale);

			if(secUser ==null){
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
			// TODO: handle exception
		}
		return "login";
	}
	
}
