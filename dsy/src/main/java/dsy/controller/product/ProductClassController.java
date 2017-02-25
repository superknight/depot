package dsy.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//获取产品表
	@RequestMapping("product/getProductList.html")
	@ResponseBody
	public JSONObject getProductList(HttpServletRequest request){
		JSONObject data = null;
		try {
			data = pcService.getProductList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	//获取产品类目录
	@RequestMapping("product/getProductClass.html")
	@ResponseBody
	public List<JSONObject> getProductClass(HttpServletRequest request){
		List<JSONObject> list = null;
		try {
			list = pcService.productClass(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//新增与修改
	@RequestMapping("product/saveAndEditProductClass.html")
	@ResponseBody
	public JSONObject saveAndEditProductClass(HttpServletRequest request){
		JSONObject data = null;
		try {
			data = pcService.saveAndEditProductClass(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	//新增一级目录
	@RequestMapping("product/addProductFirst.html")
	@ResponseBody
	public JSONObject addProductFirst(HttpServletRequest request){
		JSONObject data = null;
		try {
			data = pcService.addProductFirst(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	//删除
	@RequestMapping("product/deleteProductClass.html")
	@ResponseBody
	public JSONObject delProductClass(HttpServletRequest request){
		JSONObject data = null;
		try {
			data = pcService.delProductClass(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
