package dsy.service.shop;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dsy.module.DsyShop;
import dsy.utils.ModelAndView;

import net.sf.json.JSONObject;

public interface ShopService {

	/**
	 * 店铺数据输出
	 * @param request
	 * @return
	 */
	public JSONObject getShopList(HttpServletRequest request) throws SQLException;

	/**
	 * 获取shopid对应的数据
	 * @param shopid
	 * @return
	 */
	public DsyShop getOnlyShop(String shopid);

	/**
	 * 修改shopid对应数据
	 * @return
	 */
	public ModelAndView saveShop(HttpServletRequest request);

	/**
	 * 批量冻结
	 * @param request
	 * @return
	 */
	public ModelAndView deletShop(HttpServletRequest request);

}
