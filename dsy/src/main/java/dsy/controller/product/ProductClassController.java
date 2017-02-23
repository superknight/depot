package dsy.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.module.DsyProductClass;
import dsy.service.product.ProductClassService;
import net.sf.json.JSONObject;

@Controller
public class ProductClassController {

	@Autowired
	private ProductClassService pcService;
	
	@RequestMapping("product_class.html")
	public String productClass(HttpServletResponse response,
			HttpServletRequest request, ModelMap model){
		return "jsp/product/product_class";
	}
	
	@RequestMapping("product/getProductClass.html")
	@ResponseBody
	public List<JSONObject> getProductClass(HttpServletRequest request){
		List<JSONObject> list = null;
		try {
			list = pcService.productClass(request);
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
