package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/****
  *@author:zwz
  *@date: 2019/6/180945
  *@descrption:
  */


public interface GradeDao{
    String getGradeName(@Param("mark") int mark);
    void deleteGradeTable();
    void addGrade(@Param("grade") String grade,@Param("mini") int mini, @Param("max") int max);
}