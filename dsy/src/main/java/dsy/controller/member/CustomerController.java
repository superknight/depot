package dsy.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.member.CustomerService;
import net.sf.json.JSONObject;
import util.Response2JSON;

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
	
	@RequestMapping("member/unfreezeCustomer.html")
	@ResponseBody
	public JSONObject unfreezeCustomer(HttpServletRequest request){
		if(this.cService.unfreezeCustomer(request)){
			return Response2JSON.toJSON("解冻成功！", "000");
		}else{
			return Response2JSON.toJSON("解冻失败！", "001");
		}
	}
	
	@RequestMapping("member/freezeCustomer.html")
	@ResponseBody
	public JSONObject freezeCustomer(HttpServletRequest request){
		if(this.cService.freezeCustomer(request)){
			return Response2JSON.toJSON("冻结成功！", "000");
		}else{
			return Response2JSON.toJSON("冻结失败！", "001");
		}
	}
}
