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
    JSONArray getAllReviewers(int admin_id);

    JSONObject getSuperAdminName();

    JSONObject getUserList(int admin_id);
    JSONObject getUserInfo(int usrId);
    String getAddressInfo(int aId);
    JSONObject getPhoneAndTime(int usrId);

    int changeMaxAuditorNum(int max_num);
    int changeExpireDate(Date expire_date);
    int changeMinScore(int min_score);

    JSONArray getUserInfoBySecurityId(int seId);
    JSONArray getUserInfoByUserName(String name);

    JSONObject getAuditorNum();
    JSONObject getExpireDate();
    JSONObject getMinScore();

    JSONArray getReviewerInfo(int reviewer_id);
}
