package dsy.module;

public class SecLog {

	private int id;
	private String descripe;
	private String creater;
	private String createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripe() {
		return descripe;
	}
	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public SecLog(String descripe, String creater, String createTime) {
		this.descripe = descripe;
		this.creater = creater;
		this.createTime = createTime;
	}
	public SecLog() {
		super();
	}
	
	
}
