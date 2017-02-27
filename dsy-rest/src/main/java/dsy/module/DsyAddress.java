package dsy.module;

import java.sql.Timestamp;

public class DsyAddress {

	private String id;
	private String address;
	private String phone;
	private String receiver;
	private String remark;
	private Timestamp in_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getIn_time() {
		return in_time;
	}
	public void setIn_time(Timestamp in_time) {
		this.in_time = in_time;
	}
}
