package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.Employee;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/241038
 *@descrption:
 */
public interface AuditorService  {
    String getSecutityIdbyAuditorId( String auditor_id);
    //JSONObject getSecurity(String security_id);
    String getOneUserToReview(String security_id);
    List<Map<String,Object>>  getUserIdByTime(String auditor_id,
                                       String start,
                                       String end);
    JSONObject getUserInfo(String user_id);

    JSONObject getUserInfoUnreviewed(String user_id);
    //List<Map<String,Object>>  gettoReviewUser_List(Integer type,String security_id);

    int insertEmployee(Employee employee);
    int insertAuditor(int security_id, int auditor_id);
    int updateEmployee(
            int employee_id,
            String employee_account,
            String employee_password,
            String employee_type,
            String employee_name
    );
    int updateAuditor(int security_id, int auditor_id);
}
