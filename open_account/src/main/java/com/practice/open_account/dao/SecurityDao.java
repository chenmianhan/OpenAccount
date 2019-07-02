package com.practice.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/****
  *@author:zwz
  *@date: 2019/6/180945
  *@descrption:
  */


public interface SecurityDao{ 
	JSONObject getSecurityBySecurityId(Integer security_id);
    List<JSONObject> getSecurityAll();
}