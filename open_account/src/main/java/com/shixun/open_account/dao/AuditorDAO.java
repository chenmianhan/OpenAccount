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
        int gettoReviewNum();
        int getreviewedNum(@Param("auditor_id") String auditor_id);
    List<Map<String,Object>> getUserIdByTime(@Param("auditor_id")String auditor_id,
                                       @Param("start")String start,
                                       @Param("end")String end);
    JSONObject getUserInfo(@Param("user_id")String user_id);


}
