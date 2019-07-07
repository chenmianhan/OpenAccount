package com.practice.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.Employee;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/241027
 *@descrption:
 */
public interface AuditorDAO {
    String getSecutityIdbyAuditorId(@Param("auditor_id") String auditor_id);
    List<Map<String,Object>> getUserIdByTime(@Param("auditor_id")String auditor_id,
                                       @Param("start")String start,
                                       @Param("end")String end);
    JSONObject getUserInfo(@Param("user_id")String user_id);
    String getReviewDate(@Param("user_id")String user_id);
   // List<Map<String,Object>> getWaitForReview(@Param("security_id")String security_id);
    String getOneUserToReview(String security_id);
    JSONObject getOtherInfo(@Param("user_id")String user_id);
    List<Map<String, Object>> getAllUserByAuditorId(@Param("security_id") String security_id,
                                                    @Param("auditor_id")String auditor_id,
                                                    @Param("start") String start,
                                                    @Param("end") String end);
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
