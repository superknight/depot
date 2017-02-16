package dsy.controller.shop;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.module.DsyShop;
import dsy.service.shop.ShopService;
import dsy.utils.ModelAndView;

@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping("shop.html")
	public String role(){
		return "jsp/shop/shop";
	}
	
	// 获取用户列表
	@RequestMapping("shop/getShopList.html")
	@ResponseBody
	public JSONObject getAdminUserList(HttpServletRequest request) {
		JSONObject data = null;
		try {
			data = shopService.getShopList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping("shop/shop-add.html")
	public String shopAdd(HttpServletRequest request){
		String shopid = request.getParameter("shopid");
		DsyShop shop = this.shopService.getOnlyShop(shopid);
		request.setAttribute("shop", shop);
		return "jsp/shop/shop-add";
	}
	
	@RequestMapping(value = "shop/saveShop.html", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView saveShop(HttpServletRequest request){
		ModelAndView modelAndView = this.shopService.saveShop(request);
		return modelAndView;
	}
	
	@RequestMapping(value = "shop/deletShop.html", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView deletShop(HttpServletRequest request){
		ModelAndView modelAndView = this.shopService.deletShop(request);
		return modelAndView;
	}
	
}
