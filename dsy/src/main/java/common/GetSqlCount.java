package common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import dsy.dao.BaseJdbcDao;


@Component
public class GetSqlCount {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	public String getCount(String sql) throws Exception{
		
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		String count = null;
		while(rs.next()){
			count=rs.getString("count");
		}
		return count;
		
	}
}
