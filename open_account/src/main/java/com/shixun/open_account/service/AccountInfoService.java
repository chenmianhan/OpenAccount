package com.shixun.open_account.service;

import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;

/****
 *@author:cmh
 *@date: 2019/6/110946
 *@descrption:
 */
public interface AccountInfoService {
	//	accountInfo CRUD
	
	//	add
    int addAccountInfo(AccountInfo accountInfo);
    //	get
    AccountInfo getAccountInfoByUserId(Integer user_id);
    //	update
    int updateAccountInfo(AccountInfo accountInfo);
    //	delete
    int deleteAccountInfoByUserId(Integer user_id);
    // address CRUD
    
    //	add
    int addAddress(Address address);
    //	get
    Address getAddressByAId(Integer aid);
    //	delete
    int deleteAddressByAid(Integer aid);
    
    
}
