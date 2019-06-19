package com.shixun.open_account.controller;


import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;
import com.shixun.open_account.service.ServieImpl.AccountInfoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    public ResponseEntity<AccountInfo> addAccountInfo(
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
		return temp.getAccount_info_id()==1?ResponseEntity.ok().build():ResponseEntity.notFound().build();
    	}
    
    @PostMapping(value = "getAccountInfo",produces = "application/json;charset=UTF-8")
    public AccountInfo getAccountInfoByUserId
    	(@RequestParam("user_id")Integer user_id) {
    	
    	AccountInfo temp = null;
//    	if(( temp= (AccountInfo)redisOperations.get(user_id) )==null)
//    	{
    		temp = accountInfoService.getAccountInfoByUserId(user_id);
//    		redisOperations.set(user_id,temp);
//    	}
    	return temp;
    }
    
    @PostMapping(value = "deleteAccountInfo",produces = "application/json;charset=UTF-8")
    public int deleteAccountInfoByUserId
    	(@RequestParam("user_id")Integer user_id) {
    	return accountInfoService.deleteAccountInfoByUserId(user_id);
    }
    
    @PostMapping(value = "updateAccountInfo",produces = "application/json;charset=UTF-8")
    public int updateAccountInfo(
    		Integer account_info_id,
    		Integer user_id,
    		String name,
    		String ID_type, 
			String ID_number,
			Integer ID_address_id,
			String ID_issuance_date,
			String ID_overdue_date, 
			String ID_licensing_authority, 
			Integer contact_address_id,
			Integer postal_address_id,
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
		return accountInfoService.updateAccountInfo
				(new AccountInfo(account_info_id, user_id, name, ID_type, ID_number, ID_address_id, ID_issuance_date, ID_overdue_date, ID_licensing_authority, contact_address_id, postal_address_id, trans_password, Fund_password, n_security_id, s_security_id, deposit_bank, deposit_account, deposit_password, status, profession, education, email, ID_picture, ID_card_inverse_side, risk_assessment_mark));
	}
}
