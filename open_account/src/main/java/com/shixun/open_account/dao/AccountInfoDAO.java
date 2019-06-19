package com.shixun.open_account.dao;


import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;

/****
 *@author:cmh
 *@date: 2019/6/110945
 *@descrption:
 */
public interface AccountInfoDAO{
    // AccountInfo CRUD
    int addAccountInfo(AccountInfo accountInfo);
    
    AccountInfo getAccountInfoByUserId(Integer user_id);
    
    int updateAccountInfo(AccountInfo accountInfo);
    
    int deleteAccountInfoByUserId(Integer user_id);
    
    // Address CRUD
    int addAddress(Address address);
    Address getAddressByAId(Integer aid);
    int deleteAddressByAid(Integer aid);
    
}