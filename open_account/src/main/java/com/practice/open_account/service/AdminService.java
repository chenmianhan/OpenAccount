package com.practice.open_account.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;

public interface AdminService {
	List<JSONObject> getSecurityIdBySuperAdmin();
	Integer getSecurityIdByAdminId(Integer admin_id);
	Integer addAdminEmployee(Employee admin);
	Integer addAdminManage(Employee admin);
	Integer updateAdmin(Employee admin);
	Integer addSecurity(Security security);
	List<AccountInfo> getServeralUserBySecurityId(Integer security_id);
	String getOpenDate(Integer user_id);
	String getPhoneByUserId(Integer user_id);
	Map<Integer, String> getAllOpenDate();
}
