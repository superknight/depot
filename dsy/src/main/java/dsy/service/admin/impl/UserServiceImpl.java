package dsy.service.admin.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
					+ "(select ur.user_id,r.role from sec_user_role ur,sec_role r where ur.role_id=r.id) "
					+ " urr on u.id=urr.user_id where 1=1 ";
		
		//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
		String fullSql = "select u.id,u.username,u.`password`,"
				+ " u.`name`,u.sex,urr.role,u.phone,u.email,"
				+ " u.address,u.`status`,u.creator,u.create_time as createTime,"
				+ " u.last_update as lastUpdate,u.last_update_time as lastUpdateTime,"
				+ " u.remark from sec_user u left join "
				+ " (select ur.user_id,r.role from sec_user_role ur,sec_role r where ur.role_id=r.id) "
				+ " urr on u.id=urr.user_id where 1=1 ";
		

		StringBuffer appSql = new StringBuffer();
		StringBuffer addSql = new StringBuffer();
		SimpleDataTables dataTables = SimpleDataTables.getFromRequest(request); // 从前端获取的数据
		
		String name = request.getParameter("extra_search[username]");
		// for search
		if(StringUtil.isNotBlank(name)){
			appSql.append(" and u.name like '%").append(name).append("%' ");
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

	@Override
	public List<JSONObject> getRoleName() {
		String sql = "select * from sec_role";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		List<JSONObject> list = new ArrayList<JSONObject>();
		while (rs.next()) {
			JSONObject returnData = new JSONObject();
			returnData.put("roleId", rs.getString("id"));
			returnData.put("roleName", rs.getString("role"));

			list.add(returnData);
		}
		return list;
	}

	@Override
	public JSONObject getUserRole(String userid) {
		String sql = "select * from sec_user_role where user_id=" + userid;
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);

		JSONObject returnData = new JSONObject();
		while (rs.next()) {
			returnData.put("userid", rs.getString("user_id"));
			returnData.put("roleid", rs.getString("role_id"));
		}
		return returnData;
	}

	@Override
	public Boolean saveUserRole(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String roleid = request.getParameter("roleid");
		String delSql = "delete from sec_user_role where user_id=" + userid;
		try {
			this.baseJdbcDao.executesql(delSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "insert into sec_user_role (user_id,role_id,is_grant) values("
				+ userid + "," + roleid + ",'1')";
		return this.baseJdbcDao.executesql(sql);
	}

	//新增与修改
	@Override
	public JSONObject saveAndEditUser(SecUser user,HttpServletRequest request) throws Exception {
		String sql = "";
		JSONObject returnData = new JSONObject();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //格式化当前系统日期
		//获取创建人
		SecUser creat = (SecUser) request.getSession().getAttribute("user");
		String creator = creat.getUsername();
		//新增
		if(StringUtil.isBlank(user.getId())){
			sql = "insert into sec_user (username,password,name,sex,phone,email,address,status,creator,create_time) "
					+ " values ('"+user.getUsername()
					+"','"+user.getPassword()
					+"','"+user.getName()
					+"','"+user.getSex()
					+"','"+user.getPhone()
					+"','"+user.getEmail()
					+"','"+user.getAddress()
					+"','"+user.getStatus()
					+"','"+creator
					+"','"+df.format(new Date())
					+"')";
			
			if (this.baseJdbcDao.executesql(sql)) {
				returnData.put("msg", "000");
			} else {
				returnData.put("msg", "001");
			   }
		}
		//修改
		else{
			sql = "update sec_user "
			          + " set username='" + user.getUsername()
			          + "',password='"+ user.getPassword() 
			          + "',name='" + user.getName() 
			          + "',sex='" + user.getSex()
					  + "',phone='" + user.getPhone() 
					  + "',email='" + user.getEmail()
					  + "',address='" + user.getAddress()
					  + "',status='" + user.getStatus()
					  + "',phone='" + user.getPhone() 
					  + "',last_update='" + creator
					  + "',last_update_time='" + df.format(new Date())
					  + "' where id=" + user.getId();
				if (this.baseJdbcDao.executesql(sql)) {

					returnData.put("msg", "100");
				} else {
					returnData.put("msg", "101");
				}
				return returnData;
		}
		return returnData;
	}

	@Override
	public List<SecUser> getSingleUser(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String sql = "select * from sec_user where id='" + userid + "'";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);

		List<SecUser> list = new ArrayList<SecUser>();
		while (rs.next()) {
			SecUser user = new SecUser();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getString("sex"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			user.setStatus(rs.getString("status"));
			
			list.add(user);
		}

		return list;
	}

	@Override
	public JSONObject deleteUser(HttpServletRequest request) {
		String idArray = request.getParameter("idArray");
		String checksql = "select count(*) as count from sec_user where id in ("+idArray+") and id <> 1";
		JSONObject ret = new JSONObject();
		SqlRowSet rs = this.baseJdbcDao.execRowset(checksql);
		int num = 0;
		while(rs.next()){
			num = rs.getInt("count");
		}
		//检查删除的用户是否包含超级用户
		if(num > 0){
		String delsql = "delete from sec_user where id in (" + idArray
				+ ")";
		   if(this.baseJdbcDao.executesql(delsql)){
			   ret.put("status", "000"); //删除成功
		   }
		   else{
			   ret.put("status", "001"); //删除失败
		   }
		}
		else{
			ret.put("status", "002"); //超级管理员不能被删除
		}
		return ret;
	}
	
}
