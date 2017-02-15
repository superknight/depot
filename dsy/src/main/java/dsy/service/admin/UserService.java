package dsy.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dsy.module.SecUser;
import net.sf.json.JSONObject;

public interface UserService {

	//获取用户列表
	public JSONObject getUserList(HttpServletRequest request) throws Exception;
	
	//获取所有角色
	public List<JSONObject> getRoleName();
	
	//新增和修改用户
	public JSONObject saveAndEditUser(SecUser user,HttpServletRequest request) throws Exception;
	
	//授予用户角色
	public Boolean saveUserRole(HttpServletRequest request);

	//获取该用户已经拥有的角色
	public JSONObject getUserRole(String userid);
}
