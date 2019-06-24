package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
    @Transactional
    public int checkLogin(String phone, String password)
    {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        try {
            currentUser.login(token);
            return 1;
        } catch (AuthenticationException e) {
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
}
