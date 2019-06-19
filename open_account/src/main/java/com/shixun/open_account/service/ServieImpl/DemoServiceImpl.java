package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.DemoDao;
import com.shixun.open_account.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/****
 *@author:cmh
 *@date: 2019/6/110947
 *@descrption:
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private DemoDao demoDao;
    @Override
    public JSONObject getDemo(String id)
    {
        return demoDao.getDemo(id);
    }
    @Override
    public int addDemo( String id,String name,String age)
    {
        return  demoDao.addDemo(id,name,age);
    }
    @Override
    public int updateDemo( String id,String name, String age)
    {
        return  demoDao.updateDemo(id,name,age);
    }

}
