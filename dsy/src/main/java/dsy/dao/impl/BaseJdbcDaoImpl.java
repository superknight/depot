package dsy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import dsy.dao.BaseJdbcDao;
import util.PagesBean;

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

	public SqlRowSet execRowset(String sql, Object[] args) {
        SqlRowSet rowset = this.getJdbcTemplate().queryForRowSet(sql, args);
        return rowset;
    }
	
	@Override
	public PagesBean JdbcSimplePage(String countSql, String fullSql, int start, int length) {
		StringBuffer sbCount = new StringBuffer();
        sbCount.append(countSql);
        Connection conn = this.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        PagesBean dto = new PagesBean();
        int rowCount = 0;
        try {
            ps = conn.prepareStatement(fullSql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

//        	ps = conn.prepareStatement(fullSql);
            ps.setMaxRows(start + length);
            rs = ps.executeQuery();
        	
            SqlRowSet sqlCount = this.execRowset(sbCount.toString());
            if (null != sqlCount && sqlCount.next()) {
                rowCount = Integer.parseInt(sqlCount.getString(1));
                dto.setItemCount(rowCount);  //获取总数
            }
            
            if (start != 1) {
                rs.absolute(start-1);
           }
            dto.setResultSet(rs);
            dto.setItemCount(rowCount);
            

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return dto;
	}
	
	public boolean executesql(String sql) {
        try {
            this.getJdbcTemplate().execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public PagesBean JdbcSimplePage(String countSql, String fullSql, int start, int length, List<String> list) {
	  StringBuffer sbCount = new StringBuffer();
      sbCount.append(countSql);
      Connection conn = this.getConnection();
      ResultSet rs = null;
      java.sql.PreparedStatement ps = null;

      PagesBean dto = new PagesBean();
      int rowCount = 0;
      try {
    	  for(int i=0;i<list.size();i++){
        	  ps.setString(i+1, "%"+list.get(i)+"%");
          }
          ps = conn.prepareStatement(fullSql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                  ResultSet.CONCUR_READ_ONLY);

          ps.setMaxRows(start + length);
          
          rs = ps.executeQuery();
          
          SqlRowSet sqlCount = this.execRowset(sbCount.toString());
          if (null != sqlCount && sqlCount.next()) {
              rowCount = Integer.parseInt(sqlCount.getString(1));
              dto.setItemCount(rowCount);  //获取总数
          }
          
          if (start != 1) {
              rs.absolute(start-1);
         }
          dto.setResultSet(rs);
          dto.setItemCount(rowCount);
          

      } catch (Exception e) {
          e.printStackTrace();
      } 
      return dto;
	}
}
