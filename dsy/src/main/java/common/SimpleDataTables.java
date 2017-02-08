package common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import util.StringUtil;


/**
 * 
 * POJO that wraps some necessary and useful parameters sent by dataTables to
 * the server when server-side processing is enabled. This bean can then be used
 * to build SQL queries.
 * 
 * @author fangwk
 * @date 2017-2-5 16:53:00
 */
public class SimpleDataTables implements Serializable {

	private static final long serialVersionUID = -1567869867582282683L;

	/** dataTables 必要参数，原样返回，防御XSS */
	private Integer draw;
	private final static String DT_DRAW = "draw";

	/** 分页开始数 */
	private Integer start;
	private final static String DT_START = "start";

	/** 当前页数量 */
	private Integer length;
	private final static String DT_LENGTH = "length";

	/** 排序字段 ，命名需要和数据库字段保持一致，统一使用别名，驼峰原则 */
	private String sort;
	/** 默认只能对一列排序 */
	private final static String DT_SORT = "order[0][column]";

	/** 排序规则 */
	private String order;
	/** 默认只能对一列排序 */
	private final static String DT_ORDER = "order[0][dir]";

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public SimpleDataTables() {
		super();
	}

	public SimpleDataTables(Integer draw, Integer start, Integer length,
			String sort, String order) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.sort = sort;
		this.order = order;
	}

	public static SimpleDataTables getFromRequest(HttpServletRequest request) {
		String paramDraw = request.getParameter(DT_DRAW);
		String paramStart = request.getParameter(DT_START);
		String paramLength = request.getParameter(DT_LENGTH);
		String paramSortColum = request.getParameter(DT_SORT);
		String order = request.getParameter(DT_ORDER);
		Integer draw = StringUtil.isNotBlank(paramDraw) ? Integer
				.parseInt(paramDraw) : 1;
		Integer start = StringUtil.isNotBlank(paramStart) ? Integer
				.parseInt(paramStart) : 0;
		Integer length = StringUtil.isNotBlank(paramLength) ? Integer
				.parseInt(paramLength) : 10;
		String sort = request.getParameter("columns[" + paramSortColum
				+ "][data]");
		return new SimpleDataTables(draw, start, length, sort, order);
	}

}
