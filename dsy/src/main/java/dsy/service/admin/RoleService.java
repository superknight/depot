package dsy.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface RoleService {

	//获取角色列表
	public JSONObject getRoleList(HttpServletRequest request) throws Exception;
	
	//获取单个角色的信息
	public JSONObject getSingleRole(HttpServletRequest request);
	
	//查询新增用户账号是否已经重复
	public Boolean checkRole(String role);
	
	//获取第一层菜单
	public List<JSONObject> getMenuFList(HttpServletRequest request);
		
	//获取第二层菜单
	public List<JSONObject> getMenuTList(String parentid);
		
	//获取该角色已经拥有的权限
	public JSONObject getRoleMenuList(String roleid);
	
	//新增和修改角色
	public JSONObject saveAndEditRole(HttpServletRequest request) throws Exception;
	
	//删除用户信息
	public JSONObject deleteRole(HttpServletRequest request);
	
	//给角色授权
	public Boolean saveAuthorize(HttpServletRequest request);
}
