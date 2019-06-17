package com.shixun.open_account.service.ServieImpl;

import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.UserService;
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
        return userDao.checkLogin(phone, password);
    }
    @Override
    @Transactional
    public int addUser( String phone, String password)
    {
        return userDao.addUser(phone,password);
    }
}
