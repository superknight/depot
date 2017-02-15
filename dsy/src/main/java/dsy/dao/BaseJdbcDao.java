package dsy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import util.PagesBean;

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
    
    /**
     * 执行SQL
     * 
     * @param sql
     * @return
     */
    public boolean executesql(String sql);
    

    public SqlRowSet execRowset(String sql, Object[] args);
    /**
     * 
     * @param countSql 查询总数的sql
     * @param fullSql 查询条件的sql
     * @param start 起始页
     * @param length 返回数据条数
     * @return
     */
    public PagesBean JdbcSimplePage(String countSql,String fullSql,int start,int length);
    

    public PagesBean JdbcSimplePage(String countSql,String fullSql,
    		int start,int length,List<String> list);
}
