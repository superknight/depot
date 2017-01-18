package util;

/**
 * 有两个click 事件
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import dsy.dao.impl.BaseJdbcDaoImpl;




public class SqlToHtmlTable {
	Logger logger=Logger.getLogger(SqlToHtmlTable.class);

	
    private int totalRecordCount = 0; //总记f录数
    private int pageStart = 1; //当前页
    private int totalPage = 0; //总页数
    private int pageSize = 3; //每页显示记录数
    private int currentPageSize = 0; //实际当前页记录数
    private String URL = ""; //当前页的地址
    private String otherArg = "";
    private boolean noData = true; //当没有检索到数据时 为false
    //private String editText = "edit";
    //private String delText = "delete";
    private String editText = "编辑";
    private String delText = "删除";
    //private String confirmText = "Are sure to delete data ？click Sure to continue.";
    private String confirmText = "确定要删除数据?";

    //static org.apache.log4j.Logger logger = MyLogger.getLogger(SqlToHtmlTable.class);
    
    private BaseJdbcDaoImpl parent;
      

	public BaseJdbcDaoImpl getParent() {
		return parent;
	}

	public void setParent(BaseJdbcDaoImpl parent) {
		this.parent = parent;
	}

	public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public int getPageStart() {
    	if(this.pageStart<=1){
    		this.pageStart=1;
    	}
        return pageStart;
    }

    public String getOtherArg() {
        return otherArg;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public void setOtherArg(String otherArg) {
        this.otherArg = otherArg;
    }

    public int getTotalPage() {
        return totalPage;
    }
    
 
    public SqlToHtmlTable() {
    }

    public String export(
    		 
        String sql,
        int pageStart,
        int pageSize,
        int clickID,
        String editURL,
        String delURL,
        String clickURL
        ) {
        return export(  sql, pageStart, pageSize, clickID, -1, editURL, delURL, clickURL, "");
    }

    public String export(
    		  
        String sql,
        int pageStart,
        int pageSize,
        int clickID,
        int clickID2,
        String editURL,
        String delURL,
        String clickURL,
        String clickURL2
        ) {

        StringBuffer sb = new StringBuffer(8192);
        String PKName = "";
        String PKValue = "";
        this.pageSize = pageSize;
        this.pageStart = pageStart;
       //this.pageSize = ( (request.getParameter("pageSize") != null) ? Integer.parseInt(request.getParameter("pageSize")) : pageSize);
       // this.pageStart = (request.getParameter("pageStart") != null) ? Integer.parseInt(request.getParameter("pageStart")) : pageStart;
       // this.URL = ServletActionContext.getRequest().getRequestURI();
        //Connection conn = DBConn.getInstance().getConnection("apts");
        try {

          
        	SqlRowSet rs = this.parent.execRowset(sql, null);
            SqlRowSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            //设置总记录数，总页数，起始页数
            //
            this.totalRecordCount = 0;
            while (rs.next()) {
                this.totalRecordCount++;
            } //

            //this.totalRecordCount = getRecordcount(sql, conn);
            if (totalRecordCount % pageSize > 0) {
                totalPage = totalRecordCount / this.pageSize + 1;
            }
            else {
                totalPage = totalRecordCount / this.pageSize;
            }
            if (this.pageStart > totalPage) {
                this.pageStart = totalPage;
            }

            int recordStart, recordEnd;
            recordStart = (this.getPageStart() - 1) * this.pageSize;
            if (totalRecordCount > 0) {
                recordEnd = this.pageStart * this.pageSize;
            }
            else {
                recordEnd = 0;
            }
            if (recordEnd > totalRecordCount) {
                recordEnd = totalRecordCount;
            }
            currentPageSize = recordEnd - recordStart;

            //表头
            sb.append(
                "<table width=100%  border=1 align=center bordercolor=#008000 cellpadding=0 cellspacing=0 bordercolordark=#FFFFFF >\n");
            sb.append("   <tr align=center >\n");

            PKName = rsmd.getColumnLabel(1);
            sb.append("<td class=tdhead nowrap>Sequence</td>");
            for (int i = 1; i < columnCount; i++) { //从1开始，pk不显示
                sb.append("   <td class=tdhead nowrap>");
                sb.append(rsmd.getColumnLabel(i + 1)); //mysql 才这样
                //sb.append(rsmd.getColumnName(i + 1) );  //MsSQL

                sb.append("</td>");
            }
            if ( (!editURL.equals("")) || (!delURL.equals(""))) {
                sb.append("  <td class=tdhead nowrap>");
                sb.append("Operation");
                sb.append("</td>");
            }
            sb.append("</TR>\n");

            //没有检索到数据，出口

            if (this.totalRecordCount <= 0) {
                sb.append("<TR  class=trdata0>\n");
                sb.append("<TD  colspan=\"" + (columnCount + 1) + "\">empty data！</TD>");
                sb.append("</TR>\n");
                noData = false;

            }
                
            rs = this.parent.execRowset(sql, null);
            for (int i = 0; i < recordStart; i++) {
                rs.next();
            }
            //rs = this.getResulteSet(sql, conn, pageSize, recordStart);

            if (noData) {
                for (int i = 0; i < currentPageSize && rs.next() ; i++) {
                    String trClass = "trdata" + (i % 2);
                    String tdClass = "tddata" + (i % 2);
                    sb.append("\n<tr onmouseover=this.className='trover'; onmouseout=this.className='"
                              + trClass + "'; class='" + trClass + "'> ");
                    
                    
                    PKValue = rs.getString(1);
                    sb.append("<td class=" + tdClass + ">" + (i + recordStart + 1) + "</td>");
                    //int index = 0;
                    for (int j = 1; j < columnCount; j++) {

                        sb.append("<td class=" + tdClass + ">");

                        String colname = rsmd.getColumnName(j + 1);
                        String collabel = rsmd.getColumnLabel(j+1) ;
                        String temp=null;
                        
                        if (colname.equals("opdate")
                            || collabel.indexOf("_time")>-1
                            ) {
                        	temp = rs.getString(j + 1);
                        }
                       
                        else {
                            temp = rs.getString(j + 1);
                        }

                        if (temp == null || temp == "") {
                            temp = "&nbsp;";
                        }
                        temp = temp.trim();
                        if (temp.equals("")) {
                            temp = "&nbsp;";
                        }

                        if (clickID == j) {
                            sb.append("<A mk='clickA' HREF=\"");
                            sb.append(clickURL);
                            sb.append(clickURL.indexOf("?") > 0 ? "&" : "?");
                            sb.append(PKName);
                            sb.append("=");
                            sb.append(PKValue);
                            sb.append("&pageStart=" + this.pageStart);
                            sb.append("\">");
                            sb.append(temp);
                            sb.append("</A>");
                        }
                        else if (clickID2 == j) {
                            sb.append("<A HREF=\"");
                            sb.append(clickURL2);
                            sb.append(clickURL2.indexOf("?") > 0 ? "&" : "?");
                            sb.append(PKName);
                            sb.append("=");
                            sb.append(temp);
                            sb.append("&pageStart=" + this.pageStart);
                            sb.append("\">");
                            sb.append(temp);
                            sb.append("</A>");
                        }
                        else {
                            sb.append(temp);
                        }
                        sb.append("</td>");
                    }
                    if ( (!editURL.equals("")) || (!delURL.equals(""))) {
                        sb.append("<td  class=" + tdClass + " nowrap><div align=center>");
                        if (!editURL.equals("")) {
                            sb.append("<A HREF=\"");
                            sb.append(editURL);
                            sb.append(editURL.indexOf("?") > 0 ? "&" : "?");
                            sb.append(PKName);
                            sb.append("=");
                            sb.append(PKValue);
                            sb.append("&action=edit");
                            sb.append("&pageStart=" + this.pageStart);
                            sb.append("\">");
                            sb.append(editText);
                            sb.append("</A>&nbsp;&nbsp;");
                        }
                        if (!delURL.equals("")) {
                            sb.append("<A HREF=\"");
                            sb.append(delURL);
                            sb.append(delURL.indexOf("?") > 0 ? "&" : "?");
                            sb.append(PKName);
                            sb.append("=");
                            sb.append(PKValue);
                            sb.append("&action=del");
                            sb.append("&pageStart=" + this.pageStart);
                            sb.append("\" onclick=\"return confirm('" + confirmText + "');\">");
                            sb.append(delText);
                            sb.append("</A>");
                        }
                        sb.append("</div></td>");
                    }
                    sb.append("</TR>");
                }

         
                 

            } // end if( noData)

            sb.append("\n<tr class=class=trdata0 align=center> ");
    
            sb.append("<td align=right height=25 colspan=" + (columnCount + 1) + "  class=tddata0>");
            sb.append(getStatistic() + getNav());
            sb.append("</td></tr>");
            sb.append("</TABLE>");
            
            return sb.toString();

        }
        catch (Exception ex) {
        	logger.error("error", ex);
            return "错误:" + ex.getMessage();
        }
        finally{
	        //DBConn.getInstance().freeConnection("apts", conn);
        }

    }

    @SuppressWarnings("unused")
	private int getRecordcount(String p_Sql, Connection p_Conn) {
        int v_Res = 0;
        //String v_Sql = p_Sql.toLowerCase();
        //int pos_select = p_Sql.indexOf("select");
        int pos_from = p_Sql.indexOf("from");
        int pos_order = p_Sql.lastIndexOf("order");
        String v_SqlCount = "select count(*)  " + p_Sql.substring(pos_from, pos_order);
        //logger.info(v_SqlCount);
        try {
            Statement st = p_Conn.createStatement();
            ResultSet rs = st.executeQuery(v_SqlCount);
            rs.next();
            v_Res = rs.getInt(1);
            rs.close();
            st.close();
        }
        catch (Exception e) {
            logger.error("error", e);
            v_Res = getRecordcount2(p_Sql, p_Conn);
        }
        return v_Res;
    }

    private int getRecordcount2(String p_Sql, Connection p_Conn) {
        int v_Res = 0;
        try {
            Statement st = p_Conn.createStatement();
            ResultSet rs = st.executeQuery(p_Sql);
            while (rs.next()) {
                v_Res++;
            }
        }
        catch (Exception e) {
        	logger.error("error", e);
        }
        return v_Res;
    }

    @SuppressWarnings("unused")
	private ResultSet getResulteSet(String p_Sql, Connection p_Conn, int p_PageSize, int p_Recordstart) {
        return getResulteSet2(p_Sql, p_Conn, p_PageSize, p_Recordstart);
    }

    private ResultSet getResulteSet2(String p_Sql, Connection p_Conn, int p_PageSize, int p_Recordstart) {
        try {
            Statement st = p_Conn.createStatement();
            st.executeQuery(p_Sql);
            ResultSet rs = st.executeQuery(p_Sql);
            for (int i = 0; i < p_Recordstart; i++) {
                rs.next();
            }
            return rs;
        }
        catch (Exception e) {
        	logger.error("error", e);
        }
        return null;
    }

    private String getNav() {
        String First, Previous, Next, Last;

        First = (pageStart == 1) ? "[首页]&nbsp;" :
            "[<A HREF=\"" + this.URL + "?pageStart=1&pageSize=" + pageSize + getOtherArg() + "\">首页</A>]&nbsp;";
        Previous = (pageStart == 1) ? "[上一页]&nbsp;" :
            "[<A HREF=\"" + this.URL + "?pageStart=" + (pageStart - 1) + "&pageSize=" + pageSize + getOtherArg() + "\">上一页</A>]&nbsp;";
        Next = (pageStart >= totalPage) ? "[下一页]&nbsp;" :
            "[<A HREF=\"" + this.URL + "?pageStart=" + (pageStart + 1) + "&pageSize=" + pageSize + getOtherArg() + "\">下一页</A>]&nbsp;";
        Last = (pageStart >= totalPage) ? "[末页]&nbsp;" :
            "[<A HREF=\"" + this.URL + "?pageStart=" + totalPage + "&pageSize=" + pageSize + getOtherArg() + "\">末页</A>]&nbsp;";
        String Nav = "";

        if (this.totalRecordCount <= this.currentPageSize) {
            Nav = "";
        }
        else {
            Nav = First + Previous + Next + Last;
        }
        return Nav;
    }

    private String getStatistic() {
        return "页数: " + this.getPageStart() + " / " + this.getTotalPage() + " ; 记录数: " + this.getTotalRecordCount() + "&nbsp;";
    }
   


}
