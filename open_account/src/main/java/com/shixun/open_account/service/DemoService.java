package com.shixun.open_account.service;

import com.alibaba.fastjson.JSONObject;

/****
 *@author:cmh
 *@date: 2019/6/110946
 *@descrption:
 */
public interface DemoService {
    JSONObject getDemo(String id);
    int addDemo( String id,String name,String age);
    int updateDemo( String id,String name, String age);
}
