package dsy.service.shop.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import util.PagesBean;
import util.StringUtil;

import common.DataTablesResult;
import common.SimpleDataTables;

import dsy.dao.BaseJdbcDao;
import dsy.module.DsyShop;
import dsy.service.shop.ShopService;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	@Override
	public JSONObject getShopList(HttpServletRequest request) throws SQLException {
		String countSql = "select count(*) "
					    + "  from dsy_shop ds "
					    + "  left join sec_user su on su.id = ds.approver "
					    + "  left join dsy_user du on du.id = ds.shopkeeper where 1=1 ";
	
		//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
		String fullSql = "select ds.id, ds.shopname, du.customer, ds.type, ds.status, ds.apply_time, su.name approvername, ds.adopt_time, ds.remark "
				       + "  from dsy_shop ds "
				       + "  left join sec_user su on su.id = ds.approver "
				       + "  left join dsy_user du on du.id = ds.shopkeeper where 1=1 ";
		
	
		StringBuffer appSql = new StringBuffer();
		StringBuffer addSql = new StringBuffer();
		SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
		
		String shopName = request.getParameter("extra_search[shopname]");
		
		// for search
		if(StringUtil.isNotBlank(shopName)){
			appSql.append(" and ds.shopname like '%").append(shopName).append("%' or du.customer like '%").append(shopName).append("%'");
		}
		
		// for sort and order
		if (StringUtil.isNotBlank(dataTables.getSort())) {
			addSql.append(" order by ").append(dataTables.getSort())
							.append(" ").append(dataTables.getOrder());
			}
		
		fullSql += appSql.toString() + addSql.toString();
//		System.out.println(fullSql);
		countSql += appSql.toString();
		
		
		PagesBean pageBean = this.baseJdbcDao.JdbcSimplePage(countSql,
				fullSql, dataTables.getStart(), dataTables.getLength());
		ResultSet rs = pageBean.getResultSet();
		
		
		List<DsyShop> list = new ArrayList<DsyShop>();
		
		while(rs.next()){
			DsyShop shop = new DsyShop();
			shop.setId(rs.getInt("id"));
			shop.setShopname(rs.getString("shopname"));
			shop.setName(rs.getString("customer"));
			shop.setType(rs.getString("type"));
			shop.setStatus(rs.getString("status"));
			shop.setApprover(rs.getString("approvername"));
			shop.setApplytime(rs.getString("apply_time"));
			shop.setAdopttime(rs.getString("adopt_time"));
			shop.setRemark(rs.getString("remark"));
			list.add(shop);
		 }
	
		int draw = dataTables.getDraw();
		//结果数
		String recordsTotal = String.valueOf(pageBean.getItemCount());
		//过滤后数目，暂时和结果数保持一致
		String recordsFiltered = recordsTotal;
		return DataTablesResult.DataResult(draw, recordsTotal, recordsFiltered,
				list);
	}

	@Override
	public DsyShop getOnlyShop(String shopid) {
		String sql = "SELECT * " 
				   + "  from dsy_shop "
				   + " where id='" + shopid + "'";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		DsyShop shop = new DsyShop();
		while (rs.next()) {
			shop.setId(rs.getInt("id"));
			shop.setShopname(rs.getString("shopname"));
			shop.setType(rs.getString("type"));
			shop.setStatus(rs.getString("status"));
			shop.setApplytime(rs.getString("apply_time"));
			shop.setAdopttime(rs.getString("adopt_time"));
			shop.setRemark(rs.getString("remark"));
			
		}
		return shop;
	}

	@Override
	public JSONObject saveShop(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String shopid = request.getParameter("shopid");
		String shopname = request.getParameter("shopname");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		String remark = request.getParameter("remark");
		String sql = " update dsy_shop set" 
				   + " shopname ='" + shopname + "',"
				   + " type ='" + type + "',"
				   + " status ='" + status + "',"
				   + " remark ='" + remark + "'"
				   + " where id ='" + shopid + "'";
		try {
			boolean ok = this.baseJdbcDao.executesql(sql);
			if(ok){
				jsonObject.put("status", 000);
			}else{
				jsonObject.put("status", 001);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	
}
