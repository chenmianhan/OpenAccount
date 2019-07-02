package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.practice.open_account.service.AccountAllocService;
import com.practice.open_account.service.AuditorService;

import com.practice.open_account.service.SecurityService;
import com.practice.open_account.util.CommonUtil;
import com.practice.open_account.util.SessionUtil;
import com.practice.open_account.util.constants.AuditorConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


import java.util.ArrayList;
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
    private AuditorService auditorService;
    @Resource
    private SecurityService securityService;
    @Resource
    private AccountAllocService accountAllocService;
    @RequestMapping(value="/reviewer", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject reviewer(){
        String reviewerId= SessionUtil.getSessionAttribute().getString("employee_id");
        String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
        JSONObject security=securityService.getSecurityBySecurityId(Integer.parseInt(security_id,10));
        String netName=security.getString("name");
        String reviewerName=SessionUtil.getSessionAttribute().getString("employee_name");
        JSONObject js=new JSONObject();
        js.put("netName",netName);
        js.put("reviewerName",reviewerName);
        return js;
        }





    @RequestMapping(value="/api/statisticData/getUserInfo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONArray getUserInfo(@RequestParam(value = "reviewerId") String reviewerId,
                                      @RequestParam(value = "start") String start,
                                      @RequestParam(value = "end") String end) throws Exception {
        JSONArray jsonArray=new JSONArray();
      //  String startTimeStamp= getSQLDateTime(start);
       // String endTimeStamp= getSQLDateTime(end);
        List<Map<String,Object>> lsm= auditorService.getUserIdByTime(reviewerId,start,end);
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
        ArrayList<String> user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);
        if(user_id_list.size()==0){
            js.put("userInfo",new JSONArray());
            js.put("imageUrl_1","");
            js.put("imageUrl_2","");
            js.put("imageUrl_3","");
            js.put("userType","");
            js.put("userGrade","");
            js.put("code",AuditorConstants.NONE_MSG);
            return js;
}
        String user_id=user_id_list.get(0);
int result=auditorService.setUserStatus(user_id,"5","审核中");
if(result==1){JSONObject userInfoTemp =auditorService.getUserInfo(user_id);
    JSONArray userInfo=new JSONArray();
    parseUserInfo(user_id, userInfoTemp, userInfo);
    JSONObject jsonObject=auditorService.getUserInfoUnreviewed(user_id);
    jsonObject.put("userInfo",userInfo);
    jsonObject.put("code",AuditorConstants.MUCH_MSG);
    return jsonObject;}
else return new JSONObject();
    }

    @RequestMapping(value="/reviewer/postResult", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject postResult(@RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "infoResult") String infoResult,
                                 @RequestParam(value = "gradeResult") String gradeResult,
                                 @RequestParam(value = "imageResult") String imageResult
                                 )
    {
        String result_review;
        String status;
        if(imageResult.equals("true")&&infoResult.equals("true")&&gradeResult.equals("true"))
        {
            status="7";
            result_review="审核通过";
         boolean result = accountAllocService.openAccount(Integer.parseInt(userId,10));
         if(!result){
             status="8";
             result_review="开户系统出错";
             auditorService.setUserStatus(userId,status,result_review);
             return CommonUtil.getJson(AuditorConstants.ERROR_MSG);
         }
        }
        else {
            status="6";
            result_review="审核未通过：";}
        if(imageResult.equals("false")){
            result_review+="照片审核不通过；";
        }
        if(infoResult.equals("false"))
        {
            result_review+="个人信息填写不通过；";
        }
        if(gradeResult.equals("false"))
        {
            result_review+="风险测评不通过：";
        }
        int result=auditorService.setUserStatus(userId,status,result_review);
        if(result==1)
            return CommonUtil.getJson(AuditorConstants.SUCCESS_MSG);
        else return CommonUtil.getJson(AuditorConstants.FAIL_MSG);
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

    /*private String getSQLDateTime( String start) {
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
    }*/

}
