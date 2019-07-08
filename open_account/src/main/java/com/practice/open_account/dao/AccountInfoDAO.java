package com.practice.open_account.dao;


import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    
    int updateSecurity(Integer user_id, Integer security_id, String trade_type);
    
    int updateDeposit(Integer user_id, String deposit_bank, String deposit_account, String deposit_password);
    
    int deleteAccountInfoByUserId(Integer user_id);
    
    // Address CRUD
    int addAddress(Address address);
    Address getAddressByAId(Integer aid);
    int deleteAddressByAid(Integer aid);
    int updateAddress(Address address);
    
    // User status update
    int updateUserStatus(Integer user_id,String status);

    List<Map<String,Object>> getUserPair(@Param("security_id")String security_id);
    JSONObject getNetNameAndUserName(@Param("user_id") String user_id);
    int updateRiskAssessmentMark(int riskAssessmentMark, int user_id);
   String  getAllocTimeById(@Param("user_id") String user_id);
}