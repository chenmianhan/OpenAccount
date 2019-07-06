package com.practice.open_account.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;

public interface AdminDAO {
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
	String getAdminNameByAdminId(Integer admin_id);
	Integer getMaxEmployeeId();
//	int updateAdminSecurity(Employee employee);
}
