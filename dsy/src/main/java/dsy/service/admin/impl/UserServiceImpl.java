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
import dsy.module.SecUser;
import dsy.service.admin.UserService;
import net.sf.json.JSONObject;
import util.PagesBean;
import util.StringUtil;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public JSONObject getUserList(HttpServletRequest request) throws Exception {

		String countSql = "select count(*) from sec_user u left join "
				+ "(select r.id,r.role from sec_user_role ur,sec_role r where ur.role_id=r.id) "
				+ " urr on u.id=urr.id where 1=1";
		
		String fullSql = "select u.id,u.username,u.`password`,"
				+ " u.`name`,u.sex,urr.role,u.phone,u.email,"
				+ " u.address,u.`status`,u.creator,u.create_time as createTime,"
				+ " u.last_update as lastUpdate,u.last_update_time as lastUpdateTime,"
				+ " u.remark from sec_user u left join "
				+ " (select r.id,r.role from sec_user_role ur,sec_role r where ur.role_id=r.id) "
				+ " urr on u.id=urr.id where 1=1";
		

		StringBuffer appSql = new StringBuffer();
		SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
		
		// for sort and order
		if (StringUtil.isNotBlank(dataTables.getSort())) {
				appSql.append(" order by ").append(dataTables.getSort())
							.append(" ").append(dataTables.getOrder());
			}
		fullSql += appSql.toString();
		
		PagesBean pageBean = this.baseJdbcDao.JdbcSimplePage(countSql,
				fullSql, dataTables.getStart(), dataTables.getLength());
		ResultSet rs = pageBean.getResultSet();
		
		List<SecUser> list = new ArrayList<SecUser>();
		
		while(rs.next()){
			SecUser user = new SecUser();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getString("sex"));
			user.setRole(rs.getString("role"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			user.setStatus(rs.getString("status"));
			user.setCreator(rs.getString("creator"));
			user.setCreateTime(rs.getString("createTime"));
			user.setLastUpdate(rs.getString("lastUpdate"));
			user.setLastUpdateTime(rs.getString("lastUpdateTime"));
			user.setRemark(rs.getString("remark"));
			
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

	
}
