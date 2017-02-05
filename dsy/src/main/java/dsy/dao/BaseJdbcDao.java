package dsy.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface BaseJdbcDao {
	
	/**
	 * 获取connection连接
	 * @return
	 */
	public  Connection getconntion();
	
	 /**
     * 返回ResultSet集
     * 
     * @param sql
     * @return
     */
    public ResultSet execResultSet(String sql);
    
    public SqlRowSet execRowset(String sql);
}
