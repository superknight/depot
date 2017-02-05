package dsy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import dsy.dao.BaseJdbcDao;
import dsy.service.MenuService;
import net.sf.json.JSONObject;
import util.StringUtil;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Override
	public List<JSONObject> getMenuByUser(int userid) {
		List<JSONObject> returnList = new ArrayList<JSONObject>();

		String sql = "SELECT * from sec_menu m where m.id in "
				+ " (SELECT DISTINCT(rm.menu_id) "
				+ " FROM sec_user u INNER JOIN sec_user_role ur on u.id=ur.user_id "
				+ " INNER JOIN sec_role_menu rm on ur.role_id=rm.role_id "
				+ " where u.id='"+userid+"' and ur.is_grant<>0) "
				+ " and m.reveal='1' and m.level='1' ORDER BY number";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);

		while (rs.next()) {
			JSONObject ob = new JSONObject();
			String menuid = rs.getString("id");
			ob.put("menuId", menuid);
			ob.put("menuName", rs.getString("menu"));

			String url = rs.getString("url");
			if (StringUtil.isBlank(url)) {
				ob.put("url", "");
			} else {
				ob.put("url", url);
			}
			ob.put("level", rs.getInt("level"));

			String icon = rs.getString("icon");
			if (StringUtil.isBlank(icon)) {
				ob.put("icon", "");
			} else {
				ob.put("icon", icon);
			}

			ob.put("orderNumber", rs.getInt("number"));
			ob.put("visible", rs.getString("reveal"));

			List<JSONObject> list = getTMenuByUser(String.valueOf(userid),
					menuid);
			if (list.size() <= 0) {
				ob.put("menu", "");
			} else {
				ob.put("menu", list);
			}
			returnList.add(ob);
		}

		return returnList;
	}

	@Override
	public List<JSONObject> getTMenuByUser(String userid, String menuid) {
		String sql = "SELECT * from sec_menu m where m.id in "
				+ " (SELECT DISTINCT(rm.menu_id) "
				+ " FROM sec_user u INNER JOIN sec_user_role ur"
				+ " on u.id=ur.user_id INNER JOIN sec_role_menu rm "
				+ " on ur.role_id=rm.role_id where u.id='"+userid
				+ "' and ur.is_grant<>0) "
				+ " and m.reveal='1' and m.parent_id='"+menuid
				+ "' and m.level='2' ORDER BY number";
		SqlRowSet rs = this.baseJdbcDao.execRowset(sql);

		List<JSONObject> returnData = new ArrayList<JSONObject>();

		while (rs.next()) {
			JSONObject ob = new JSONObject();
			ob.put("menuId", rs.getString("id"));
			ob.put("parentMenu", rs.getString("parent_id"));
			ob.put("menuName", rs.getString("menu"));

			String url = rs.getString("url");
			if (StringUtil.isBlank(url)) {
				ob.put("url", "");
			} else {
				ob.put("url", url);
			}
			ob.put("level", rs.getInt("level"));

			String icon = rs.getString("icon");
			if (StringUtil.isBlank(icon)) {
				ob.put("icon", "");
			} else {
				ob.put("icon", icon);
			}

			ob.put("orderNumber", rs.getInt("number"));
			ob.put("visible", rs.getString("reveal"));

			returnData.add(ob);
		}
		return returnData;
	}

}
