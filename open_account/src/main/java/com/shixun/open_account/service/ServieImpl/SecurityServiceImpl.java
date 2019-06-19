package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.DemoDao;
import com.shixun.open_account.dao.GradeDao;
import com.shixun.open_account.dao.SecurityDao;
import com.shixun.open_account.dao.RiskEvaluationDao;
import com.shixun.open_account.service.DemoService;
import com.shixun.open_account.service.GradeService;
import com.shixun.open_account.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
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
        JSONObject tmp=new JSONObject();
        return securityDao.getSecurity(province,city);
    }
}