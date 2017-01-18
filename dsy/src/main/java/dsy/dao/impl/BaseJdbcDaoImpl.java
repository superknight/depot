package dsy.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import dsy.dao.BaseJdbcDao;
import util.SqlToHtmlTable;
import util.SqlUtil;
import util.StringUtil;


/**
 * @author: carrot
 * @version: 1.0
 */
public class BaseJdbcDaoImpl extends JdbcDaoSupport implements BaseJdbcDao {

	public static Logger logger=Logger.getLogger(BaseJdbcDaoImpl.class);
	
	private SqlUtil sqlUtil;
	private SqlToHtmlTable sqlToHtmlTable;
	public BaseJdbcDaoImpl() {
	}

	//查询提交
	public SqlRowSet execRowset(String sql, Object[] args) {
		SqlRowSet rowset = this.getJdbcTemplate().queryForRowSet(sql, args);
		return rowset;
	}

	public SqlRowSet execRowset(String sql) {
//		SqlRowSet rowset = (SqlRowSet) this.getJdbcTemplate().query(sql,
//				new SqlRowSetOracleResultSetExtractor());
		SqlRowSet rowset = this.getJdbcTemplate().queryForRowSet(sql);
		return rowset;
	}
	
	
	public final Connection  getconntion(){
		return super.getConnection();
	}

	public boolean execSql(String sql, Object[] args) {
		if (StringUtil.isBlank(sql)) {
			return false;
		}
		this.getJdbcTemplate().update(sql, args);
		return true;
	}

	public String sqlToXml(String sql, boolean bXmlhead, String resHead) {
		return sqlToXml(sql, null, bXmlhead, resHead);
	}

	public String sqlToXml(String p_Sql, Object[] args) {
		return this.sqlToXml(p_Sql, args, true, "Rows");

	}

	public void setSqlToHtmlTable(SqlToHtmlTable sqlToHtmlTable) {
		this.sqlToHtmlTable = sqlToHtmlTable;
	}

	public SqlUtil getSqlUtil() {
		return sqlUtil;
	}

	public void setSqlUtil(SqlUtil sqlUtil) {
		this.sqlUtil = sqlUtil;
	}

	public StringBuffer sqlToCsv(String sql) {
		StringBuffer sb = new StringBuffer();
		SqlRowSet rs = this.execRowset(sql, null);

		// 列的名称-----------------
		SqlRowSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			if (i != 1) {
				sb.append(",");
			}
			sb.append(rsmd.getColumnName(i));
		}
		sb.append("\n");
		// 数据-----------------------
		while (rs.next()) {
			for (int i = 1; i <= columnCount; i++) {
				if (i != 1) {
					sb.append(",");
				}
				String vl = rs.getString(i);
				if (vl == null)
					vl = "";
				sb.append(vl);
			}
			sb.append("\n");
		}

		return sb;
	}

	

	public void delByTbnameFiledID(String p_Table, String p_Field, String p_ID) {
		String sql = "delete from " + p_Table + " where " + p_Field + "="
				+ p_ID;
		this.getJdbcTemplate().execute(sql);
	}
	
	

	public boolean executesql(String sql) {
		try{
			this.getJdbcTemplate().execute(sql);
			return true;
		} catch (Exception e){ 
			logger.error("error", e);
		}
		return false;
	}

	public String[][] execSel(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet execResultSet(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sqlToXml(String sql, Object[] args, boolean xmlHeadFlag, String resHead) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getTableKeyID(String p_Table, String p_Field) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getTbMaxID(String p_Table, String p_Field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String export(String sql, int pageStart, int pageSize, int clickID, String editURL, String delURL,
			String clickURL, String otherVar) {
		// TODO Auto-generated method stub
		return null;
	}
}
