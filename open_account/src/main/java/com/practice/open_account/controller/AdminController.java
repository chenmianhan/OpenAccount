package com.practice.open_account.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;
import com.practice.open_account.service.AdminService;
import com.practice.open_account.service.AuditorService;
import com.practice.open_account.service.SecurityService;
import com.practice.open_account.util.constants.LoginConstants;

@RestController
public class AdminController {
	@Autowired
    private AdminService adminService;
	@Autowired
	private SecurityService securityService;
	@Autowired
    private AuditorService auditorService;
    
	//	super admin
	//	getAllSecurity
    @GetMapping(value = "/superadmin/get_securityUnderAdmin", produces = "application/json;charset=UTF-8")
    public JSONArray getSecurityIdBySuperAdmin()
    {
    	//	get Security_id under one specific admin
    	List<JSONObject> list = adminService.getSecurityIdBySuperAdmin();
    	JSONArray array = new JSONArray(list.size());
    	array.addAll(list);
    	return array;
    }
    
    //	addAdmin
    @PostMapping(value = "/superadmin/addAdmin", produces = "application/json;charset=UTF-8")
    public int addAdmin(@RequestBody JSONObject jsonObject) {
    	Employee admin = new Employee(null, 
    			jsonObject.getString("name"), 
    			jsonObject.getString("account"), 
    			jsonObject.getString("password"),
    			"2",
    			jsonObject.getInteger("store"));
    	
    	return adminService.addAdminEmployee(admin)&adminService.addAdminManage(admin);
    }
    
    //	modifyAdmin
    @PostMapping(value = "/superadmin/modifyAdmin",produces = "application/json;charset=UTF-8")
    public int updateAdmin(@RequestBody JSONObject jsonObject) {
    	Employee admin = new Employee(
    			jsonObject.getInteger("admin_id"),
    			jsonObject.getString("name"),
    			jsonObject.getString("account"),
    			jsonObject.getString("password"),
    			null,
    			null);
    	return adminService.updateAdmin(admin);	
    }
    
    //	addSecurity
    @PostMapping(value = "/superadmin/addStore",produces = "application/json;charset=UTF-8")
    public int addSecurity(@RequestBody JSONObject jsonObject) {
    	Security security = new Security(
				null,
				jsonObject.getString("store"), 
				jsonObject.getJSONArray("address").getString(0),
				jsonObject.getJSONArray("address").getString(1),
				jsonObject.getString("contact_phone"));
    	return adminService.addSecurity(security);
    }
    
    //	normal admin
	@PostMapping(value = "/admin/addAuditor",produces = "application/json;charset=UTF-8")
	public int addAuditor(@RequestBody JSONObject jsonObject) {
		//	insert employee table
		Employee employee = new Employee(null, jsonObject.getString("name"),jsonObject.getString("account"), jsonObject.getString("password"), "2",null);
		auditorService.insertEmployee(employee);
		//	before insert auditor_manage, get current admin_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int security_id = adminService.getSecurityIdByAdminId(sessonJsonObject.getInteger("employee_id"));
		return auditorService.insertAuditor(
				security_id,
				employee.getEmployee_id()
				);
	}
	
	@PutMapping(value = "/admin/modifyAuditor")
	public int updateAuditor(@RequestBody JSONObject jsonObject) {
		return auditorService.updateEmployee(
				jsonObject.getIntValue("auditor_id"),
				jsonObject.getString("account"),
				jsonObject.getString("password"),
				"2",
				jsonObject.getString("name"));
//				&auditorService.updateAuditor(
//				jsonObject.getIntValue("security_id"),
//				jsonObject.getIntValue("auditor_id"));
	}
}
