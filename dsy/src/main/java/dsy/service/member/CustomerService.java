package dsy.service.member;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface CustomerService {

	//获取客户列表
	public JSONObject getCustomerList(HttpServletRequest request) throws Exception;
	
	
	//冻结客户
	public boolean freezeCustomer(HttpServletRequest request);
	
	//解冻客户
	public boolean unfreezeCustomer(HttpServletRequest request);
		
}
