package com.shixun.open_account.service;

import com.alibaba.fastjson.JSONObject;


import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/241038
 *@descrption:
 */
public interface AuditorService  {
    String getSecutityIdbyAuditorId( String auditor_id);
    JSONObject getSecurity(String security_id);
    int gettoReviewNum(Integer type,String name);
    int getreviewedNum( String auditor_id);
    List<Map<String,Object>>  getUserIdByTime(String auditor_id,
                                       String start,
                                       String end);
    JSONObject getUserInfo(String user_id);
    JSONObject getUserInfoUnreviewed(String user_id);
    List<Map<String,Object>>  gettoReviewUser_List(Integer type,String security_id);

}
