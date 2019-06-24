package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.SecurityDao;
import com.shixun.open_account.service.SecurityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/****
  *@author:zwz
  *@date: 2019/6/110947
  *@descrption:
  */

@Service
public class SecurityServiceImpl implements SecurityService{
    @Resource
    private SecurityDao securityDao;

   
    @Override
    public List<JSONObject> getSecurity(String province, String city)
    {
        return securityDao.getSecurity(province,city);
    }
    
    @Override
    public List<JSONObject> getSecurityAll()
    {
        return securityDao.getSecurityAll();
    }
}