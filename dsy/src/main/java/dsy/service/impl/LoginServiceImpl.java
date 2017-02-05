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
		String sql="select * from sec_user where USERNAME='"+user.getUsername()+"' and PASSWORD='"
				+user.getPassword();
		ResultSet rs=this.baseJdbcDao.execResultSet(sql);
		try {
			user.setName(rs.getString("NAME"));
			user.setUsername(rs.getString("USERNAME"));
			user.setSex(rs.getString("SEX"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.getName());
		return user;
	}

	
	
}
