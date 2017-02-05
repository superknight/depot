package dsy.service;

import java.util.List;

import net.sf.json.JSONObject;

public interface MenuService {


	/**
     * 根据用户ID获取第一层菜单
     * 
     * @param userid
     * @return
     */
    public List<JSONObject> getMenuByUser(int userid);

    /**
     * 根据用户ID获取第 二层菜单
     * 
     * @param userid
     * @return
     */
    public List<JSONObject> getTMenuByUser(String userid, String menuid);
}
