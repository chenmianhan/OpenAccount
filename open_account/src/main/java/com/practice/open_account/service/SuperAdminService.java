package com.practice.open_account.service;

import java.util.List;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.sql.Date;

/****
 *@author:hwy
 *@date: 2019/7/011510
 *@descrption:
 */
public interface SuperAdminService {
    int getSecurityUnderAdmin(int admin_id);
    // return security_id

    int addAdmin(String name, String account, String password, int store);
    // return 1 as successful 0 otherwise

    int modifyAdmin(int employee_id, String name, String password, String account);
    // return 1 as successful 0 otherwise

    int addStore();
    // Aborting
    // return 1 as successful 0 otherwise

    int deleteStore(int store);
    // return 1 as successful 0 otherwise

    int deleteUser(int user_id);
    // return 1 as successful 0 otherwise

    int deleteAdmin(int admin_id);
    // return 1 as successful 0 otherwise

    JSONArray getStore(String store);

    JSONArray getAllStore();

    JSONArray getAdminByStore(String store);

    JSONArray getAdminByName(String admin_name);

    JSONArray getAllAdmin();

    JSONObject getSuperAdminName();

    JSONObject getUserList();
    JSONObject getUserInfo(int usrId);
    String getAddressInfo(int aId);
    JSONObject getPhoneAndTime(int usrId);

    int changeMaxAuditorNum(int max_num);
    int changeExpireDate(Date expire_date);
    int changeMinScore(int min_score);

    JSONArray getUserInfoBySecurityId(int seId);
    JSONArray getUserInfoByUserName(String name);
}
