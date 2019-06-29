package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/****
  *@author:zwz
  *@date: 2019/6/280945
  *@descrption:
  */


public interface TimeLineDAO{
    List<JSONObject> getTimeLine(@Param("cusId") String customerId);
    List<JSONObject> getOptionalTimeLine(@Param("cusId") String customerId,@Param("startTime") java.sql.Date startTime,@Param("endTime") java.sql.Date endTime);
    String getCusId(@Param("usrId") String usrId);
}