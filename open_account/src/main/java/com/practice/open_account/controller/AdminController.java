package com.practice.open_account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.practice.open_account.util.PasswordUtil;
import com.practice.open_account.util.constants.LoginConstants;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

@RestController
public class AdminController {
	@Autowired
	private ValueOperations<Object, Object> redisOperations;
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
    public ResponseEntity<Integer> addAdmin(@RequestBody JSONObject jsonObject) {
    	String password = PasswordUtil.getMD5(jsonObject.getString("password")+jsonObject.getString("account"));
    	Employee admin = new Employee(null, 
    			jsonObject.getString("name"), 
    			jsonObject.getString("account"), 
    			password,
    			"2",
    			jsonObject.getInteger("store"));
    	
    	try {
    		adminService.addAdminEmployee(admin);
    		adminService.addAdminManage(admin);
    		return ResponseEntity.status(200).body(new Integer(1));
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return ResponseEntity.status(409).body(new Integer(0));
    }
    
    //	modifyAdmin
    @PutMapping(value = "/superadmin/modifyAdmin",produces = "application/json;charset=UTF-8")
    public int updateAdmin(@RequestBody JSONObject jsonObject) {
    	String password = PasswordUtil.getMD5(jsonObject.getString("password")+jsonObject.getString("account"));
    	Employee admin = new Employee(
    			jsonObject.getInteger("admin_id"),
    			jsonObject.getString("name"),
    			jsonObject.getString("account"),
    			password,
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
				jsonObject.getString("phone"));
    	return adminService.addSecurity(security);
    }
    
    //	normal admin
    //	addAuditor
	@PostMapping(value = "/admin/addAuditor",produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> addAuditor(@RequestBody JSONObject jsonObject) {
		String password = PasswordUtil.getMD5(jsonObject.getString("password")+jsonObject.getString("account"));
		//	insert employee table
		Employee employee = new Employee(null, 
				jsonObject.getString("name"),
				jsonObject.getString("account"), 
				password, 
				"1",null);
		try {
			auditorService.insertEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(409).body(new Integer(0));
		}
		
		//	before insert auditor_manage, get current admin_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int security_id = adminService.getSecurityIdByAdminId(sessonJsonObject.getInteger("employee_id"));
		auditorService.insertAuditor(security_id,employee.getEmployee_id());
		return ResponseEntity.status(200).body(new Integer(1));
	}
	
	//	modifyAuditor
	@PutMapping(value = "/admin/modifyAuditor")
	public int updateAuditor(@RequestBody JSONObject jsonObject) {
		String password = PasswordUtil.getMD5(jsonObject.getString("password")+jsonObject.getString("account"));
		return auditorService.updateEmployee(
				jsonObject.getIntValue("auditor_id"),
				jsonObject.getString("account"),
				password,
				"1",
				jsonObject.getString("name"));
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
		int admin_id = sessonJsonObject.getIntValue("employee_id");
		//	get specific security_id
		int security_id = adminService.getSecurityIdByAdminId(admin_id);
		//	get users in specific security and users already open account
		List<AccountInfo> userInSpecificSecurity = adminService.getServeralUserBySecurityId(security_id);
		JSONArray tableData = new JSONArray(userInSpecificSecurity.size()/2);
		for(AccountInfo i:userInSpecificSecurity) {
			//	get openDate
			String openDate = null;
			if( (openDate=(String)redisOperations.get("user_id_openDate"+i.getUser_id()))==null ) {
				openDate = adminService.getOpenDate(i.getUser_id());
				if(openDate==null) continue;
				redisOperations.set("user_id_openDate"+i.getUser_id(), openDate, Duration.ofDays(1l));
			}
			Date compare = format.parse(openDate);
//			System.out.println(compare);
			if(compare.compareTo(startDate)>=0&&compare.compareTo(endDate)<=0) {
				JSONObject elementInArray = new JSONObject();
				//	get user_id,name,id_num,date
				elementInArray.put("user_id", i.getUser_id());
				elementInArray.put("name",i.getName());
				elementInArray.put("id_num",i.getId_number());
				elementInArray.put("date",openDate);
				//	get contact_address
				String address = null;
				if( (address=(String)redisOperations.get("aid:"+i.getContact_address_id()))==null ) {
					Address temp = accountInfoService.getAddressByAId(i.getContact_address_id());
					StringBuffer addressSb = new StringBuffer();
					addressSb.append(temp.getProvince()+" ");
					addressSb.append(temp.getCity()+" ");
					addressSb.append(temp.getStreet()+" ");
					addressSb.append(temp.getDetail()+" ");
					address = addressSb.toString();
					redisOperations.set("aid:"+i.getContact_address_id(), address, Duration.ofHours(24l));
				}
				elementInArray.put("address", address);
				String phone = null;
				if( (phone=(String)redisOperations.get("user_id:"+i.getUser_id()))==null ) {
					phone = adminService.getPhoneByUserId(i.getUser_id());
					redisOperations.set("user_id:"+i.getUser_id(), phone, Duration.ofHours(24l));
				}
				elementInArray.put("contact",phone);
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
	
	@PostMapping(value = "/admin/getReviewerByName",produces = "application/json;charset=UTF-8")
	public JSONArray getReviewerByName(@RequestBody JSONObject jsonObject) {
		//	get reviewerName
		String searchName = jsonObject.getString("reviewerName");
		//	get admin_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int admin_id = sessonJsonObject.getIntValue("employee_id");
		//	get specific security_id
		int security_id = adminService.getSecurityIdByAdminId(admin_id);
		//	get auditors in specific security
		List<Employee> auditorInSpecificSecurity = adminService.getServeralAuditorBySecurityId(security_id);
		JSONArray tableData = new JSONArray(auditorInSpecificSecurity.size()/2);
		for(Employee i:auditorInSpecificSecurity) {
			if(i.getEmployee_name()!=null&&i.getEmployee_name().equals(searchName)) {
				JSONObject elementInArray = new JSONObject();
				//	get employee_id, employee_name, employee_account, employee_password
				elementInArray.put("reviewer_id", i.getEmployee_id());
				elementInArray.put("name",i.getEmployee_name());
				elementInArray.put("account",i.getEmployee_account());
				elementInArray.put("password", i.getEmployee_password());
				tableData.add(elementInArray);
			}
		}
		return tableData;
	}
	@GetMapping(value = "/admin/getReviewerId",produces = "application/json;charset=UTF-8")
	public JSONArray getReviewerId() {
		//	get admin_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int admin_id = sessonJsonObject.getIntValue("employee_id");
		//	get specific security_id
		int security_id = adminService.getSecurityIdByAdminId(admin_id);
		//	get auditors in specific security
		List<Employee> auditorInSpecificSecurity = adminService.getServeralAuditorBySecurityId(security_id);
		JSONArray tableData = new JSONArray(auditorInSpecificSecurity.size()/2);
		for(Employee i:auditorInSpecificSecurity) {
			if(i!=null) {
				JSONObject elementInArray = new JSONObject();
				//	get employee_id, employee_name
				elementInArray.put("address", i.getEmployee_id());
				elementInArray.put("value",i.getEmployee_name());
				tableData.add(elementInArray);
			}
		}
		return tableData;
	}
	@PostMapping(value = "/admin/deleteReviewer",produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> deleteReviewer(@RequestBody JSONObject jsonObject) throws Exception{
		Integer reviewer_id = jsonObject.getInteger("reviewer_id");
		if(reviewer_id!=null) {
			adminService.deleteReviewerInEmployee(reviewer_id);
			adminService.deleteReviewInAuditorManager(reviewer_id);
			return ResponseEntity.ok(new Integer(1));
		}
		return ResponseEntity.status(406).body(new Integer(0));
		
	}
}
