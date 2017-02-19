package dsy.service.member.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dsy.dao.BaseJdbcDao;
import dsy.module.DsyAddress;
import dsy.service.member.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public List<DsyAddress> getAddressList(HttpServletRequest request) throws Exception {

		String customerId = request.getParameter("customerId");
		//数据库返回的字段要与前端dataTable的字段和Bean类的字段名字都要相同
		String sql = "select * from dsy_address where customer_id="
		            +customerId+" order by in_time desc";
		
		ResultSet rs = this.baseJdbcDao.execResultSet(sql);
		List<DsyAddress> list = new ArrayList<DsyAddress>();
		
		while(rs.next()){
			DsyAddress address = new DsyAddress();
			address.setId(rs.getString("id"));
			address.setAddress(rs.getString("address"));
			address.setPhone(rs.getString("phone"));
			address.setReceiver(rs.getString("receiver"));
			if(rs.getString("remark") == null){
				address.setRemark("");
			}
			else{
			address.setRemark(rs.getString("remark"));
			}
			address.setIn_time(rs.getTimestamp("in_time"));
			
			list.add(address);
		}
		return list;
		
	}

}
