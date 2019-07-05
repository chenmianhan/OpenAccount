package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.TimeLineService;
import com.practice.open_account.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.alibaba.fastjson.JSONArray;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/****
 *@author:zwz
 *@date: 2019/6/281600
 *@descrption:
 */

@RestController
public class TimeLineController {
    @Autowired
    private TimeLineService timeLineService;

    @RequestMapping(value="/timeline/get_timeline",method = GET,produces = "application/json;charset=UTF-8")
    public JSONArray getTimeLine()
    {
        String user_id= SessionUtil.getSessionAttribute().getString("user_id");
        int usrId= Integer.parseInt(user_id);
        return timeLineService.getTimeLine(usrId);
    }
    @RequestMapping(value="/timeline/get_optional_timeline",method = POST,produces = "application/json;charset=UTF-8")
    public JSONArray getOptionalTimeLine(@RequestBody JSONObject js)
    {
        String user_id= SessionUtil.getSessionAttribute().getString("user_id");
        int usrId= Integer.parseInt(user_id);
        String startTime=js.getString("starttime");
        String endTime=js.getString("endtime");
        return timeLineService.getOptionalTimeLine(usrId,startTime,endTime);
    }
}
