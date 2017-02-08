package common;


import java.util.List;

import net.sf.json.JSONObject;

public class DataTablesResult {
     
	//dataTables返回的格式
	public static JSONObject DataResult(int draw,String recordsTotal,String recordsFiltered,List<?> list){
		JSONObject returnData=new JSONObject();
		returnData.put("draw", draw);
		returnData.put("recordsTotal", recordsTotal);
		returnData.put("recordsFiltered", recordsFiltered);
		returnData.put("data", list);
		return returnData;
	}
}
