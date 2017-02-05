package dsy.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import dsy.dao.BaseJdbcDao;

public class BaseJdbcDaoImpl extends JdbcDaoSupport implements BaseJdbcDao {

	public static Logger logger=Logger.getLogger(BaseJdbcDaoImpl.class);

	
	public BaseJdbcDaoImpl() {
		
	}
	
	@Override
	public Connection getconntion(){
		return super.getConnection();
	}
	
	@Override
	public ResultSet execResultSet(String sql) {
		ResultSet rs=null;
		try {
			rs=this.getConnection().prepareStatement(sql).executeQuery();
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return rs;
    }

	@Override
	public SqlRowSet execRowset(String sql) {
		SqlRowSet rowset = this.getJdbcTemplate().queryForRowSet(sql);
        return rowset;
	}
}
