package com.practice.open_account.service;


import com.alibaba.fastjson.JSONObject;

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
}
