package com.shixun.open_account.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    
    @GetMapping(value = "/admin/get_securityUnderAdmin", produces = "application/json;charset=UTF-8")
    public JSONArray getSecurity(@RequestParam Integer admin_id)
    {
    	List<Integer> list = adminService.getSecurityIdByAdminId(admin_id);
    	LinkedList<JSONObject> js = new LinkedList<>();
    	for(Integer i:list) {
    		js.add(securityService.getSecurityBySecurityId(i));
    	}
        JSONArray result=new JSONArray();
        result.addAll(js);
        return result;
    }

	@PostMapping(value = "/admin/addAuditor")
	public int addAuditor(@RequestBody JSONObject jsonObject) {
		int auditor_id = auditorService.insertEmployee(
				jsonObject.getString("account"), 
				jsonObject.getString("password"), 
				"2", 
				jsonObject.getString("name")
				);
		return auditorService.insertAuditor(
				jsonObject.getIntValue("security_id"),
				auditor_id
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
