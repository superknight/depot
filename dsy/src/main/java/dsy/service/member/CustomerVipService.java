package dsy.service.member;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface CustomerVipService {

	//获取客户列表
	public JSONObject getCustomerVipList(HttpServletRequest request) throws Exception;
	
}
