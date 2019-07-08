package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.AccountInfoService;
import com.practice.open_account.service.EmployeeService;
import com.practice.open_account.service.ReviewResultService;
import com.practice.open_account.service.UserService;
import com.practice.open_account.util.CommonUtil;
import com.practice.open_account.util.PasswordUtil;
import com.practice.open_account.util.SessionUtil;
import com.practice.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @RequestMapping(value="/test", method=GET, produces = "application/json;charset=UTF-8")
    public JSONObject test() {

        return SessionUtil.getSessionAttribute();

    }
}
