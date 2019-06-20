package com.shixun.open_account.controller;


import com.shixun.open_account.dto.AccountInfoDto;
import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;
import com.shixun.open_account.service.ServieImpl.AccountInfoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    		Integer user_id,
    		String name,
    		String ID_type, 
			String ID_number,
			String ID_address_province,
			String ID_address_city,
			String ID_address_street,
			String ID_issuance_date,
			String ID_overdue_date,
			String ID_licensing_authority, 
			String contact_address_province,
			String contact_address_city,
			String contact_address_street,
			String postal_address_province,
			String postal_address_city,
			String postal_address_street,
			String trans_password, 
			String Fund_password, 
			Integer n_security_id,
			Integer s_security_id,
			String deposit_bank,
			String deposit_account,
			String deposit_password, 
			String status, 
			String profession, 
			String education, 
			String email,
			String ID_picture, 
			String ID_card_inverse_side
    		) 
    {
    	// create address first
    	Address ID_address = new Address(null, ID_address_province, ID_address_city, ID_address_street);
    	accountInfoService.addAddress(ID_address);
    	Integer ID_address_id = ID_address.getAid();
    	
    	Address contact_address = new Address(null, contact_address_province, contact_address_city, contact_address_street);
    	accountInfoService.addAddress(contact_address);
    	Integer contact_address_id = contact_address.getAid();
    	
    	Address postal_address = new Address(null, postal_address_province, postal_address_city, postal_address_street);
    	accountInfoService.addAddress(postal_address);
		Integer postal_address_id = postal_address.getAid(); 
		
		AccountInfo temp = new AccountInfo(null, user_id, name, ID_type, ID_number, ID_address_id, ID_issuance_date, ID_overdue_date, ID_licensing_authority, contact_address_id, postal_address_id, trans_password, Fund_password, n_security_id, s_security_id, deposit_bank, deposit_account, deposit_password, status, profession, education, email, ID_picture, ID_card_inverse_side, null);
		accountInfoService.addAccountInfo(temp);
		
		//	if successfully insert, then set redis
		if(temp.getAccount_info_id()!=null) {
//			redisOperations.set("account_info:"+user_id, temp, Duration.ofHours(24L));
			return 1;
			}
		return 0;
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
				accountInfoService.getAddressByAId(temp.getID_address_id()), 
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
    		String ID_type, 
			String ID_number,
			String ID_address_province,
			String ID_address_city,
			String ID_address_street,
			String ID_issuance_date,
			String ID_overdue_date, 
			String ID_licensing_authority, 
			String contact_address_province,
			String contact_address_city,
			String contact_address_street,
			String postal_address_province,
			String postal_address_city,
			String postal_address_street,
			String trans_password, 
			String Fund_password, 
			Integer n_security_id,
			Integer s_security_id,
			String deposit_bank,
			String deposit_account,
			String deposit_password, 
			String status, 
			String profession, 
			String education, 
			String email,
			String ID_picture, 
			String ID_card_inverse_side,
			Integer risk_assessment_mark
    		)
	{
    	//	get address_id first
    	AccountInfo before = 
//    			redisOperations.get("account_info:"+user_id)!=null?
//    					(AccountInfo)redisOperations.get("account_info:"+user_id):
    			accountInfoService.getAccountInfoByUserId(user_id);
    	Integer ID_address_id = before.getID_address_id();
    	Integer contact_address_id = before.getContact_address_id();
    	Integer postal_address_id = before.getPostal_address_id();
    	
    	//	then update address
    	accountInfoService.updateAddress(new Address(ID_address_id, ID_address_province, ID_address_city, ID_address_street));
    	accountInfoService.updateAddress(new Address(contact_address_id, contact_address_province, contact_address_city, contact_address_street));
    	accountInfoService.updateAddress(new Address(postal_address_id, postal_address_province, postal_address_city, postal_address_street));

    	//	update account_info
    	AccountInfo temp = new AccountInfo(null, user_id, name, ID_type, ID_number, ID_address_id, ID_issuance_date, ID_overdue_date, ID_licensing_authority, contact_address_id, postal_address_id, trans_password, Fund_password, n_security_id, s_security_id, deposit_bank, deposit_account, deposit_password, status, profession, education, email, ID_picture, ID_card_inverse_side, risk_assessment_mark);
    	if(accountInfoService.updateAccountInfo(temp)==1) {
    		// update success then update redis
//    		redisOperations.set("account_info:"+"account_info:"+temp.getUser_id(),
//    				accountInfoService.getAccountInfoByUserId(user_id),
//    				Duration.ofHours(24L));
    		return 1;
    	}
    	else return 0;
	}
}
