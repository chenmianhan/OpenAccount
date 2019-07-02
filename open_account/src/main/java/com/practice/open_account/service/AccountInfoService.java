package com.practice.open_account.service;

import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Address;

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
    //	updateAll
    int updateAccountInfo(AccountInfo accountInfo);
    //	updateSecurity
    int updateSecurity(Integer user_id, Integer security_id, String trade_type);
    //	updateDeposit
    int updateDeposit(Integer user_id, String deposit_bank, String deposit_account, String deposit_password);
    //	delete
    int deleteAccountInfoByUserId(Integer user_id);


    //	address CRUD
    //	add
    int addAddress(Address address);
    //	get
    Address getAddressByAId(Integer aid);
    //	delete
    int deleteAddressByAid(Integer aid);
    //	update
    int updateAddress(Address address);
    
    //	User status update
    int updateUserStatus(Integer user_id,String status);

}
