package util;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

 

/**
 * 
 * Description:
 * Copyright: Copyright (c) 2006
 * 
 * 
 * @author 胡如根
 * @version 1.0
 */
public class SqlUtil {
	
	Logger logger=Logger.getLogger(SqlUtil.class);
	
	private Connection conn;
			
	public SqlUtil() {
	}

	public ResultSet SQLToRS(String p_Sql, Connection p_Conn)
			throws SQLException {
		Statement st = p_Conn.createStatement();
		ResultSet rs = st.executeQuery(p_Sql);
		return rs;
	}

	/**
	 * 返回二维数据组
	 * 
	 * @param p_Sql
	 *            String
	 * @return String[][]
	 */
	public String[][] execSel(String p_Sql) {

		//SimpleDateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat tsFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ArrayList<String[]> v_Ret = new ArrayList<String[]>(20); // Initial allocate a 20 array

		// the actually sql operation

		Statement v_Stmt = null;
		ResultSet v_Rs = null;
		ResultSetMetaData v_MetaData = null;
		int v_Cols = 0;

		try {

			v_Stmt = this.conn.createStatement();

			v_Rs = v_Stmt.executeQuery(p_Sql); // Get the result set

			// Get the resultset's structure from the ResultSetMetaData
			v_MetaData = v_Rs.getMetaData();
			v_Cols = v_MetaData.getColumnCount();

			while (v_Rs.next()) {
				String v_Row[] = new String[v_Cols];

				for (int i = 1; i < v_Cols + 1; i++) {
					v_Row[i - 1] = v_Rs.getString(i);
				}

				v_Ret.add(v_Row);

			} // End while
			// v_Stmt.close();
			// v_Rs.close();

		} catch (Exception e) {
			logger.error("error", e);
		} finally {
			try {
				if (v_Rs != null) {
					v_Rs.close();
				}
				if (v_Stmt != null) {
					v_Stmt.close();
				}
			} catch (Exception e) {
				logger.error("error", e);
			}
		}

		// Return the result
		if (v_Ret.isEmpty()) {
			return null;
		} else {
			Object[] v_Rows = v_Ret.toArray();
			String[][] v_StrRet = new String[v_Rows.length][];
			for (int i = 0; i < v_Rows.length; i++) {
				v_StrRet[i] = (String[]) v_Rows[i];
			}
			return v_StrRet;

			// The following Line cannot do a Class Cast
			// and will Throw a exception Ljava.lang.Object;
			// return (String [][]) v_Ret.toArray();

		}

	}

	/**
	 * 查询得到数据库表的 ID字段的最大数值
	 * 
	 * @param p_Table
	 *            String
	 * @param p_Field
	 *            String
	 * @param conn
	 *            Connection
	 * @throws SQLException
	 * @return long
	 */
	public long getTbMaxID(String p_Table, String p_Field, Connection conn)
			throws SQLException {
		String v_Sql = "select max(" + p_Field + ") from " + p_Table;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(v_Sql);
		long res = -1;
		if (rs.next()) {
			res = rs.getLong(1);
		}

		rs.close();
		st.close();

		return res;
	}

	/**
	 * 
	 * @param p_Sql
	 *            String
	 * @return boolean
	 */
	public boolean execSql(String p_Sql) {

		//logger.info(p_Sql);
		boolean b_Res = true;

		try {
			Statement st = this.conn.createStatement();
			st.execute(p_Sql);
			st.close();
		} catch (Exception e) {
			logger.error("error", e);
			b_Res = false;
		}

		return b_Res;
	}

	public boolean execSqlNolog(String p_Sql) {

		// logger.info(p_Sql);
		boolean b_Res = true;

		try {
			Statement st = this.conn.createStatement();
			st.execute(p_Sql);
			st.close();
		} catch (Exception e) {
			logger.error("error", e);
			b_Res = false;
		}

		return b_Res;
	}

	public long getSeqValue(String p_SeqName) {
		long res = -1;
		String sql = "select " + p_SeqName + ".nextval from dual";
		try {
			Statement st = this.conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				res = rs.getLong(1);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.error("error", e);
		}

		return res;
	}

	public String SqlToXml(String p_Sql, String[] paramValues) {

		PreparedStatement pst = null;
		try {
			pst = this.conn.prepareStatement(p_Sql, paramValues);

		} catch (SQLException e) {
			logger.error("error", e);
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					logger.error("error", e);
				}
			}

		}

		return null;
	}

	/**
	 * 将SQL的查询结果转为excel文件
	 * 
	 * @param p_Sql
	 * @param p_Filepath
	 * @return
	 */
	public boolean SqlToXls(String p_Sql, String p_Filepath) {

		return true;
	}

	/**
	 * 将SQL语句转化成CSV格式的数据文本
	 * 
	 * @param p_Sql
	 * @return
	 */
	public StringBuffer SqlToCsv(String p_Sql) {
		StringBuffer sb = new StringBuffer();

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(p_Sql);
			// 列的名称-----------------
			ResultSetMetaData rsmd = rs.getMetaData();
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

			rs.close();
			st.close();
		} catch (Exception e) {
			logger.error("error", e);
		}

		return sb;
	}

	public long getSeqOfCommon() {
		String sql = "SELECT SEQ_COMMON.NEXTVAL FROM DUAL";
		long res = 0;

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				res = rs.getLong(1);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			logger.error("error", e);
		}

		return res;
	}

	/**
	 * 得到某个表的sequence, 表的sequence命名必须按照seq_table的形式
	 * 
	 * @param c
	 * @return
	 */
	public long getSeqOfModel(Class<?> c) {
		return 1001;
/*
		if (true)
			return 100l;

		String sql = "SELECT SEQ_" + c.getSimpleName() + ".NEXTVAL FROM DUAL";
		long res = 0;

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				res = rs.getLong(1);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
		*/
	}

	public String filterSqlStr(String p_Str) {
		if (p_Str == null) {
			return "";
		} else {
			String str = p_Str.replaceAll("'", "''");
			str = str.trim();
			return str;
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	

}
