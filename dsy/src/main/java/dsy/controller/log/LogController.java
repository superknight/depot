package dsy.controller.log;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dsy.service.log.LogService;
import net.sf.json.JSONObject;
import util.Response2JSON;

@Controller
public class LogController {

	@Autowired
	private LogService lService;
	
	@RequestMapping("log.html")
	public String log(){
		return "jsp/log/log";
	}
	
	 //获取日志列表
    @RequestMapping("log/getLogList.html")
    @ResponseBody
    public JSONObject getLogList(HttpServletRequest request){
    	JSONObject data = null;
    	try {
			data = lService.getLogList(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return data;
    }
    
  //删除日志信息
    @RequestMapping("log/deleteLog.html")
    @ResponseBody
    public JSONObject deleteLog(HttpServletRequest request){
    	if(lService.deleteLog(request)){
    		return Response2JSON.toJSON("删除成功！", "000");
    	}
    	else{
    		return Response2JSON.toJSON("删除失败！", "001");
    	}
    }
}
