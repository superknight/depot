package util;

import net.sf.json.JSONObject;

public class Response2JSON {

	public static JSONObject toJSON(Object obj,String status){
		JSONObject ob = new JSONObject();
		ob.put("data", obj);
		ob.put("status", status);
		return ob;
	}
}
