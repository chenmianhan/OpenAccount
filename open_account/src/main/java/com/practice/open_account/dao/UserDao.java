package com.practice.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/171509
 *@descrption:
 */
public interface UserDao {
     int checkPhone(@Param("phone") String phone);
   // int checkLogin(@Param("phone") String phone, @Param("password") String password);

    int addUser(@Param("phone") String phone, @Param("password") String password);
    JSONObject getUser(@Param("phone") String phone, @Param("password") String password);
    String getStatus(@Param("user_id") String user_id);
    List<Map<String,Object>> getWaitForReview(@Param("security_id")String security_id,
                                              @Param("start")String start,
                                              @Param("end")String end);
    int setUserStatus(@Param("user_id")String user_id,
                      @Param("status")String status);
}
