package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/****
 *@author:cmh
 *@date: 2019/6/171509
 *@descrption:
 */
public interface UserDao {
     int checkPhone(@Param("phone") String phone);
    int checkLogin(@Param("phone") String phone, @Param("password") String password);
    int addUser(@Param("phone") String phone, @Param("password") String password);
    JSONObject getUser(@Param("phone") String phone, @Param("password") String password);
    JSONObject getEmployee(@Param("employee_account") String employee_account,
                           @Param("employee_password") String employee_password,
                           @Param("employee_type") String employee_type);
}
