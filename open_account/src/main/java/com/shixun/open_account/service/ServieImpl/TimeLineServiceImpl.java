package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.TimeLineDAO;
import com.shixun.open_account.service.TimeLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/****
  *@author:zwz
  *@date: 2019/6/280947
  *@descrption:
  */


@Service
public class TimeLineServiceImpl implements TimeLineService{
    @Resource
    private TimeLineDAO timeLineDao;

    @Override
    public JSONArray getTimeLine(String cusId)
    {
        JSONArray arr=new JSONArray();
        arr.addAll(timeLineDao.getTimeLine(cusId));
        JSONObject tmp=new JSONObject();
        JSONArray ans=new JSONArray();
        for(int i=0;i<arr.size();i++)
        {
            JSONObject tmp1=arr.getJSONObject(i);
            tmp.put("timstep",tmp1.getSqlDate("createtime"));
            tmp.put("message",tmp1.getString("message"));
            Float value=tmp1.getFloat("value");
            tmp.put("value",value);
            if(value<0)
                tmp.put("color","#F56C6C");
            else
                tmp.put("color","67C23A");
            ans.add(tmp);
            tmp=new JSONObject();
        }
        return ans;
    }
}
