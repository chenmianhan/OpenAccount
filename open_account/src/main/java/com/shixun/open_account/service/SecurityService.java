
package com.shixun.open_account.service;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;
import java.util.List;

/****
 *@author:zwz
 *@date: 2019/6/181007
 *@descrption:
 */
public interface SecurityService {
    JSONObject getSecurityBySecurityId(Integer security_id);
    List<JSONObject> getSecurityAll();
}
