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
	
	//获取单个用户的信息：用于修改预览
	public List<SecUser> getSingleUser(HttpServletRequest request);
	
	//查询新增用户账号是否已经重复
	public Boolean checkUser(String username);
	
	//新增和修改用户
	public JSONObject saveAndEditUser(SecUser user,HttpServletRequest request) throws Exception;
	
	//删除用户信息
	public JSONObject deleteUser(HttpServletRequest request);
	
	//授予用户角色
	public Boolean saveUserRole(HttpServletRequest request);

	//获取该用户已经拥有的角色
	public JSONObject getUserRole(String userid);
}
