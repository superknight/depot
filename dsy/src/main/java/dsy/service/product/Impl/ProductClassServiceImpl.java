package dsy.service.product.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


import dsy.dao.BaseJdbcDao;
import dsy.service.product.ProductClassService;
import net.sf.json.JSONObject;
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
	public JSONObject getProductClassList(HttpServletRequest request) throws Exception {
		String countSql = "select count(*) from dsy_user where 1=1 ";
		
		//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
		String fullSql ="";
		return null;
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

}
