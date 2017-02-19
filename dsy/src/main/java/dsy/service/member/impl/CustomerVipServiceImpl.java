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
import dsy.module.DsyCustomerVip;
import dsy.service.member.CustomerVipService;
import net.sf.json.JSONObject;
import util.PagesBean;
import util.StringUtil;

@Service
public class CustomerVipServiceImpl implements CustomerVipService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public JSONObject getCustomerVipList(HttpServletRequest request) throws Exception {
		String countSql = "select count(*) from dsy_customer_vip v,dsy_user u "
				+ " where 1=1 and v.customer_id = u.id ";
		
		//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
		String fullSql = "select v.id,u.customer,v.vip_grade,v.vip_integral,"
				+ " v.`status`,v.apply_time from dsy_customer_vip v,dsy_user u "
				+ " where 1=1 and v.customer_id = u.id ";

		StringBuffer appSql = new StringBuffer();
		StringBuffer addSql = new StringBuffer();
		SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
		
		String customer = request.getParameter("extra_search[customer]");
		String logmin = request.getParameter("extra_search[logmin]");
		String logmax = request.getParameter("extra_search[logmax]");
		String vipGrade = request.getParameter("extra_search[vip_grade]");
		// for search
		if(StringUtil.isNotBlank(logmax) && StringUtil.isNotBlank(logmin)){
			appSql.append(" and v.apply_time >= '")
			      .append(logmin)
			      .append("' and v.apply_time <='")
			      .append(logmax)
			      .append("'");
		}
		if(StringUtil.isNotBlank(customer)){
			appSql.append(" and u.customer like '%").append(customer).append("%' ");
		}
		if(StringUtil.isNotBlank(vipGrade)){
			appSql.append(" and v.vip_grade ='").append(vipGrade).append("'");
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
		
		List<DsyCustomerVip> list = new ArrayList<DsyCustomerVip>();
		while(rs.next()){
			DsyCustomerVip vip = new DsyCustomerVip();
			
			vip.setId(rs.getString("id"));
			vip.setCustomer(rs.getString("customer"));
			vip.setVip_grade(rs.getString("vip_grade"));
			vip.setVip_integral(rs.getString("vip_integral"));
			vip.setStatus(rs.getString("status"));
			vip.setApply_time(rs.getString("apply_time"));
			
			list.add(vip);
		}
		
		int draw = dataTables.getDraw();
		//结果数
		String recordsTotal = String.valueOf(pageBean.getItemCount());
		//过滤后数目，暂时和结果数保持一致
		String recordsFiltered = recordsTotal;
		return DataTablesResult.DataResult(draw, recordsTotal, recordsFiltered,
				list);
	}

}
