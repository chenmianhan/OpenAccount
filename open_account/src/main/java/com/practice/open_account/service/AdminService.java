package com.practice.open_account.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.Employee;

public interface AdminService {
	List<JSONObject> getSecurityIdBySuperAdmin();
	Integer getSecurityIdByAdminId(Integer admin_id);
	Integer addAdminEmployee(Employee admin);
	Integer addAdminManage(Employee admin);
	Integer updateAdmin(Employee admin);
}
