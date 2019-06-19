package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/****
 *@author:cmh
 *@date: 2019/6/110945
 *@descrption:
 */
public interface DemoDao{
    JSONObject getDemo(@Param("id") String id);
    int addDemo(@Param("id") String id,@Param("name") String name, @Param("age") String age);
    int updateDemo(@Param("id") String id,@Param("name") String name, @Param("age") String age);
}