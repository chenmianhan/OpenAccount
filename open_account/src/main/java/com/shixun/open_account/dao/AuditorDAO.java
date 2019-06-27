package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
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
    JSONObject getSecurity(@Param("security_id")String security_id);
        int gettoReviewNum(@Param("security_id")String security_id);
        int getreviewedNum(@Param("auditor_id") String auditor_id);
    List<Map<String,Object>> getUserIdByTime(@Param("auditor_id")String auditor_id,
                                       @Param("start")String start,
                                       @Param("end")String end);
    JSONObject getUserInfo(@Param("user_id")String user_id);
    String getOpenDate(@Param("user_id")String user_id);
    JSONObject getOtherInfo(@Param("user_id")String user_id);
    //JSONObject getUserInfoUnreviewed(@Param("user_id")String user_id);
    List<Map<String,Object>>  gettoReviewUser_List_N(@Param("security_id")String security_id);
    List<Map<String,Object>>  gettoReviewUser_List_S(@Param("security_id") String security_id);

    int insertEmployee(
    		String employee_account, 
    		String employee_password,
    		String employee_type,
    		String employee_name
    		);
    int insertAuditor(int security_id, int auditor_id);
    


}
