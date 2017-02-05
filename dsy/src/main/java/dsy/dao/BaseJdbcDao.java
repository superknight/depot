package dsy.dao;

import java.sql.Connection;
import java.sql.ResultSet;

public interface BaseJdbcDao {
	
	/**
	 * 获取connection连接
	 * @return
	 */
	public  Connection getconntion() throws Exception;
	
	 /**
     * 返回ResultSet集
     * 
     * @param sql
     * @return
     */
    public ResultSet execResultSet(String sql);
}
