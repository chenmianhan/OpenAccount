package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.config.shiro.common.UserToken;
import com.practice.open_account.service.EmployeeService;
import com.practice.open_account.dao.EmployeeDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/****
 *@author:cmh
 *@date: 2019/6/281514
 *@descrption:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDAO employeeDAO;
    @Override
    @Transactional
    public JSONObject getEmployee(String employee_account, String employee_password, String Employee_type) {
        JSONObject  result=employeeDAO.getEmployee(employee_account, employee_password, Employee_type);
        return result;
    }
    @Override
    public int login(String employee_account, String employee_password, String Employee_type) {
        Subject currentUser = SecurityUtils.getSubject();
        UserToken token =  new UserToken(employee_account,employee_password,Employee_type);
        try {
            currentUser.login(token);
            return 1;
        } catch (AuthenticationException e) {
            return 0;
        }
    }
}
