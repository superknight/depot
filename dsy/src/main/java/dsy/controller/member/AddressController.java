package dsy.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.module.DsyAddress;
import dsy.service.member.AddressService;
import net.sf.json.JSONObject;

@Controller
public class AddressController {

	@Autowired
	private AddressService aService;
	
	@RequestMapping("getAddress.html")
	public String address(HttpServletResponse response,
			HttpServletRequest request, ModelMap model){
		return "jsp/member/address";
	}
	
	@RequestMapping("member/getAddressList.html")
	@ResponseBody
	public List<DsyAddress> getAddressList(HttpServletRequest request){
		System.out.println(1111);
		List<DsyAddress> data = null;
		try {
			data = aService.getAddressList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
		
	}
	
}
