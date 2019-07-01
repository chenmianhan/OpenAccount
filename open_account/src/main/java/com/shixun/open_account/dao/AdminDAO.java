package com.shixun.open_account.dao;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.entity.Employee;

public interface AdminDAO {
	List<JSONObject> getSecurityIdBySuperAdmin();
	Integer getSecurityIdByAdminId(Integer admin_id);
	Integer addAdminEmployee(Employee admin);
	Integer addAdminManage(Employee admin);
	Integer updateAdmin(Employee admin);
}
