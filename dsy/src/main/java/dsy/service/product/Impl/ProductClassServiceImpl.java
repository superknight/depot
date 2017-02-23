package dsy.service.product.Impl;

import java.sql.ResultSet;
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
//		List<DsyProductClass> list = new ArrayList<DsyProductClass>();
		while(rs.next()){
			JSONObject ob = new JSONObject();
			
			ob.put("id", rs.getString("id"));
			ob.put("pId", rs.getString("class_id"));
			ob.put("name", rs.getString("classname"));
			String content = rs.getString("content");
			if(StringUtil.isNotBlank(content)){
				ob.put("file", content);
			}
			System.out.println(ob);
			list.add(ob);
//			DsyProductClass spClass = new DsyProductClass();
//			spClass.setId(rs.getString("id"));
//			spClass.setClassname(rs.getString("classname"));
//			spClass.setClass_id(rs.getString("class_id"));
//			spClass.setClass_grade(rs.getString("class_grade"));
//			spClass.setContent(rs.getString("content"));
//			spClass.setDescripe(rs.getString("descripe"));
//			spClass.setCreater(rs.getString("creater"));
//			spClass.setCreate_time(rs.getString("create_time"));
//			list.add(spClass);
		}
		return list;
	}

}
