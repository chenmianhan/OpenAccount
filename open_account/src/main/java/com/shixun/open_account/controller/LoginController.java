package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.UserService;
import com.shixun.open_account.util.CommonUtil;
import com.shixun.open_account.util.constants.LoginConstants;
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
    UserDao userDao;
    @Resource
    private UserService userService;
    @RequestMapping(value="/login", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject login(@RequestParam(value = "phone") String phone,
                       @RequestParam(value = "password") String password,@RequestParam(value = "role") String role) throws Exception {
        //int result=userService.checkLogin(phone,password);
        if(role.equals("0")){
            int result=userService.checkPhone(phone);
            if(result==0){
                try{
                    result= userService.addUser(phone,password);
                    return CommonUtil.getJson(LoginConstants.NEW_CODE);
                }
                catch(Exception e){
                    return CommonUtil.getJson(LoginConstants.ERROR_CODE);
                }


            }
            else {
                result=userService.checkLogin(phone,password);
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
          return CommonUtil.getJson(LoginConstants.OLD_CODE);
        }

    }
}
