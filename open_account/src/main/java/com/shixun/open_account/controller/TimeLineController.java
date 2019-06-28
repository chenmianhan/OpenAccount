package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.TimeLineDAO;
import com.shixun.open_account.service.AdminService;
import com.shixun.open_account.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.alibaba.fastjson.JSONArray;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:zwz
 *@date: 2019/6/281600
 *@descrption:
 */

@RestController
public class TimeLineController {
    @Autowired
    private TimeLineService timeLineService;

    @RequestMapping(value="/timeline/get_timeline",method = POST,produces = "application/json;charset=UTF-8")
    public JSONArray getTimeLine(@RequestBody JSONObject js)
    {
        String cusId=js.getString("customer_id");
        return timeLineService.getTimeLine(cusId);
    }
    @RequestMapping(value="/timeline/get_optional_timeline",method = POST,produces = "application/json;charset=UTF-8")
    public JSONArray getOptionalTimeLine(@RequestBody JSONObject js)
    {
        String cusId=js.getString("customer_id");
        String startTime=js.getString("starttime");
        String endTime=js.getString("endtime");
        return timeLineService.getOptionalTimeLine(cusId,startTime,endTime);
    }
}
