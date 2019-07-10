package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.config.shiro.common.UserToken;
import com.practice.open_account.service.UserService;
import com.practice.open_account.util.CommonUtil;
import com.practice.open_account.util.constants.LoginConstants;
import com.practice.open_account.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        try{
        return userDao.addUser(phone,password);
    }catch (Exception e)
        {
            e.printStackTrace();
        }return -1;
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
    @Override
    @Transactional
    public  List<Map<String,Object>> getWaitForReview(String security_id,
                                                      String start,
                                                      String end)
    {
        try {
          return   userDao.getWaitForReview(security_id, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    @Transactional
    public int setUserStatus(String user_id, String status)
    {
        return userDao.setUserStatus(user_id, status);
    }
    @Override
    @Transactional
    public String getStatus(String user_id)
    {
        return userDao.getStatus(user_id);
    }
    @Override
    @Transactional
    public  List<Map<String, Object>> getUserByName(String security_id,String name)
    {
        try{
            return userDao.getUserByName(security_id,name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      return null;
    }

    @Override
    @Transactional
    public int checkPassword(String phone, String password)
    {
        try {
            return userDao.checkPassword(phone, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    @Transactional
    public int updatePassword(String phone, String password)
    {
       // System.out.println(phone);
        try{
            return userDao.updatePassword(phone, password);
        }
        catch (Exception e)
        {
           // System.out.println(phone);
            e.printStackTrace();
        }
        return 0;
    }
}
