package dsy.service.member.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.DataTablesResult;
import common.SimpleDataTables;
import dsy.dao.BaseJdbcDao;
import dsy.module.DsyUser;
import dsy.service.member.CustomerService;
import net.sf.json.JSONObject;
import util.PagesBean;
import util.StringUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public JSONObject getCustomerList(HttpServletRequest request) throws Exception {
	
	String countSql = "select count(*) from dsy_user where 1=1 ";
	
	//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
	String fullSql = "select id,customer,accout,`password`,status,email,"
			+ "apply_time as applyTime,last_updator as lastUpdator,"
			+ "last_update_time as lastUpdateTime from dsy_user where 1=1 ";

	StringBuffer appSql = new StringBuffer();
	StringBuffer addSql = new StringBuffer();
	SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
	
	String customer = request.getParameter("extra_search[customer]");
	String logmin = request.getParameter("extra_search[logmin]");
	String logmax = request.getParameter("extra_search[logmax]");
	// for search
	if(StringUtil.isNotBlank(logmax) && StringUtil.isNotBlank(logmin)){
		appSql.append(" and apply_time >= '")
		      .append(logmin)
		      .append("' and apply_time <='")
		      .append(logmax)
		      .append("'");
	}
	if(StringUtil.isNotBlank(customer)){
		appSql.append(" and customer like '%").append(customer).append("%' ");
	}
	
	// for sort and order
	if (StringUtil.isNotBlank(dataTables.getSort())) {
		addSql.append(" order by ").append(dataTables.getSort())
						.append(" ").append(dataTables.getOrder());
		}
	
	fullSql += appSql.toString() + addSql.toString();
	countSql += appSql.toString();
	
	
	PagesBean pageBean = this.baseJdbcDao.JdbcSimplePage(countSql,
			fullSql, dataTables.getStart(), dataTables.getLength());
	ResultSet rs = pageBean.getResultSet();
	
	
	List<DsyUser> list = new ArrayList<DsyUser>();
	while(rs.next()){
		DsyUser user = new DsyUser();
		user.setId(rs.getInt("id"));
		user.setCustomer(rs.getString("customer"));
		user.setAccout(rs.getString("accout"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setStatus(rs.getString("status"));
		user.setApplyTime(rs.getString("applyTime"));
		user.setLastUpdator(rs.getString("lastUpdator"));
		user.setLastUpdateTime(rs.getString("lastUpdateTime"));
		
		list.add(user);
		
	}
	int draw = dataTables.getDraw();
	//结果数
	String recordsTotal = String.valueOf(pageBean.getItemCount());
	//过滤后数目，暂时和结果数保持一致
	String recordsFiltered = recordsTotal;
	return DataTablesResult.DataResult(draw, recordsTotal, recordsFiltered,
			list);
	}

	//冻结
	@Override
	public boolean freezeCustomer(HttpServletRequest request) {
		String idArray = request.getParameter("idArray");
		String sql = "update dsy_user set status = '1' where id in ("+idArray+")";
		return this.baseJdbcDao.executesql(sql);
	}

	//解冻
	@Override
	public boolean unfreezeCustomer(HttpServletRequest request) {
		String idArray = request.getParameter("idArray");
		String sql = "update dsy_user set status = '0' where id in ("+idArray+")";
		return this.baseJdbcDao.executesql(sql);
	}

}
