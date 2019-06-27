package com.shixun.open_account.service.ServieImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixun.open_account.dao.AdminDAO;
import com.shixun.open_account.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	AdminDAO adminDAO;
	@Override
	public List<Integer> getSecurityIdByAdminId(Integer admin_id) {
		return adminDAO.getSecurityIdByAdminId(admin_id);
	}

}
