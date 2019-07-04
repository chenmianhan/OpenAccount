package com.practice.open_account.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;

public interface AdminDAO {
	List<JSONObject> getSecurityIdBySuperAdmin();
	Integer getSecurityIdByAdminId(Integer admin_id);
	Integer addAdminEmployee(Employee admin);
	Integer addAdminManage(Employee admin);
	Integer updateAdmin(Employee admin);
	Integer addSecurity(Security security);
}
