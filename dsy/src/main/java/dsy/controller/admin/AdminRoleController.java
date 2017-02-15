package dsy.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.admin.RoleService;
import net.sf.json.JSONObject;

@Controller
public class AdminRoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("adminRole.html")
	public String role(){
		return "jsp/admin/role";
	}
	
	// 获取角色列表
	@RequestMapping("adminUser/getAdminRoleList.html")
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
}
