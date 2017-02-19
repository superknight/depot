package dsy.service.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dsy.module.DsyAddress;

public interface AddressService {

	//获取地址列表
	public List<DsyAddress> getAddressList(HttpServletRequest request) throws Exception;
}
