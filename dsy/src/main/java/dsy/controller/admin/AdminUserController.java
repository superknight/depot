package dsy.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.module.SecUser;
import dsy.service.admin.UserService;
import net.sf.json.JSONObject;

@Controller
public class AdminUserController {

	@Autowired
	private UserService uService;
	
	// jsp 
	//用户管理页面
	@RequestMapping("adminUser.html")
	public String AdminUser(){
		return "jsp/admin/user";
	}
	
	//用户新增与修改
	@RequestMapping("user-add.html")
	public String AdminUserAdd(){
		return "jsp/admin/user_add";
	}
	
	//授权页面
	@RequestMapping("userAuthorize.html")
	public String authorize(HttpServletResponse response,
			HttpServletRequest request, ModelMap model) throws Exception{
		String realName = request.getParameter("realName");
		String userName = request.getParameter("userName");
		model.addAttribute("realName", realName);
		model.addAttribute("userName", userName);
		return "jsp/admin/user_authorize";
	}
	
	// 获取用户列表
	@RequestMapping("admin/getAdminUserList.html")
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
	
	// 获取角色名称
	@RequestMapping("admin/getRoleName.html")
	@ResponseBody
	public List<JSONObject> getRoleName() {
		return uService.getRoleName();
	}
	
	// 新增与编辑
	@RequestMapping("admin/saveAndEditUser.html")
	@ResponseBody
	public JSONObject saveAndEditUser(SecUser user,HttpServletRequest request) {
	   JSONObject data = null;
			 try {
				data = uService.saveAndEditUser(user,request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
	
	//获取用户角色
	@RequestMapping("admin/getUserRole.html")
	@ResponseBody
	public JSONObject getUserRole(HttpServletRequest request) {

		String userid = request.getParameter("userid");
		List<JSONObject> data = uService.getRoleName();
		JSONObject returnData = new JSONObject();
		returnData.put("role", data);
		returnData.put("URid", uService.getUserRole(userid));
		return returnData;

	}
	
	//角色授权
	@RequestMapping("admin/saveUserRole.html")
	@ResponseBody
	public JSONObject saveUserRole(HttpServletRequest request) {
		JSONObject data = new JSONObject();
		if (uService.saveUserRole(request)) {
			data.put("status", "000"); //授权成功
		} else
			data.put("status", "001"); //授权失败
		
		return data;
	}
	
	// 删除用户信息
	@RequestMapping("admin/deleteUser.html")
	@ResponseBody
	public JSONObject deleteUser(HttpServletRequest request) {
		JSONObject data = null;
		try {
			data = uService.deleteUser(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// 获取单个用户信息
	@RequestMapping("admin/getSingleUser.html")
	@ResponseBody
	public List<SecUser> getSingleUser(HttpServletRequest request) {
		List<SecUser> data = null;
		try {
			data = uService.getSingleUser(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
