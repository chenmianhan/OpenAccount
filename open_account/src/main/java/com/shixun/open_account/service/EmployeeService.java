package com.shixun.open_account.service;

import com.alibaba.fastjson.JSONObject;


/****
 *@author:cmh
 *@date: 2019/6/281513
 *@descrption:
 */
public interface EmployeeService {
    JSONObject getEmployee(String employee_account, String employee_password, String Employee_type);
    int login(String employee_account, String employee_password, String Employee_type);
}
