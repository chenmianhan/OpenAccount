package com.practice.open_account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Address;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.entity.Security;
import com.practice.open_account.service.AccountInfoService;
import com.practice.open_account.service.AdminService;
import com.practice.open_account.service.AuditorService;
import com.practice.open_account.service.SecurityService;
import com.practice.open_account.util.constants.LoginConstants;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

@RestController
public class AdminController {
	@Autowired
    private AdminService adminService;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
    private AuditorService auditorService;
	@Autowired
	private SecurityService securityService;
    
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
    @PutMapping(value = "/superadmin/modifyAdmin",produces = "application/json;charset=UTF-8")
    public int updateAdmin(@RequestBody JSONObject jsonObject) {
    	Employee admin = new Employee(
    			jsonObject.getInteger("admin_id"),
    			jsonObject.getString("name"),
    			jsonObject.getString("account"),
    			jsonObject.getString("password"),
    			"2",
    			jsonObject.getInteger("store"));
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
    //	addAuditor
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
	
	//	modifyAuditor
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
	
	//	getuserByDate
	@PostMapping(value = "/admin/getUserByDate",produces = "application/json;charset=UTF-8")
	public JSONArray getUserByDate(
			@RequestBody JSONObject jsonObject
			) throws ParseException {
		String start = jsonObject.getString("start");
		String end = jsonObject.getString("end");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = format.parse(start);
		Date endDate = format.parse(end);
		//	get admin_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
//		Map<Integer,String> allOpenDate = (Map<Integer, String>) adminService.getAllOpenDate();
//		System.out.println(sessonJsonObject);
		int admin_id = sessonJsonObject.getIntValue("employee_id");
		//	get specific security_id
		int security_id = adminService.getSecurityIdByAdminId(admin_id);
		//	get users in specific security
		List<AccountInfo> userInSpecificSecurity = adminService.getServeralUserBySecurityId(security_id);
		JSONArray tableData = new JSONArray(userInSpecificSecurity.size()/2);
		int index = 0;
		for(AccountInfo i:userInSpecificSecurity) {
			//	get openDate
			String openDate = adminService.getOpenDate(i.getUser_id());
			if(openDate==null) continue;
			Date compare = format.parse(openDate);
			System.out.println(compare);
			if(compare.compareTo(startDate)>=0&&compare.compareTo(endDate)<=0) {
				JSONObject elementInArray = new JSONObject();
				//	get user_id,name,id_num,date
				elementInArray.put("user_id", i.getUser_id());
				elementInArray.put("name",i.getName());
				elementInArray.put("id_num",i.getId_number());
				elementInArray.put("date",openDate);
				//	get contact_address
				Address temp = accountInfoService.getAddressByAId(i.getContact_address_id());
				StringBuffer address = new StringBuffer();
				address.append(temp.getProvince()+" ");
				address.append(temp.getCity()+" ");
				address.append(temp.getStreet()+" ");
				address.append(temp.getDetail()+" ");
				elementInArray.put("address", address.toString());
				elementInArray.put("contact",adminService.getPhoneByUserId(i.getUser_id()));
				tableData.add(elementInArray);
			}
		}
		return tableData;
	}
	
	//	getUserByName
	@PostMapping(value = "/admin/getUserByName",produces = "application/json;charset=UTF-8")
	public JSONArray getUserByName(@RequestBody JSONObject jsonObject) {
		String searchName = jsonObject.getString("username");
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int admin_id = sessonJsonObject.getIntValue("employee_id");
		//	get specific security_id
		int security_id = adminService.getSecurityIdByAdminId(admin_id);
		//	get users in specific security
		List<AccountInfo> userInSpecificSecurity = adminService.getServeralUserBySecurityId(security_id);
		JSONArray tableData = new JSONArray(userInSpecificSecurity.size()/2);
		for(AccountInfo i:userInSpecificSecurity) {
			if(i.getName()!=null&&i.getName().equals(searchName)) {
				JSONObject elementInArray = new JSONObject();
				//	get user_id,name,id_num
				elementInArray.put("user_id", i.getUser_id());
				elementInArray.put("name",i.getName());
				elementInArray.put("id_num",i.getId_number());
				//	get contact_address
				Address temp = accountInfoService.getAddressByAId(i.getContact_address_id());
				StringBuffer address = new StringBuffer();
				address.append(temp.getProvince()+" ");
				address.append(temp.getCity()+" ");
				address.append(temp.getStreet()+" ");
				address.append(temp.getDetail()+" ");
				elementInArray.put("address", address.toString());
				elementInArray.put("contact",adminService.getPhoneByUserId(i.getUser_id()));
				tableData.add(elementInArray);
			}
		}
		return tableData;
	}
	
	//	admin
	@GetMapping(value = "/admin",produces = "application/json;charset=UTF-8")
	public JSONObject getAdmin() {
		// res
		JSONObject res = new JSONObject(2);
		//	getAdmin_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int admin_id = sessonJsonObject.getIntValue("employee_id");
		//	get specific security_id by admin_id
		int security_id = adminService.getSecurityIdByAdminId(admin_id);
		//	get securityName by security_id
		String name = securityService.getSecurityBySecurityId(security_id).getString("name");
		res.put("netName", name);
		String admin_name = adminService.getAdminNameByAdminId(admin_id);
		res.put("adminName", admin_name);
		return res;
	}
//	@GetMapping(value)
}
