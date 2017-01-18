package dsy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;
public interface BaseJdbcDao {
	
	
	public  Connection  getconntion();
	/**
	 * @param sql
	 * @param args
	 * @return
	 */
	public String[][] execSel(String sql, Object[] args);
	
	public SqlRowSet execRowset(String sql, Object[] args);
	
	/**
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean execSql(String sql,Object[] args);
	
	
	public SqlRowSet execRowset(String sql);
	
	/**
	 * 杩斿洖ResultSet闆�
	 * @param sql
	 * @return
	 */
	public ResultSet execResultSet(String sql) ;
	/**
	 * @param sql
	 * @param xmlhead
	 * @param reshead
	 * @return
	 */
	public String sqlToXml(String sql,Object[]args, boolean xmlHeadFlag, String resHead);

	public String sqlToXml(String p_Sql, boolean bXmlhead,String p_Reshead);
	
	public String sqlToXml(String p_Sql,Object[]args);

	public StringBuffer sqlToCsv(String sql);
	
	long getTableKeyID(String p_Table, String p_Field);
	 /**
     *鏌ヨ寰楀埌鏁版嵁搴撹〃鐨� ID瀛楁鐨勬渶澶ф暟鍊�
     * @param p_Table String
     * @param p_Field String
     * @param conn Connection
     * @throws SQLException
     * @return long
     */
    long getTbMaxID(String p_Table, String p_Field);
    
    void delByTbnameFiledID(String p_Table, String p_Field, String p_ID);
    
	public String export(
	        String sql,
	        int pageStart,
	        int pageSize,
	        int clickID,
	        String editURL,
	        String delURL,
	        String clickURL,
	        String otherVar
	        );
	/**
	 * 鎵цSQL
	 * @param sql
	 * @return
	 */
	public boolean executesql(String sql);
	
}
