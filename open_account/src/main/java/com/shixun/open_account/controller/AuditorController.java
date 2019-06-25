package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.AuditorDAO;

import com.shixun.open_account.service.AuditorService;

import net.sf.jsqlparser.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:cmh
 *@date: 2019/6/241100
 *@descrption:
 */
@RestController
public class AuditorController {
        @Resource
    AuditorDAO auditorDAO;
        @Resource
        private AuditorService auditorService;
        @RequestMapping(value="/api/statisticData/getReviewerInfo", method=POST, produces = "application/json;charset=UTF-8")
        public JSONObject getReviewerInfo(@RequestParam(value = "reviewerId") String reviewerId) throws Exception
        {
            int getreviewedNum=auditorService.getreviewedNum(reviewerId);
            int gettoReviewNum=auditorService.gettoReviewNum();
            String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
            JSONObject security=auditorService.getSecurity(security_id);
            JSONObject js=new JSONObject();
            if((Integer)(security.get("type"))==0)
            {
                js.put("exchangeName","上海证券交易所");
            }
            else {
                js.put("exchangeName","深圳证券交易所");
            }
            js.put("branchNetName",security.get("name"));
            js.put("toReviewNum",gettoReviewNum);
            js.put("reviewedNum",getreviewedNum);
            return js;
        }
    @RequestMapping(value="/api/statisitcData/getUserInfo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getUserInfo(@RequestParam(value = "reviewerId") String reviewerId,
                                      @RequestParam(value = "start") String start,
                                      @RequestParam(value = "end") String end) throws Exception {
       String startTimeStamp= getSQLDateTime(start);
        String endTimeStamp= getSQLDateTime(end);
        List<Map<String,Object>> lsm= auditorService.getUserIdByTime(reviewerId,startTimeStamp,endTimeStamp);
        ArrayList<String> user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);

        return   null;
    }

    private void getUseridList(List<Map<String, Object>> lsm, ArrayList<String> user_id_list) {
        for(int i=0;i<lsm.size();i++)
        {
            for(String key : lsm.get(i).keySet()){
                user_id_list.add((lsm.get(i).get(key)).toString());
            }
        }
    }

    private String getSQLDateTime( String start) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sdate = null;
        Timestamp timestamp ;//初始化  
        try {
            Date udate = sdf.parse(start);
            sdate = new java.sql.Date(udate.getTime());//2013-01-14  
            long longTime = sdate.getTime();
            timestamp  = new Timestamp(longTime);
            return timestamp.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
