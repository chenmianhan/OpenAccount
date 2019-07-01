package com.shixun.open_account.dao;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface AdminDAO {
	List<JSONObject> getSecurityIdBySuperAdmin();
	Integer getSecurityIdByAdminId(Integer admin_id);
}
