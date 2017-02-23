package dsy.controller.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductBrandController {

	@RequestMapping("product_brand.html")
	public String productClass(HttpServletResponse response,
			HttpServletRequest request, ModelMap model){
		return "jsp/product/product_brand";
	}
}
