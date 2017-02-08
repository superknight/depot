package util;


import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

@SuppressWarnings("rawtypes")
public class PagesBean {


    protected int itemCount; // 所有的记录数
	
    public List list;

    private SqlRowSet rowSet = null;

    private ResultSet ResultSet = null;

    
    
    public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public PagesBean() {
    }

    public List getList() {
        return list;
    }

   

    public SqlRowSet getRowSet() {
        return rowSet;
    }

    public void setRowSet(SqlRowSet rowSet) {
        this.rowSet = rowSet;
    }

    public ResultSet getResultSet() {
        return ResultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        ResultSet = resultSet;
    }

}
