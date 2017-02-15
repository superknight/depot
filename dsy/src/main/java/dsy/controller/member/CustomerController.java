package dsy.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.member.CustomerService;
import net.sf.json.JSONObject;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	@RequestMapping("customer.html")
	public String customer(){
		return "jsp/member/customer";
	}
	
	// 获取用户列表
	@RequestMapping("member/getCustomerList.html")
	@ResponseBody
	public JSONObject getCustomerList(HttpServletRequest request) {
			JSONObject data = null;
			try {
			    data = cService.getCustomerList(request);
					
			} catch (Exception e) {
				e.printStackTrace();
		       }
				return data;
			}
}
