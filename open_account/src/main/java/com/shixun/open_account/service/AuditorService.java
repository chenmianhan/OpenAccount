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
    int gettoReviewNum();
    int getreviewedNum( String auditor_id);
    List<Map<String,Object>>  getUserIdByTime(String auditor_id,
                                       String start,
                                       String end);
}
