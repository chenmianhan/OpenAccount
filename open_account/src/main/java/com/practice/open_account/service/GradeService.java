package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;

/****
 *@author:hwy
 *@date: 2019/6/181007
 *@descrption:
 */
public interface GradeService {
    public String getGradeName(int mark);

    public void deleteGradeTable();

    public void addGrade(String grade,int mini,int max);

    public int getMark(int re_id, JSONArray answer);
}
