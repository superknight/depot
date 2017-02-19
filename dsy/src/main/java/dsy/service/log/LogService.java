package dsy.service.log;

import javax.servlet.http.HttpServletRequest;

import dsy.module.SecLog;
import net.sf.json.JSONObject;

public interface LogService {

	// 保存log到数据库
	public boolean saveToDB(SecLog log);
			
	//获取日志列表
	public JSONObject getLogList(HttpServletRequest request) throws Exception;
			
	//删除日志信息
	public Boolean deleteLog(HttpServletRequest request);
}
