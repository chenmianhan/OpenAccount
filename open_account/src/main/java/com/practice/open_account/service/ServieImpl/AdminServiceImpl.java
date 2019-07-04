package com.practice.open_account.service.ServieImpl;


import java.util.List;

import com.practice.open_account.dao.AdminDAO;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
    AdminDAO adminDAO;
	@Override
	public List<JSONObject> getSecurityIdBySuperAdmin() {
		return adminDAO.getSecurityIdBySuperAdmin();
	}
	@Override
	public Integer getSecurityIdByAdminId(Integer admin_id) {
		return adminDAO.getSecurityIdByAdminId(admin_id);
	}
	@Override
	public Integer addAdminEmployee(Employee admin) {
		// TODO Auto-generated method stub
		return adminDAO.addAdminEmployee(admin);
	}
	@Override
	public Integer addAdminManage(Employee admin) {
		// TODO Auto-generated method stub
		return adminDAO.addAdminManage(admin);
	}
	@Override
	public Integer updateAdmin(Employee admin) {
		// TODO Auto-generated method stub
		return adminDAO.updateAdmin(admin);
	}
	@Override
	public Integer addSecurity(Security security) {
		// TODO Auto-generated method stub
		return adminDAO.addSecurity(security);
	}
	@Override
	public List<AccountInfo> getServeralUserBySecurityId(Integer security_id) {
		// TODO Auto-generated method stub
		return adminDAO.getServeralUserBySecurityId(security_id);
	}
}
