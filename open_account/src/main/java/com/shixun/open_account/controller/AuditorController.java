package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
        //@Resource
        //AuditorDAO auditorDAO;
        @Resource
        private AuditorService auditorService;
        @RequestMapping(value="/api/statisticData/getReviewerInfo", method=POST, produces = "application/json;charset=UTF-8")
        public JSONObject getReviewerInfo(@RequestParam(value = "reviewerId") String reviewerId) throws Exception
        {

            String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
            JSONObject security=auditorService.getSecurity(security_id);
            int getreviewedNum=auditorService.getreviewedNum(reviewerId);
            int gettoReviewNum=auditorService.gettoReviewNum(security_id);
           // System.out.println("gettoReviewNum"+gettoReviewNum);
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
    @RequestMapping(value="/api/statisticData/getUserInfo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONArray getUserInfo(@RequestParam(value = "reviewerId") String reviewerId,
                                      @RequestParam(value = "start") String start,
                                      @RequestParam(value = "end") String end) throws Exception {
        JSONArray jsonArray=new JSONArray();
       String startTimeStamp= getSQLDateTime(start);
        String endTimeStamp= getSQLDateTime(end);
        List<Map<String,Object>> lsm= auditorService.getUserIdByTime(reviewerId,startTimeStamp,endTimeStamp);
        ArrayList<String> user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);
        for(int j=0;j<user_id_list.size();j++)
        {

            jsonArray.add(auditorService.getUserInfo(user_id_list.get(j)));
        }

        return   jsonArray;
    }
    @RequestMapping(value="/api/reviewUser/getUserInfo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getUserInfo(@RequestParam(value = "reviewerId") String reviewerId)
    {
        String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
        JSONObject security=auditorService.getSecurity(security_id);
        JSONObject js=new JSONObject();
        List<Map<String,Object>> lsm=auditorService.gettoReviewUser_List((Integer)(security.get("type")),security_id);
       // System.out.println("lsm"+lsm);
        ArrayList<String> user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);
       // System.out.println(user_id_list);
        String user_id=user_id_list.get(0);
        JSONObject userInfoTemp =auditorService.getUserInfo(user_id);
        JSONArray userInfo=new JSONArray();
        parseUserInfo(user_id, userInfoTemp, userInfo);
        JSONObject jsonObject=auditorService.getUserInfoUnreviewed(user_id);
        jsonObject.put("userInfo",userInfo);
        return jsonObject;
    }

    private void parseUserInfo(String user_id, JSONObject userInfoTemp, JSONArray userInfo) {
        JSONObject temp=new JSONObject();
        temp.put("title","用户ID");
        temp.put("content",user_id);
        userInfo.add(temp);
       // System.out.println(userInfo);
        temp=new JSONObject();
        temp.put("title","姓名");
        temp.put("content",userInfoTemp.get("userName"));
        userInfo.add(temp);
        temp=new JSONObject();
        temp.put("title","身份证号码");
        temp.put("content",userInfoTemp.get("idCardNum"));
        userInfo.add(temp);
        System.out.println(userInfo);
        temp=new JSONObject();
        temp.put("title","证件有效期");
        temp.put("content",userInfoTemp.get("idValDate"));
        userInfo.add(temp);
        temp=new JSONObject();
        temp.put("title","发证机关");
        temp.put("content",userInfoTemp.get("idInstitute"));
        userInfo.add(temp);
        temp=new JSONObject();
        temp.put("title","职业");
        temp.put("content",userInfoTemp.get("userJob"));
        userInfo.add(temp);
        temp.put("title","学历");
        temp.put("content",userInfoTemp.get("education"));
        userInfo.add(temp);
        temp=new JSONObject();
        temp.put("title","联系邮箱");
        temp.put("content",userInfoTemp.get("email"));
        userInfo.add(temp);
        temp=new JSONObject();
        temp.put("title","银行");
        temp.put("content",userInfoTemp.get("bankName"));
        userInfo.add(temp);
        temp=new JSONObject();
        temp.put("title","银行卡号");
        temp.put("content",userInfoTemp.get("bankCardNum"));
        userInfo.add(temp);
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
