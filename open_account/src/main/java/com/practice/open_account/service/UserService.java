package com.practice.open_account.service;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/171527
 *@descrption:
 */
public interface UserService {
    int checkPhone(String phone);
    int checkPassword(String phone, String password);
    int updatePassword(String phone, String password);
    int checkLogin(String phone, String password);
    int addUser(String phone, String password);
    JSONObject getUser(String phone, String password);
    JSONObject logout();
    String getStatus(String user_id);
    List<Map<String,Object>> getWaitForReview(String security_id,
                                              String start,
                                             String end);

    int setUserStatus(String user_id, String status);
    List<Map<String, Object>> getUserByName(String security_id,String name);


}
