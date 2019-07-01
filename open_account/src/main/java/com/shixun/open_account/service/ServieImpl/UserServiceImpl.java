package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.config.shiro.common.UserToken;
import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.UserService;
import com.shixun.open_account.util.CommonUtil;
import com.shixun.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/****
 *@author:cmh
 *@date: 2019/6/171528
 *@descrption:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    @Transactional
    public int checkPhone(String phone)
    {
        return userDao.checkPhone(phone);
    }
    @Override
    public int checkLogin(String account, String password)
    {
        Subject currentUser = SecurityUtils.getSubject();
        UserToken token = new UserToken(account, password,"0");
        try {
            currentUser.login(token);
            return 1;
        } catch (AuthenticationException e) {
           e.printStackTrace();
            return 0;
        }
    }


    @Override
    @Transactional
    public int addUser( String phone, String password)
    {
        return userDao.addUser(phone,password);
    }

    @Override
    //@Transactional
    public JSONObject getUser(String phone, String password) { return   userDao.getUser(phone, password); }

    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            return CommonUtil.getJson(LoginConstants.LOGOUT_ERROR_CODE);
        }
        return CommonUtil.getJson(LoginConstants.LOGOUT_CODE);
    }
}
