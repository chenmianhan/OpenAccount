package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.EmployeeService;
import com.practice.open_account.service.ReviewResultService;
import com.practice.open_account.service.UserService;
import com.practice.open_account.util.CommonUtil;
import com.practice.open_account.util.SessionUtil;
import com.practice.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private EmployeeService employeeService;
    @RequestMapping(value="/login", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject login(@RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "role") String role) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        //System.out.println("session.getAttribute(LoginConstants.SESSION_USER_INFO)"+session.getAttribute(LoginConstants.SESSION_USER_INFO));
        if(session.getAttribute(LoginConstants.SESSION_USER_INFO)!=null)
        {

         //   System.out.println("session is not empty");
            // (JSONObject) ;
            currentUser.logout();
        }
        int result;
        if(role.equals("0")){
           result =userService.checkPhone(account);
            if(result==0){
                try{
                    userService.addUser(account,password);
                    userService.checkLogin(account,password);
                  //System.out.println(SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO));
                    return CommonUtil.getJson(LoginConstants.NEW_CODE);
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
    @RequestMapping(value="/test", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject test() {
        Session session = SecurityUtils.getSubject().getSession();
        return (JSONObject) session.getAttribute(LoginConstants.SESSION_USER_INFO);
}
    @RequestMapping(value="/getReviewResult", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getReviewResult() {
        String user_id= SessionUtil.getSessionAttribute().getString("user_id");
       return reviewResultService.getReviewResult(user_id);
    }

}
