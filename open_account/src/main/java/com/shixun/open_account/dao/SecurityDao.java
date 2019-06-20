package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/****
  *@author:zwz
  *@date: 2019/6/180945
  *@descrption:
  */


public interface SecurityDao{
    List<JSONObject> getSecurity(@Param("province") String province, @Param("city") String city);
    List<JSONObject> getSecurityAll();
}