package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Address;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/110946
 *@descrption:
 */
public interface AccountInfoService {
    //	accountInfo CRUD
    //	add
    int addAccountInfo(AccountInfo accountInfo) throws Exception;
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

    List<Map<String,Object>> getUserPair(String security_id);
    JSONObject getNetNameAndUserName(String user_id);
    String getAllocTimeById(String use_id);
    int updateRiskAssessmentMark(int riskAssessmentMark, int user_id);
    //	upload image
    int uploadId(String image, int user_id);
   	int uploadIdReverse(String image, int user_id);
   	int uploadHeadShot(String image, int user_id);
}
