package dsy.service.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsy.dao.BaseJdbcDao;
import dsy.module.SecUser;
import dsy.service.LoginService;
import util.StringUtil;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public SecUser LoginSystem(SecUser user) {
		
		if(user == null || StringUtil.isBlank(user.getUsername())){
			return null;
		}
		String sql="select * from sec_user where username='"+user.getUsername()+"' and password='"
				+user.getPassword()+"'";
		ResultSet rs=this.baseJdbcDao.execResultSet(sql);
		
		SecUser secUser=new SecUser();
		try {
			while(rs.next()){
			secUser.setName(rs.getString("name"));
			secUser.setUsername(rs.getString("username"));
			secUser.setSex(rs.getString("sex"));
			secUser.setPassword(rs.getString("password"));
			secUser.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return secUser;
	}

	
	
}
