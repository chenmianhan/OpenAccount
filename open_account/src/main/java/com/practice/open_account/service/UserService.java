package com.practice.open_account.service;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/171527
 *@descrption:
 */
public interface UserService {
    int checkPhone(String phone);
    int checkLogin(String phone, String password);
    int addUser(String phone, String password);
    JSONObject getUser(String phone, String password);
    JSONObject logout();
    List<Map<String,Object>> getWaitForReview(String security_id,
                                              String start,
                                             String end);
    int setUserStatus(String user_id, String status);
}
