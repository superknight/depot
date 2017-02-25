package dsy.service.product;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface ProductClassService {

	//树形菜单
	public List<JSONObject> productClass(HttpServletRequest request) throws SQLException;
	
	//获取客户列表
	public JSONObject getProductList(HttpServletRequest request) throws Exception;
	
	//新增和修改
	public JSONObject saveAndEditProductClass(HttpServletRequest request);
	
	//删除
	public JSONObject delProductClass(HttpServletRequest request);
	
	//新增一级目录
	public JSONObject addProductFirst(HttpServletRequest request);
}
