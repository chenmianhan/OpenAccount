package com.shixun.open_account.controller;


import com.shixun.open_account.dto.AccountInfoDto;
import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;
import com.shixun.open_account.service.ServieImpl.AccountInfoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.time.Duration;

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
    
    @RequestMapping(value = "/addAccountInfo", method=POST,produces = "application/json;charset=UTF-8")
    public int addAccountInfo(
    		@RequestBody AccountInfoDto accountInfoDto
//    		Integer user_id,
//    		String name,
//    		String id_type, 
//			String id_number,
//			String id_address_province,
//			String id_address_city,
//			String id_address_street,
//			String id_issuance_date,
//			String id_overdue_date,
//			String id_licensing_authority, 
//			String contact_address_province,
//			String contact_address_city,
//			String contact_address_street,
//			String postal_address_province,
//			String postal_address_city,
//			String postal_address_street,
//			String trans_password, 
//			String fund_password, 
//			Integer n_security_id,
//			Integer s_security_id,
//			String deposit_bank,
//			String deposit_account,
//			String deposit_password, 
//			String status, 
//			String profession, 
//			String education, 
//			String email,
//			String id_picture, 
//			String id_card_inverse_side
    		) 
    {
    	// create address first
    	accountInfoService.addAddress(accountInfoDto.getID_address());
    	Integer id_address_id = accountInfoDto.getID_address().getAid();
    
    	accountInfoService.addAddress(accountInfoDto.getContact_address());
    	Integer contact_address_id = accountInfoDto.getContact_address().getAid();
    	
    	accountInfoService.addAddress(accountInfoDto.getPostal_address());
		Integer postal_address_id = accountInfoDto.getPostal_address().getAid(); 
		
		//	update ids in account_info after insert addresses
		accountInfoDto.getAccount_info().setId_address_id(id_address_id);
		accountInfoDto.getAccount_info().setContact_address_id(contact_address_id);
		accountInfoDto.getAccount_info().setPostal_address_id(postal_address_id);
		accountInfoService.addAccountInfo(accountInfoDto.getAccount_info());
		
		//	if successfully insert, then set redis
//		if(temp.getAccount_info_id()!=null) {
//			redisOperations.set("account_info:"+user_id, temp, Duration.ofHours(24L));
			return 1;
//			}
//		return 0;
    	}
    
    @GetMapping(value = "/getAccountInfo",produces = "application/json;charset=UTF-8")
    public AccountInfoDto getAccountInfoByUserId
    	(@RequestParam("user_id")Integer user_id) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    	//	get directly if in redis.
    	//	Otherwise get from mysql and reset in redis
    	AccountInfo temp = null;
//    	if(( temp= (AccountInfo)redisOperations.get("account_info:"+user_id) )==null)
//    	{
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
    public int updateAccountInfo(
    		Integer user_id,
    		String name,
    		String id_type, 
			String id_number,
			String id_address_province,
			String id_address_city,
			String id_address_street,
			String id_issuance_date,
			String id_overdue_date, 
			String id_licensing_authority, 
			String contact_address_province,
			String contact_address_city,
			String contact_address_street,
			String postal_address_province,
			String postal_address_city,
			String postal_address_street,
			String trans_password, 
			String fund_password, 
			Integer n_security_id,
			Integer s_security_id,
			String deposit_bank,
			String deposit_account,
			String deposit_password, 
			String status, 
			String profession, 
			String education, 
			String email,
			String id_picture, 
			String id_card_inverse_side,
			Integer risk_assessment_mark
    		)
	{
    	//	get address_id first
    	AccountInfo before = 
//    			redisOperations.get("account_info:"+user_id)!=null?
//    					(AccountInfo)redisOperations.get("account_info:"+user_id):
    			accountInfoService.getAccountInfoByUserId(user_id);
    	Integer id_address_id = before.getId_address_id();
    	Integer contact_address_id = before.getContact_address_id();
    	Integer postal_address_id = before.getPostal_address_id();
    	
    	//	then update address
    	accountInfoService.updateAddress(new Address(id_address_id, id_address_province, id_address_city, id_address_street));
    	accountInfoService.updateAddress(new Address(contact_address_id, contact_address_province, contact_address_city, contact_address_street));
    	accountInfoService.updateAddress(new Address(postal_address_id, postal_address_province, postal_address_city, postal_address_street));

    	//	update account_info
    	AccountInfo temp = new AccountInfo(null, user_id, name, id_type, id_number, id_address_id, id_issuance_date, id_overdue_date, id_licensing_authority, id_picture, id_card_inverse_side, contact_address_id, postal_address_id, trans_password, fund_password, n_security_id, s_security_id, deposit_bank, deposit_account, deposit_password, status, profession, education, email, risk_assessment_mark);
    	if(accountInfoService.updateAccountInfo(temp)==1) {
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
			Integer user_id, Integer n_security_id, Integer s_security_id) {
		if(accountInfoService.updateSecurity(user_id, n_security_id, s_security_id)==1) {
			
//			redisOperations.set("account_info:"+user_id, value);
			return 1;
		}
		return 0;
	}
	
	@PutMapping(value = "/updateDeposit",produces = "application/json;charset=UTF-8")
	public int updateDeposit(
			Integer user_id, String deposit_bank, String deposit_account, String deposit_password)
	{
		if(accountInfoService.updateDeposit(user_id, deposit_bank, deposit_account, deposit_password)==1) {
			return 1;
		}
		else return 0;
	}
}
