package dsy.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

	@RequestMapping("product.html")
	public String product(){
		return "jsp/product/product";
	}
}
