package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;
import java.util.List;

/****
 *@author:zwz
 *@date: 2019/6/181007
 *@descrption:
 */

public interface TimeLineService {
    JSONArray getTimeLine(int usrId);
    JSONArray getOptionalTimeLine(int usrId,String startTime,String endTime);
}
