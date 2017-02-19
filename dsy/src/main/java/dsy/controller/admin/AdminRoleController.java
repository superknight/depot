package dsy.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.admin.RoleService;
import net.sf.json.JSONObject;
import util.Response2JSON;

@Controller
public class AdminRoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("adminRole.html")
	public String role(){
		return "jsp/admin/role";
	}
	
	//新增和修改页面
	@RequestMapping("role-add.html")
	public String RoleAdd() throws Exception {
		return "jsp/admin/role_add";
	}
	
	//角色授权
	@RequestMapping("role-permission.html")
	public String RolePermission(HttpServletResponse response,
			HttpServletRequest request, ModelMap model) throws IOException {
		String rolename = request.getParameter("rolename");
		model.put("rolename", rolename);
		return "jsp/admin/role_permission";
	}
	
	// 获取角色列表
	@RequestMapping("admin/getAdminRoleList.html")
	@ResponseBody
	public JSONObject getAdminRoleList(HttpServletRequest request) {
		   JSONObject data = null;
			try {
				data = roleService.getRoleList(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
	}

	// 获取单个用户信息
	@RequestMapping("admin/getSingleRole.html")
	@ResponseBody
	public JSONObject getSingleRole(HttpServletRequest request) {
		JSONObject data = null;
		try {
			data = roleService.getSingleRole(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return data;
	}
	
	// 新增与编辑
	@RequestMapping("admin/saveAndEditRole.html")
	@ResponseBody
	public JSONObject saveAndEditRole(HttpServletRequest request) {
		JSONObject data = null;
		try {
			data = roleService.saveAndEditRole(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// 删除用户信息
	@RequestMapping("admin/deleteRole.html")
	@ResponseBody
	public JSONObject deleteRole(HttpServletRequest request) {
		JSONObject data = null;
		try {
				data = roleService.deleteRole(request);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return data;
	}
	
	// 获取所有菜单
	@RequestMapping("admin/getMenus.html")
	@ResponseBody
	public JSONObject getMenus(HttpServletRequest request) {
		List<JSONObject> data = roleService.getMenuFList(request);
			if (data.size() <= 0) {
				return Response2JSON.toJSON("", "001");
			} else
				return Response2JSON.toJSON(data, "000");
	}
	
	@RequestMapping("admin/role-authorize.html")
	@ResponseBody
	public JSONObject saveRoleAuthorize(HttpServletRequest request) {
		if (roleService.saveAuthorize(request)) {
			return Response2JSON.toJSON("授权成功", "000");
		} else
			return Response2JSON.toJSON("授权失败", "001");
	}
}
