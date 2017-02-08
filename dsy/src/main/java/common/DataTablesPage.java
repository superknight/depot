package common;


import javax.servlet.http.HttpServletRequest;

import util.StringUtil;


/**
 * DataTables的分页
 * @author Administrator
 *
 */
public class DataTablesPage {
	
    private static int draw;     //绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
    private static int pageSize;     //分页
    private static int startRecord;  //开始位置
    private static String sortOrder;   //返回的需要排序是第几列
    private static String sortDir;     //返回排序的方式 asc desc
	
	public static void getParameter(HttpServletRequest request){
		setDraw(Integer.valueOf(request.getParameter("draw")));  //draw
		setPageSize(10);
		setStartRecord(0);
		String size = request.getParameter("length"); //页数
		if (StringUtil.isNotBlank(size)) {
			setPageSize(Integer.parseInt(size)); 
		}
		String currentRecord = request.getParameter("start"); //开始位置
		if (StringUtil.isNotBlank(currentRecord)) {
			setStartRecord(Integer.parseInt(currentRecord));    
		}
		
		setSortOrder(request.getParameter("order[0][column]"));
		setSortDir(request.getParameter("order[0][dir]"));
	}

	/**
	 * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
	 * @return
	 */
	
	public int getDraw() {
		return draw;
	}

	/**
	 * 页数
	 * @return
	 */
	
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 开始的位置
	 * @return
	 */
	public int getStartRecord() {
		return startRecord;
	}

	/**
	 * 需要排序是第几列
	 * @return
	 */
	public String getSortOrder() {
		return sortOrder;
	}
    /**
     * 排序的方式 asc desc
     * @return
     */
	public String getSortDir() {
		return sortDir;
	}

	public static void setDraw(int draw) {
		DataTablesPage.draw = draw;
	}

	public static void setPageSize(int pageSize) {
		DataTablesPage.pageSize = pageSize;
	}

	public static void setStartRecord(int startRecord) {
		DataTablesPage.startRecord = startRecord;
	}

	public static void setSortOrder(String sortOrder) {
		DataTablesPage.sortOrder = sortOrder;
	}

	public static void setSortDir(String sortDir) {
		DataTablesPage.sortDir = sortDir;
	}

	
     
}
