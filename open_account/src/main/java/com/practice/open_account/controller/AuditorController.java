package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONStreamAware;
import com.github.pagehelper.PageHelper;
import com.practice.open_account.service.*;

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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
    private ReviewResultService reviewResultService;
    @Resource
    private UserService userService;
    @Resource
    private AccountInfoService accountInfoService;
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
    @RequestMapping(value="/reviewer/getUserInfo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getUserInfo()
    {
        String reviewerId=null;
        try{reviewerId=Long.toString((Long)(SessionUtil.getSessionAttribute().get("employee_id")));}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);

        String user_id=reviewResultService.checkExitWaitForReviewByAuditor(reviewerId);
        if(user_id!=null)
        {
            return getUserInfo(user_id);
        }
        //System.out.println(getClass()+user_id);
      else {
            user_id=auditorService.getOneUserToReview(security_id);
            if(user_id==null)
            {
                JSONObject js=new JSONObject();
                js.put("userInfo",new JSONArray());
                js.put("imageUrl_1","");
                js.put("imageUrl_2","");
                js.put("imageUrl_3","");
                js.put("userType","");
                js.put("userGrade","");
                js.put("code",AuditorConstants.NONE_MSG);
                return js;
            }
            // System.out.println(getClass()+user_id);
            int result= reviewResultService.addReviewResult(user_id,reviewerId,"审核中");
            if(result==1){
                return getUserInfo(user_id);
            }
            else return new JSONObject();
        }
        //String

    }

    @RequestMapping(value="/reviewer/getUserId", method=POST, produces = "application/json;charset=UTF-8")
    public JSONArray getUserPair(){
        String reviewerId= SessionUtil.getSessionAttribute().getString("employee_id");
        String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
        List<Map<String,Object>> lsm= accountInfoService.getUserPair(security_id);

        //System.out.println(lsm);
        return getUserPair(lsm);
    }

    @RequestMapping(value="/reviewer/getReviewerInfo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getReviewerInfo(@RequestParam(value = "reviewerId") String reviewerId,
                                      @RequestParam(value = "start") String start,
                                      @RequestParam(value = "end") String end){
        if(reviewerId.equals(""))
            try{reviewerId=Long.toString((Long)(SessionUtil.getSessionAttribute().get("employee_id")));}
            catch (Exception e)
            {
                e.printStackTrace();
            }
        String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
        List<Map<String,Object>> lsm= userService.getWaitForReview(security_id, start, end);
        //System.out.println(lsm);
        ArrayList<String> user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("toReviewNum",user_id_list.size());

        lsm=reviewResultService.getReviewSuccess(reviewerId, start, end);
       // System.out.println(lsm);
        System.out.println("getReviewerInfo");
        System.out.println("reviewerId:"+reviewerId);
        System.out.println("start:"+start);
        System.out.println("end:"+end);
        user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);
        jsonObject.put("reviewedNum",user_id_list.size());

        lsm=reviewResultService.getReviewFail(reviewerId, start, end);
        System.out.println(lsm);
        user_id_list=new ArrayList<>();
        getUseridList(lsm, user_id_list);
        jsonObject.put("notPassNum",user_id_list.size());
        return jsonObject;
    }
    @RequestMapping(value= "/reviewer/getUserInfoById", method=GET, produces = "application/json;charset=UTF-8")
    public JSONArray getUserInfoById(@RequestParam(value = "userId") String userId)
    {
        JSONObject userInfoTemp = auditorService.getUserInfo(userId);
        String  reviewStatus=userService.getStatus(userId);
        reviewStatus = getStatusStringByStatusInt(reviewStatus);
        userInfoTemp.put("reviewStatus",reviewStatus);
        JSONArray userInfo = new JSONArray();
        userInfo.add(userInfoTemp);
        return userInfo;
    }

    private String getStatusStringByStatusInt(String reviewStatus) {
        if (reviewStatus.equals("4")) {
            reviewStatus = "未审核";
        } else if (reviewStatus.equals("5")) {
            reviewStatus = "审核中";
        } else if (reviewStatus.equals("6")) {
            reviewStatus = "未通过";
        } else if (reviewStatus.equals("7")) {
            reviewStatus = "通过";
        }
        return reviewStatus;
    }

    @RequestMapping(value= "/reviewer/getUserByName", method=POST, produces = "application/json;charset=UTF-8")
    public JSONArray getUserByName(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                   @RequestParam(value = "size", defaultValue = "5")int pageSize)
    {
        String reviewerId= SessionUtil.getSessionAttribute().getString("employee_id");
        String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
        PageHelper.startPage(pageNum, pageSize,"user_id");
        List<Map<String,Object>>lsm;
        lsm=userService.getUserByName(security_id,username);
        ArrayList<String> userIdList=new ArrayList<>();
        getUseridList(lsm,userIdList);
        JSONArray userInfo = new JSONArray();
        for(int i=0;i<lsm.size();i++)
        {
            JSONObject userInfoTemp = auditorService.getUserInfo(userIdList.get(i));
            String  reviewStatus=userService.getStatus(userIdList.get(i));
            reviewStatus = getStatusStringByStatusInt(reviewStatus);
            userInfoTemp.put("reviewStatus",reviewStatus);
            userInfo.add(userInfoTemp);

        }
        return userInfo;
    }

    @RequestMapping(value="/reviewer/getUserByDate", method=POST, produces = "application/json;charset=UTF-8")
    public JSONArray getUserByDate(@RequestParam(value = "start") String start,
                                    @RequestParam(value = "end") String end,
                                    @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                    @RequestParam(value = "size", defaultValue = "5")int pageSize,
                                    @RequestParam(value ="status")String status)  {
        String reviewerId= SessionUtil.getSessionAttribute().getString("employee_id");
      PageHelper.startPage(pageNum, pageSize,"user_id");
        List<Map<String,Object>>lsm;
        if(status.equals("0"))
        {
            lsm=reviewResultService.getReviewed(reviewerId,start,end);
            System.out.println(lsm.size());
        }
        else if(status.equals("1"))
        {
            lsm=reviewResultService.getReviewSuccess(reviewerId,start,end);

            System.out.println("getUserByDate");
            System.out.println("reviewerId:"+reviewerId);
            System.out.println("start:"+start);
            System.out.println("end:"+end);

        }
        else
        {
            lsm=reviewResultService.getReviewFail(reviewerId,start,end);
            System.out.println(lsm.size());
        }
        ArrayList<String> userIdList=new ArrayList<>();
        getUseridList(lsm,userIdList);
       // System.out.println(userIdList);
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<userIdList.size();i++) {
            JSONObject temp=auditorService.getUserInfo(userIdList.get(i));
            if(temp.get("reviewStatus").equals("6")){
                temp.remove("reviewStatus");
            temp.put("reviewStatus","未通过");}
            else {
                temp.remove("reviewStatus");
                temp.put("reviewStatus","通过");
            }
            jsonArray.add(temp);
        }      //  =reviewResultService.getReviewSuccess(reviewerId, start, end);
       // System.out.println(lsm);
        return jsonArray;

    }
   @RequestMapping(value="/reviewer/postResult", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject postResult(@RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "infoResult") String infoResult,
                                 @RequestParam(value = "gradeResult") String gradeResult,
                                 @RequestParam(value = "imageResult") String imageResult)    {
        String result_review;
        String status;
        String reviewerId= SessionUtil.getSessionAttribute().getString("employee_id");
        if(imageResult.equals("true")&&infoResult.equals("true")&&gradeResult.equals("true"))
        {
            status="7";
            result_review="审核通过";
         boolean result = accountAllocService.openAccount(Integer.parseInt(userId,10));
         if(!result){
             status="8";
             result_review="开户系统出错";
             reviewResultService.setReviewResultByUseridAndReviewerId(userId,reviewerId,result_review);
             userService.setUserStatus(userId,status);
             return CommonUtil.getJson(AuditorConstants.ERROR_MSG);
         }
        }
        else {
            status="6";
            result_review="审核未通过：";}
        if(imageResult.equals("false")){
            result_review+="照片审核未通过；";
        }
        if(infoResult.equals("false"))
        {
            result_review+="个人信息填写未通过；";
        }
        if(gradeResult.equals("false"))
        {
            result_review+="风险测评未通过：";
        }
        reviewResultService.setReviewResultByUseridAndReviewerId(userId,reviewerId,result_review);
        userService.setUserStatus(userId,status);
            return CommonUtil.getJson(AuditorConstants.SUCCESS_MSG);
       // else return CommonUtil.getJson(AuditorConstants.FAIL_MSG);
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
    private JSONObject getUserInfo(String user_id) {
        JSONObject userInfoTemp = auditorService.getUserInfo(user_id);
        JSONArray userInfo = new JSONArray();
        parseUserInfo(user_id, userInfoTemp, userInfo);
        JSONObject jsonObject = auditorService.getUserInfoUnreviewed(user_id);
        jsonObject.put("userInfo", userInfo);
        jsonObject.put("code", AuditorConstants.MUCH_MSG);
        return jsonObject;
    }
    private JSONArray getUserPair(List<Map<String,Object>> lsm) {
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> map : lsm) {
            JSONObject jsonObject = new JSONObject();

            if (map.containsKey("value")) {
                jsonObject.put("value", map.get("value"));
            }
            if (map.containsKey("address")) {
                jsonObject.put("address", map.get("address"));
            }

            jsonArray.add(jsonObject);
        }

        return jsonArray;
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
