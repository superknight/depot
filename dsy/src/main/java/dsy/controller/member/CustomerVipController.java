package dsy.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.member.CustomerVipService;
import net.sf.json.JSONObject;

@Controller
public class CustomerVipController {

	@Autowired
	private CustomerVipService cvService;
	
	@RequestMapping("customer_vip.html")
	public String customerVip(HttpServletResponse response,
			HttpServletRequest request, ModelMap model){
		return "jsp/member/customer_vip";
	}
	
	// 获取用户列表
	@RequestMapping("member/getCustomerVipList.html")
	@ResponseBody
	public JSONObject getCustomerVipList(HttpServletRequest request) {
			JSONObject data = null;
			try {
				  data = cvService.getCustomerVipList(request);
						
			} catch (Exception e) {
				e.printStackTrace();
			 }
			return data;
	}
}
