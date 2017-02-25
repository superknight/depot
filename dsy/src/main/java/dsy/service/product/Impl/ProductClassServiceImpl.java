package dsy.service.product.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import common.DataTablesResult;
import common.SimpleDataTables;
import dsy.dao.BaseJdbcDao;
import dsy.module.DsyProduct;
import dsy.service.product.ProductClassService;
import net.sf.json.JSONObject;
import util.PagesBean;
import util.StringUtil;

@Service
public class ProductClassServiceImpl implements ProductClassService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public List<JSONObject> productClass(HttpServletRequest request) throws SQLException {
		
		String sql = "select * from dsy_product_class ";
		
		SqlRowSet rs = baseJdbcDao.execRowset(sql);
		List<JSONObject> list = new ArrayList<JSONObject>();
		while(rs.next()){
			JSONObject ob = new JSONObject();
			
			ob.put("id", rs.getString("id"));
			ob.put("pId", rs.getString("pId"));
			ob.put("name", rs.getString("name"));
			list.add(ob);
		}
		return list;
	}

	@Override
	public JSONObject getProductList(HttpServletRequest request) throws Exception {
	
	String countSql = "select count(*) from dsy_product where 1=1 ";
	
	//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
	String fullSql = "select id,product_name as productName,class_id as pId,"
			+ "format,product_place as productPlace,supplier,"
			+ "picture_id as pictureId from dsy_product where 1=1 ";
	
	StringBuffer appSql = new StringBuffer();
	StringBuffer addSql = new StringBuffer();
	SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
	
	String productName = request.getParameter("extra_search[productName]");
	String supplier = request.getParameter("extra_search[supplier]");
	
	// for search
	if(StringUtil.isNotBlank(productName)){
		appSql.append(" and product_name '%").append(productName).append("%' ");
	}
	if(StringUtil.isNotBlank(supplier)){
		appSql.append(" and supplier '%").append(supplier).append("%' ");
	}
	
	// for sort and order
	if (StringUtil.isNotBlank(dataTables.getSort())) {
			addSql.append(" order by ").append(dataTables.getSort())
							.append(" ").append(dataTables.getOrder());
		}
			
	fullSql += appSql.toString() + addSql.toString();
	countSql += appSql.toString();
			
			
	PagesBean pageBean = this.baseJdbcDao.JdbcSimplePage(countSql,
			fullSql, dataTables.getStart(), dataTables.getLength());
	ResultSet rs = pageBean.getResultSet();
			
			
	List<DsyProduct> list = new ArrayList<DsyProduct>();
	while(rs.next()){
		DsyProduct pro = new DsyProduct();
		
		pro.setId(rs.getString("id"));
		pro.setpId(rs.getString("pId"));
		pro.setFormat(rs.getString("format"));
		pro.setProductName(rs.getString("productName"));
		pro.setProductPlace(rs.getString("productPlace"));
		pro.setSupplier(rs.getString("supplier"));
		pro.setPictureId(rs.getString("pictureId"));
		
		list.add(pro);
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
	public JSONObject saveAndEditProductClass(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pId = request.getParameter("pId");
		String name = request.getParameter("name");
		String sql = "";
		JSONObject returnData = new JSONObject();
		//新增
		if(StringUtil.isBlank(id)){
			sql = "insert into dsy_product_class (name,pId) "
					+ "values ('"+name+"',"+pId+")";
			
			if (this.baseJdbcDao.executesql(sql)) {
				returnData.put("msg", "000");
			} else {
				returnData.put("msg", "001");
			   }
		}
		else{  //修改
			sql = "update dsy_product_class set name='"+name
					+"' where id ="+id;
			if (this.baseJdbcDao.executesql(sql)) {
				returnData.put("msg", "100");
			} else {
				returnData.put("msg", "101");
			   }
		}
		return returnData;
	}

	@Override
	public JSONObject delProductClass(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sql = "delete from dsy_product_class where id ="+id;
		JSONObject ret = new JSONObject();
		if(this.baseJdbcDao.executesql(sql)){
			   ret.put("status", "000"); //删除成功
		   }
		   else{
			   ret.put("status", "001"); //删除失败
		   }
		return ret;
	}

	@Override
	public JSONObject addProductFirst(HttpServletRequest request) {
		String name = request.getParameter("name");
		String sql = "insert into dsy_product_class (name,pId)"
				+ " values ('"+name+"',0)";
		
		JSONObject returnData = new JSONObject();
		if (this.baseJdbcDao.executesql(sql)) {
			returnData.put("msg", "000");
		} else {
			returnData.put("msg", "001");
		   }
		return returnData;
	}

}
