package dsy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.admin.UserService;
import net.sf.json.JSONObject;

@Controller
public class AdminUserController {

	@Autowired
	private UserService uService;
	
	@RequestMapping("adminUser.html")
	public String AdminUser(){
		return "jsp/admin/user";
	}
	
	// 获取用户列表
	@RequestMapping("adminUser/getAdminUserList.html")
	@ResponseBody
	public JSONObject getAdminUserList(HttpServletRequest request) {
			JSONObject data = null;
			try {
				data = uService.getUserList(request);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
}
