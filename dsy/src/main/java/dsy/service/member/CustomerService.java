package dsy.service.member;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface CustomerService {

	//获取用户列表
		public JSONObject getCustomerList(HttpServletRequest request) throws Exception;
		
}
