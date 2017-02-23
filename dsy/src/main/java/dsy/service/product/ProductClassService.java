package dsy.service.product;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface ProductClassService {

	public List<JSONObject> productClass(HttpServletRequest request) throws SQLException;
}
