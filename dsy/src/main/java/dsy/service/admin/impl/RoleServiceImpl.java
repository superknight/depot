package dsy.service.admin.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import common.DataTablesResult;
import common.SimpleDataTables;
import dsy.dao.BaseJdbcDao;
import dsy.module.SecRole;
import dsy.module.SecUser;
import dsy.service.admin.RoleService;
import net.sf.json.JSONObject;
import util.DateUtil;
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

	@Override
	public JSONObject getSingleRole(HttpServletRequest request) {
		String roleid=request.getParameter("roleid");
		String sql="select * from sec_role where id='"+roleid+"'";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		JSONObject returnData=new JSONObject();
		while(rs.next()){
			returnData.put("roleid", rs.getString("id"));
			returnData.put("rolename", rs.getString("role"));
			returnData.put("remark", rs.getString("remark"));
		}
		return returnData;
	}

	@Override
	public JSONObject saveAndEditRole(HttpServletRequest request) throws Exception {
		String roleId=request.getParameter("roleid");
		String rolename=request.getParameter("rolename");
		String remark=request.getParameter("remark");
		
		//获取创建人
		SecUser creat = (SecUser) request.getSession().getAttribute("user");
		String creator = creat.getUsername();
		String sql="";
		JSONObject returnData=new JSONObject();
		if(StringUtil.isBlank(roleId)){
			if(StringUtil.isNotBlank(rolename)&&StringUtil.isNotBlank(remark)){
			sql="insert into sec_role (role,remark,creator,create_time) " +
					"values ('"+rolename+"','"+remark+"','"+creator+"','"+DateUtil.Now()+"')";
			}
			if(checkRole(rolename)){
				returnData.put("status", "003");
			}
			else{
				 if(this.baseJdbcDao.executesql(sql)){
	            	 returnData.put("status", "000");
	             }
	             else{
	            	 returnData.put("status", "001");
	             }
			}
             return returnData;
		
		}
		
		else{
			    sql="update sec_role set role='"+rolename
		            +"',remark='"+remark+"',last_update='"
			    		+creator+"',last_update_time='"+DateUtil.Now()+"' where id="+roleId;
			    if(this.baseJdbcDao.executesql(sql)){
	            	 
	            	 returnData.put("status", "100");
	             }
	             else{
	            	 returnData.put("status", "101");
	             }
			    return returnData;
		    }	
	}

	@Override
	public JSONObject deleteRole(HttpServletRequest request) {
		String idArray = request.getParameter("idArray");
		String[] str = idArray.split(",");
		String checksql = "select count(*) as count from sec_role where id in ("+idArray+") and id <> 1";
		JSONObject ret = new JSONObject();
		SqlRowSet rs = this.baseJdbcDao.execRowset(checksql);
		int num = 0;
		while(rs.next()){
			num = rs.getInt("count");
		}
		//检查删除的角色是否包含超级管理员
		System.out.println("num:"+num+",str="+str.length);
		if(num == str.length){
		String delsql = "delete from sec_role where id in (" + idArray
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

	@Override
	public Boolean checkRole(String role) {
		String sql=" select count(*) as count from sec_role where role='"+role+"'";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		int num = 0;
		while(rs.next()){
			num=rs.getInt("count");
		}
		if(num > 0){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<JSONObject> getMenuFList(HttpServletRequest request) {
		String roleid=request.getParameter("roleid");
		String sql="select * from sec_menu where parent_id='0' and 1=1 ORDER BY number asc";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		List<JSONObject> list=new ArrayList<JSONObject>();
		while(rs.next()){
			JSONObject ob=new JSONObject();
			ob.put("menu_id", rs.getString("id"));
			ob.put("menu_name", rs.getString("menu"));
			ob.put("icon", rs.getString("icon"));
			ob.put("menus", getMenuTList(rs.getString("id")));
			list.add(ob);
		}
		list.add(getRoleMenuList(roleid));
		return list;
	}

	@Override
	public List<JSONObject> getMenuTList(String parentid) {
		String sql="select * from sec_menu where parent_id='"+parentid
				+"' and 1=1 ORDER BY number asc";
				List<JSONObject> list=new ArrayList<JSONObject>();
				SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
				while(rs.next()){
					JSONObject ob=new JSONObject();
					ob.put("menuid", rs.getString("id"));
					ob.put("menuname", rs.getString("menu"));
					list.add(ob);
				}
				return list;
	}

	@Override
	public JSONObject getRoleMenuList(String roleid) {
		String sql="select * from sec_role_menu where role_id="+roleid+" and 1=1";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);
		JSONObject returnData=new JSONObject();
		List<JSONObject> list=new ArrayList<JSONObject>();
		while(rs.next()){
			JSONObject ob=new JSONObject();
			ob.put("id", rs.getString("menu_id"));
			list.add(ob);
		}	
		if(list.size()>0)
			returnData.put("RMid", list);
		else
			returnData.put("RMid", "");
		return returnData;
	}

	@Override
	public Boolean saveAuthorize(HttpServletRequest request) {
		String roleid=request.getParameter("roleid");
		//删除角色拥有的权限
		String delSql="delete from sec_role_menu where role_id="+roleid;
		try {
			this.baseJdbcDao.executesql(delSql); 
		} catch (Exception e) {
		e.printStackTrace();
		}
		String menuid=request.getParameter("menuid");
		String[] idArray=menuid.split(",");
		String str="";
		boolean init=true;
		for(int i=0;i<idArray.length;i++){
			if(init){
			str+="("+roleid+","+idArray[i]+")";
			init=false;
			}
			str+=",("+roleid+","+idArray[i]+")";
		}
		System.out.println(str);
		//从新给角色赋予权限
		String sql="insert into sec_role_menu (role_id,menu_id) values "+str;
		return this.baseJdbcDao.executesql(sql);
	}

}
