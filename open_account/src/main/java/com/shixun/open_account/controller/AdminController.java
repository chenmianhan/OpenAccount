package com.shixun.open_account.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.shixun.open_account.entity.Employee;
import com.shixun.open_account.service.AdminService;
import com.shixun.open_account.service.AuditorService;
import com.shixun.open_account.service.SecurityService;

@RestController
public class AdminController {
	@Autowired
    private AdminService adminService;
	@Autowired
	private SecurityService securityService;
	@Autowired
    private AuditorService auditorService;
    
//    @GetMapping(value = "/admin/get_securityUnderAdmin", produces = "application/json;charset=UTF-8")
//    public JSONArray getSecurity(@RequestParam Integer admin_id)
//    {
    	//	get Security_id under one specific admin
//    	List<Integer> list = adminService.getSecurityIdByAdminId(admin_id);
//    	ArrayList<JSONObject> js = new ArrayList<>(50);
//    	//	get security details
//    	for(Integer i:list)	js.add(securityService.getSecurityBySecurityId(i));
//    	//	result instance
//    	JSONArray res = new JSONArray(list.size());
//    	
    	
    	
    	//
//    	int index = 0;
//    	while(true) {
//    		if(js.get(index).getString("province")
//    				.equals(js.get(index+1).getString("province"))) {
//    			while(true) {
//    				if(js.get(index).getString("city")
//    						.equals(js.get(index+1).getString("city"))) {
//    					JSONArray citySecurity = new JSONArray();
//    					while(true) {
//    						JSONObject singleSecurity = js.get(index);
//    						
//    					}
//    				}
//    			}
//    		}
//    	}
//    	
//    	JSONArray provinceSecurity = new JSONArray();
//    	
//    	
//    	
//    	
//    	
//    	
//    	
//    	
//    }

	@PostMapping(value = "/admin/addAuditor")
	public int addAuditor(@RequestBody JSONObject jsonObject) {
		Employee employee = new Employee(null, jsonObject.getString("account"), jsonObject.getString("password"), "2", jsonObject.getString("name"));
		auditorService.insertEmployee(employee);
		return auditorService.insertAuditor(
				jsonObject.getIntValue("security_id"),
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
