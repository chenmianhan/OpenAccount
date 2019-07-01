package com.shixun.open_account.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.shixun.open_account.entity.Employee;
import com.shixun.open_account.service.AdminService;
import com.shixun.open_account.service.AuditorService;
import com.shixun.open_account.service.SecurityService;
import com.shixun.open_account.util.constants.LoginConstants;

@RestController
public class AdminController {
	@Autowired
    private AdminService adminService;
	@Autowired
	private SecurityService securityService;
	@Autowired
    private AuditorService auditorService;
    
    @GetMapping(value = "/superadmin/get_securityUnderAdmin", produces = "application/json;charset=UTF-8")
    public JSONArray getSecurityIdBySuperAdmin()
    {
    	//	get Security_id under one specific admin
    	List<JSONObject> list = adminService.getSecurityIdBySuperAdmin();
    	JSONArray array = new JSONArray(list.size());
    	array.addAll(list);
    	return array;
    }

	@PostMapping(value = "/admin/addAuditor")
	public int addAuditor(@RequestBody JSONObject jsonObject) {
		//	insert employee table
		Employee employee = new Employee(null, jsonObject.getString("account"), jsonObject.getString("password"), "2", jsonObject.getString("name"));
		auditorService.insertEmployee(employee);
		//	before insert auditor_manage, get current admin_id
		JSONObject jsonObject2 = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int security_id = adminService.getSecurityIdByAdminId(jsonObject2.getInteger("employee_id"));
		return auditorService.insertAuditor(
				security_id,
				employee.getEmployee_id()
				);
	}
	
	@PutMapping(value = "/admin/modifyAuditor")
	public int updateEmployee(@RequestBody JSONObject jsonObject) {
		return auditorService.updateEmployee(
				jsonObject.getIntValue("auditor_id"),
				jsonObject.getString("account"),
				jsonObject.getString("password"),
				"2",
				jsonObject.getString("name"))&auditorService.updateAuditor(
				jsonObject.getIntValue("security_id"),
				jsonObject.getIntValue("auditor_id"));
	}
}
