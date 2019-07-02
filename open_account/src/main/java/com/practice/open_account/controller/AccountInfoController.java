package com.practice.open_account.controller;


import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dto.AccountInfoDto;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Address;
import com.practice.open_account.service.SecurityService;
//import com.shixun.open_account.redisUtils.RedisOperation;
import com.practice.open_account.service.ServieImpl.AccountInfoServiceImpl;
import com.practice.open_account.util.constants.LoginConstants;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.apache.shiro.SecurityUtils;

/****
 *@author:cmh
 *@date: 2019/6/110944
 *@description:
 */
@RestController
public class AccountInfoController {
//	@Autowired
//	private ValueOperations<Object, Object> redisOperations;
	@Autowired
    private AccountInfoServiceImpl accountInfoService;
	@Autowired
	private SecurityService securityService;
    
	//	上传
//	@PostMapping(value="/savePic")
//	public void savePic()
//	
	//	添加用户信息
    @RequestMapping(value = "/addAccountInfo", method=POST,produces = "application/json;charset=UTF-8")
    public int addAccountInfo(@RequestBody JSONObject jsonObject
    		) throws Exception 
    {
    	System.out.println(jsonObject);
    	AccountInfoDto accountInfoDto = new AccountInfoDto
    			(jsonObject.getObject("account_info",AccountInfo.class), 
		  		 new Address(jsonObject.getObject("id_address",String[].class),jsonObject.getString("id_address_detail")),
		  		 new Address(jsonObject.getObject("contact_address",String[].class),jsonObject.getString("contact_address_detail")),
		  		 new Address(jsonObject.getObject("postal_address",String[].class),jsonObject.getString("postal_address_detail"))
		   		 );
//    	通过session获取user_id
    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	accountInfoDto.getAccount_info().setUser_id(sessonJsonObject.getInteger("user_id"));
    	// insert address first
    	accountInfoService.addAddress(accountInfoDto.getId_address());
//    	redisOperations.set(accountInfoDto.getId_address(), value);
    	Integer id_address_id = accountInfoDto.getId_address().getAid();
    
    	accountInfoService.addAddress(accountInfoDto.getContact_address());
    	Integer contact_address_id = accountInfoDto.getContact_address().getAid();
    	
    	accountInfoService.addAddress(accountInfoDto.getPostal_address());
		Integer postal_address_id = accountInfoDto.getPostal_address().getAid(); 
		
		//	update ids in account_info after insert addresses
		accountInfoDto.getAccount_info().setId_address_id(id_address_id);
		accountInfoDto.getAccount_info().setContact_address_id(contact_address_id);
		accountInfoDto.getAccount_info().setPostal_address_id(postal_address_id);
		accountInfoService.addAccountInfo(accountInfoDto.getAccount_info());
		System.out.println();
		//	update status
		return accountInfoService.updateUserStatus(accountInfoDto.getAccount_info().getUser_id(), "1");
		//	if successfully insert, then set redis
//		if(temp.getAccount_info_id()!=null) {
//			redisOperations.set("account_info:"+user_id, temp, Duration.ofHours(24L));
			
//			}
//		return 0;
    	}
    
    @GetMapping(value = "/getAccountInfo",produces = "application/json;charset=UTF-8")
    public AccountInfoDto getAccountInfoByUserId
    	() throws Exception{
    	//	get directly if in redis.
    	//	Otherwise get from mysql and reset in redis
    	AccountInfo temp = null;
//    	if(( temp= (AccountInfo)redisOperations.get("account_info:"+user_id) )==null)
//    	{
    	Integer user_id =((JSONObject) SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO)).getIntValue("user_id");
		temp = accountInfoService.getAccountInfoByUserId(user_id);
//    		redisOperations.set("account_info:"+user_id,temp,Duration.ofHours(24L));
//    	}
    	//System.out.println(temp.getClass().getField("name").get(temp));
    	return new AccountInfoDto(temp, 
				accountInfoService.getAddressByAId(temp.getId_address_id()), 
				accountInfoService.getAddressByAId(temp.getContact_address_id()), 
				accountInfoService.getAddressByAId(temp.getPostal_address_id())
				);
    }
    
    @PostMapping(value = "/deleteAccountInfo",produces = "application/json;charset=UTF-8")
    public int deleteAccountInfoByUserId
    	(@RequestParam("user_id")Integer user_id) {
    	return accountInfoService.deleteAccountInfoByUserId(user_id);
    }
    
    @PostMapping(value = "/updateAccountInfo",produces = "application/json;charset=UTF-8")
    public int updateAccountInfo(@RequestBody JSONObject jsonObject) throws Exception
	{
    	//	get modify AccountInfo
    	AccountInfo modify = jsonObject.getObject("account_info", AccountInfo.class);
    	//	get address_id first
    	AccountInfo before = accountInfoService.getAccountInfoByUserId(modify.getUser_id());
//    			redisOperations.get("account_info:"+user_id)!=null?(AccountInfo)redisOperations.get("account_info:"+user_id):
    	Integer id_address_id = before.getId_address_id();
    	Integer contact_address_id = before.getContact_address_id();
    	Integer postal_address_id = before.getPostal_address_id();
    	
    	//	update address
		if(
				!(accountInfoService.updateAddress(new Address(id_address_id,jsonObject.getObject("id_address",String[].class)))>0
    		&&accountInfoService.updateAddress(new Address(contact_address_id, jsonObject.getObject("contact_address",String[].class)))>0
			&&accountInfoService.updateAddress(new Address(postal_address_id, jsonObject.getObject("postal_address",String[].class)))>0
			))
			throw new Exception("update Address failed");
		//	update address_id of modify
		modify.setId_address_id(id_address_id);
		modify.setContact_address_id(contact_address_id);
		modify.setPostal_address_id(postal_address_id);
    	//	update account_info by modify
    	if(accountInfoService.updateAccountInfo(modify)==1) {
    		// update success then update redis
//    		redisOperations.set("account_info:"+temp.getUser_id(),
//    				accountInfoService.getAccountInfoByUserId(user_id),
//    				Duration.ofHours(24L));
    		return 1;
    	}
    	else return 0;
	}


	@PutMapping(value = "/updateSecurity",produces = "application/json;charset=UTF-8")
	public int updateSecurity(
			 Integer security_id, String trade_type) {
    	//	通过session获取user_id
    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	int user_id = sessonJsonObject.getIntValue("user_id");
		return ((accountInfoService.updateSecurity(user_id, security_id, trade_type))&
				accountInfoService.updateUserStatus(user_id, "3"));
//			redisOperations.set("account_info:"+user_id, value);
	}
	
	@PutMapping(value = "/updateDeposit",produces = "application/json;charset=UTF-8")
	public String updateDeposit(
			String deposit_bank, String deposit_account, String deposit_password)
	{
//		通过session获取user_id
    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	int user_id = sessonJsonObject.getIntValue("user_id");
    	//	update table account_info 
		if(accountInfoService.updateDeposit(user_id, deposit_bank, deposit_account, deposit_password)==1) {
//			if(redisOperations.get(key))
			//	update user status to 4
			if(accountInfoService.updateUserStatus(user_id, "4")==1) {
				//	return contact_phone of the corresponding security  
				return securityService.getSecurityBySecurityId(
						accountInfoService.getAccountInfoByUserId(user_id).getSecurity_id()
						).getString("contact_phone");
			}
				
				else return "status update fail";
		}
		else return "deposit update fail";
	}

	@GetMapping(value = "/contactNum",produces = "application/json;charset=UTF-8")
	public String getContactNum() {
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	int user_id = sessonJsonObject.getIntValue("user_id");
		return securityService.getSecurityBySecurityId(
				accountInfoService.getAccountInfoByUserId(user_id).getSecurity_id()
				).getString("contact_phone"); 
	}
}
// 22 11
