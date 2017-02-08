package dsy.service.admin;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface UserService {

	//获取用户列表
	public JSONObject getUserList(HttpServletRequest request) throws Exception;
}
