package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.UserService;
import com.shixun.open_account.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
                       @RequestParam(value = "password") String password) throws Exception {
        //int result=userService.checkLogin(phone,password);
        int result=userService.checkPhone(phone);
        if(result==0){
           result= userService.addUser(phone,password);
           return CommonUtil.successJson("成功注册并登录");
        }
        else {
            result=userService.checkLogin(phone,password);
            if(result!=0)
            {
                return CommonUtil.successJson("成功登录");
            }
            else
                {
                    return CommonUtil.successJson("用户名或密码错误");
                }
        }
    }
}
