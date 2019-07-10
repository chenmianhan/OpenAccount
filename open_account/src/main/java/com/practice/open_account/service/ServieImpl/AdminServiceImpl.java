package com.practice.open_account.service.ServieImpl;


import java.util.List;
import java.util.Map;

import com.practice.open_account.dao.AdminDAO;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
    AdminDAO adminDAO;
	@Override
	@Transactional
	public List<JSONObject> getSecurityIdBySuperAdmin() {
		return adminDAO.getSecurityIdBySuperAdmin();
	}
	@Override
	@Transactional
	public Integer getSecurityIdByAdminId(Integer admin_id) {
		return adminDAO.getSecurityIdByAdminId(admin_id);
	}
	@Override
	@Transactional
	public Integer addAdminEmployee(Employee admin) throws Exception {
		// TODO Auto-generated method stub
		try {
			adminDAO.addAdminEmployee(admin);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("dupliate admin account");
		}
		return 0;
	}
	@Override
	@Transactional
	public Integer addAdminManage(Employee admin) {
		// TODO Auto-generated method stub
		admin.setEmployee_id(adminDAO.getMaxEmployeeId());
		return adminDAO.addAdminManage(admin);
	}
	@Override
	@Transactional
	public Integer updateAdmin(Employee admin) {
		// TODO Auto-generated method stub
		return adminDAO.updateAdmin(admin);
	}
	@Override
	@Transactional
	public Integer addSecurity(Security security) {
		// TODO Auto-generated method stub
		return adminDAO.addSecurity(security);
	}
	@Override
	@Transactional
	public List<AccountInfo> getServeralUserBySecurityId(Integer security_id) {
		// TODO Auto-generated method stub
		return adminDAO.getServeralUserBySecurityId(security_id);
	}
	@Override
	@Transactional
	public String getOpenDate(Integer user_id) {
		// TODO Auto-generated method stub
		return adminDAO.getOpenDate(user_id);
	}
	@Override
	@Transactional
	public String getPhoneByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return adminDAO.getPhoneByUserId(user_id);
	}
	@Override
	@Transactional
	public Map<Integer, String> getAllOpenDate() {
		// TODO Auto-generated method stub
		return adminDAO.getAllOpenDate();
	}
	@Override
	@Transactional
	public String getAdminNameByAdminId(Integer admin_id) {
		// TODO Auto-generated method stub
		return adminDAO.getAdminNameByAdminId(admin_id);
	}
	@Override
	@Transactional
	public List<Employee> getServeralAuditorBySecurityId(Integer security_id) {
		// TODO Auto-generated method stub
		return adminDAO.getServeralAuditorBySecurityId(security_id);
	}
//	@Override
//	public List<Integer> getAuditorIdBySecurityId(Integer SecurityId) {
//		// TODO Auto-generated method stub
//		return adminDAO.getAuditorIdBySecurityId(SecurityId);
//	}
	@Override
	@Transactional
	public int deleteReviewerInEmployee(int auditor_id) {
		// TODO Auto-generated method stub
		try {
			adminDAO.deleteReviewerInEmployee(auditor_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	@Override
	@Transactional
	public int deleteReviewInAuditorManager(int auditor_id) {
		// TODO Auto-generated method stub
		try {
			adminDAO.deleteReviewInAuditorManager(auditor_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
}
