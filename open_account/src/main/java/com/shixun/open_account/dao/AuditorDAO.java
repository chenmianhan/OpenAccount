package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

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

}
