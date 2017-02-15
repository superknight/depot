package dsy.service.admin.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.DataTablesResult;
import common.SimpleDataTables;
import dsy.dao.BaseJdbcDao;
import dsy.module.SecRole;
import dsy.service.admin.RoleService;
import net.sf.json.JSONObject;
import util.PagesBean;
import util.StringUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public JSONObject getRoleList(HttpServletRequest request) throws Exception {

		String countSql = "select count(*) from sec_role where 1=1";
		
		String fullSql = "select id,role,remark,creator,"
				+ "create_time as createTime,"
				+ "last_update as lastUpdate,"
				+ "last_update_time as lastUpdateTime from sec_role where 1=1";
		

		StringBuffer appSql = new StringBuffer();
		SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
		
		String rolename = request.getParameter("extra_search[role]");
		// for search
		if(StringUtil.isNotBlank(rolename)){
			appSql.append(" and role like '%")
			.append(rolename)
			.append("%' ");
		}
		
		// for sort and order
		if (StringUtil.isNotBlank(dataTables.getSort())) {
			appSql.append(" order by ").append(dataTables.getSort())
							.append(" ").append(dataTables.getOrder());
				}
		fullSql += appSql.toString();
		countSql += appSql.toString();
		
		PagesBean pageBean = this.baseJdbcDao.JdbcSimplePage(countSql,
					fullSql, dataTables.getStart(), dataTables.getLength());
		ResultSet rs = pageBean.getResultSet();
				
		List<SecRole> list = new ArrayList<SecRole>();
		
		while(rs.next()){
			SecRole secRole = new SecRole();
			
			secRole.setId(rs.getString("id"));
			secRole.setRole(rs.getString("role"));
			secRole.setRemark(rs.getString("remark"));
			secRole.setCreator(rs.getString("creator"));
			secRole.setCreateTime(rs.getString("createTime"));
			secRole.setLastUpdate(rs.getString("lastUpdate"));
			secRole.setLastUpdateTime(rs.getString("lastUpdateTime"));
			
			System.out.println(secRole.getRole());
			list.add(secRole);
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
