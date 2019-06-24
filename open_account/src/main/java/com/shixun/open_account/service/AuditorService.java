package com.shixun.open_account.service;

import com.alibaba.fastjson.JSONObject;

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
}
