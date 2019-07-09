package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.AccountInfoService;
import com.practice.open_account.service.EmployeeService;
import com.practice.open_account.service.ReviewResultService;
import com.practice.open_account.service.UserService;
import com.practice.open_account.util.CommonUtil;
import com.practice.open_account.util.PasswordUtil;
import com.practice.open_account.util.SessionUtil;
import com.practice.open_account.util.UcpaasUtil.client.JsonReqClient;
import com.practice.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:cmh
 *@date: 2019/6/171549
 *@descrption:
 */
@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private ReviewResultService reviewResultService;
    @Resource
    private AccountInfoService accountInfoService;
    @Resource
    private EmployeeService employeeService;
    @RequestMapping(value="/login", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject login(@RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "role") String role) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        password = PasswordUtil.getMD5(password+account);
        if(session.getAttribute(LoginConstants.SESSION_USER_INFO)!=null)
        {
            currentUser.logout();
        }
        int result;
        if(role.equals("0")){
           result =userService.checkPhone(account);
            if(result==0){
                try{
                   int a= userService.addUser(account,password);
                   ///System.out.println("a:"+a);
                   if(a==1)
                   {
                       userService.checkLogin(account,password);
                    //System.out.println(SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO));
                    return CommonUtil.getJson(LoginConstants.NEW_CODE);}
                   else return CommonUtil.getJson(LoginConstants.ERROR_CODE);
                }
                catch(Exception e){
                    return CommonUtil.getJson(LoginConstants.ERROR_CODE);
                }
            }
            else {
                result=userService.checkLogin(account,password);
                if(result!=0)
                {
                    return CommonUtil.getJson(LoginConstants.OLD_CODE);
                }
                else
                {
                    return CommonUtil.getJson(LoginConstants.FAIL_CODE);
                }
            }
        }
        else
        {
            result=employeeService.login(account,password,role);
            if(result!=0)
            {
                return CommonUtil.getJson(LoginConstants.OLD_CODE);
            }
            else
            {
                return CommonUtil.getJson(LoginConstants.FAIL_CODE);
            }
        }


    }
    @RequestMapping(value="/logout", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject logout()
    {
        return userService.logout();
    }
    @RequestMapping(value="/getReviewResult", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getReviewResult() {
        String user_id= SessionUtil.getSessionAttribute().getString("user_id");
       return reviewResultService.getReviewResult(user_id);
    }
    @RequestMapping(value="/user", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getNetNameAndUserName() {
        String user_id=SessionUtil.getSessionAttribute().getString("user_id");

        return accountInfoService.getNetNameAndUserName(user_id);
    }
    @RequestMapping(value="/checkInvalid", method=POST, produces = "application/json;charset=UTF-8")
    public String checkInvalid() {
        JSONObject sessionAttribute=SessionUtil.getSessionAttribute();
        if(sessionAttribute==null)
       // System.out.println(user_id);
        return LoginConstants.INVALID_CODE;
        else return LoginConstants.VALID_CODE;
    }
    @RequestMapping(value="/askForCheckSum", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject  askForCheckSum(@RequestParam(value = "phone") String phone) {
        String checkNum=getFourRandom();
        SecurityUtils.getSubject().getSession().setAttribute(LoginConstants.SESSION_CHECKNUM, checkNum);
        System.out.println(SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_CHECKNUM).toString());
        String checkPhone=phone;
        JsonReqClient jsonReqClient=new JsonReqClient();
       String result= jsonReqClient.sendSms("37a490144f5a9c924d1bfe5901847973",
                            "57e9b612d3cc1303f59a41f6bb08efe3",
                "426324c3e49d4340bd71e1a47420b64f",
                "483287",
                checkNum,checkPhone);
       JSONObject jsonObject=JSONObject.parseObject(result);
       result=(String)jsonObject.get("code");
       System.out.println(result);
       JSONObject returnObject=new JSONObject();
       if(result.equals("000000"))
       {
           returnObject.put("code",LoginConstants.SMSSUCCESS);
       }
       else
           {
               returnObject.put("code", LoginConstants.SMSERROR);
           }
       return returnObject;

    }
    @RequestMapping(value="/checkNum", method=GET, produces = "application/json;charset=UTF-8")
    public JSONObject checkNum(@RequestParam(value ="checkNum")String checkNum) {
        JSONObject jsonObject=new JSONObject();
        if(checkNum.equals( SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_CHECKNUM)))
        {
            jsonObject.put("code",LoginConstants.CHECKNUMTRUE);
        }
        else
            {
                jsonObject.put("code",LoginConstants.CHECKNUMFALSE);
            }
        return jsonObject;
       // return SessionUtil.getSessionAttribute();
    }

    @RequestMapping(value="/checkPhone", method=GET, produces = "application/json;charset=UTF-8")
    public JSONObject checkPhone(@RequestParam(value ="phone")String phone) {
        JSONObject jsonObject=new JSONObject();
       int  result =userService.checkPhone(phone);
      // System.out.println(result);
        if(result==0){
            jsonObject.put("code",LoginConstants.USERNOTEXIST);
        }
        else
        {
            jsonObject.put("code",LoginConstants.USEREXIST);
        }
        return jsonObject;
        // return SessionUtil.getSessionAttribute();
    }

    @RequestMapping(value="/checkPassword", method=GET, produces = "application/json;charset=UTF-8")
    public JSONObject  checkPassword(@RequestParam(value ="phone")String phone,
                                     @RequestParam(value ="password")String password)
    {
        JSONObject jsonObject=new JSONObject();
        password=PasswordUtil.getMD5(password+phone);
       int result= userService.checkPassword(phone, password);
        if(result==0)
        {
            jsonObject.put("code",LoginConstants.PASSWORDFALSE);
        }
        else
            {
                jsonObject.put("code",LoginConstants.PASSWORDTRUE);
            }
        return jsonObject;
    }

    @RequestMapping(value="/updatePassword", method=GET, produces = "application/json;charset=UTF-8")
    public JSONObject updatePassword(@RequestParam(value ="phone")String phone,
                                     @RequestParam(value ="newPassword")String password)
    {
        if(phone.equals(""))
        {
            phone=SessionUtil.getSessionAttribute().getString("phone");
        }
        System.out.println(phone);
        JSONObject jsonObject=new JSONObject();
        password=PasswordUtil.getMD5(password+phone);
       // System.out.println(phone);
        int result= userService.updatePassword(phone, password);
       // System.out.println(phone);
        if(result==0)
        {
            jsonObject.put("code",LoginConstants.EXCEPTION);
        }
        else
        {
            jsonObject.put("code",LoginConstants.UPDATESUCCESS);
        }
        return jsonObject;
    }
    @RequestMapping(value="/test", method=GET, produces = "application/json;charset=UTF-8")
    public JSONObject  test() {
       return SessionUtil.getSessionAttribute();
    }

    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    public  String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0"+fourRandom;
        }
        return fourRandom;
    }

}
