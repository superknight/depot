package dsy.service.admin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface RoleService {

	//获取角色列表
	public JSONObject getRoleList(HttpServletRequest request) throws Exception;
}
