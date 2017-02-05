package dsy.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dsy.dao.BaseJdbcDao;

public class BaseJdbcDaoImpl extends JdbcDaoSupport implements BaseJdbcDao {

	public static Logger logger=Logger.getLogger(BaseJdbcDaoImpl.class);

	
	public BaseJdbcDaoImpl() {
		
	}
	
	@Override
	public Connection getconntion() throws Exception{
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
}
