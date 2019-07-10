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
//	List<Integer> getAuditorIdBySecurityId(Integer securityId);
	Integer addAdminEmployee(Employee admin) throws Exception;
	Integer addAdminManage(Employee admin);
	Integer updateAdmin(Employee admin);
	Integer addSecurity(Security security);
	List<AccountInfo> getServeralUserBySecurityId(Integer security_id);
	List<Employee> getServeralAuditorBySecurityId(Integer security_id);
	String getOpenDate(Integer user_id);
	String getPhoneByUserId(Integer user_id);
	Map<Integer, String> getAllOpenDate();
	String getAdminNameByAdminId(Integer admin_id);
//	int updateAdminSecurity(Employee employee);
	int deleteReviewerInEmployee(int auditor_id) throws Exception;
	int deleteReviewInAuditorManager(int auditor_id)throws Exception;
}
