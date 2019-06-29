package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/****
 *@author:cmh
 *@date: 2019/6/281445
 *@descrption:
 */
public interface EmployeeDAO {
    JSONObject getEmployee(@Param("employee_account") String employee_account,
                           @Param("employee_password") String employee_password,
                           @Param("employee_type") String employee_type);
}
