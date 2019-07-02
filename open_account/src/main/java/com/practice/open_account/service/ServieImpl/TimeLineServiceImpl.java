package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.TimeLineDAO;
import com.practice.open_account.service.TimeLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public JSONArray getTimeLine(String usrId)
    {
        JSONArray arr=new JSONArray();
        String cusId=timeLineDao.getCusId(usrId);
        arr.addAll(timeLineDao.getTimeLine(cusId));
        JSONObject tmp=new JSONObject();
        JSONArray ans=new JSONArray();
        for(int i=0;i<arr.size();i++)
        {
            JSONObject tmp1=arr.getJSONObject(i);
            tmp.put("timestep",tmp1.getSqlDate("createtime"));
            tmp.put("message",tmp1.getString("message"));
            Float value=tmp1.getFloat("value");
            tmp.put("value",value);
            if(value<0)
                tmp.put("color","#F56C6C");
            else
                tmp.put("color","#67C23A");
            ans.add(tmp);
            tmp=new JSONObject();
        }
        return ans;
    }

    @Override
    public JSONArray getOptionalTimeLine(String usrId,String startTime,String endTime)
    {

        JSONArray arr=new JSONArray();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start=null;
        Date end=null;
        try {
            start = simpleDateFormat.parse(startTime);
            end = simpleDateFormat.parse(endTime);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        java.sql.Date sstart=new java.sql.Date(start.getTime());
        java.sql.Date eend=new java.sql.Date(end.getTime());
        String cusId=timeLineDao.getCusId(usrId);
        arr.addAll(timeLineDao.getOptionalTimeLine(cusId,sstart,eend));
        JSONObject tmp=new JSONObject();
        JSONArray ans=new JSONArray();
        for(int i=0;i<arr.size();i++)
        {
            JSONObject tmp1=arr.getJSONObject(i);
            tmp.put("timestep",tmp1.getSqlDate("createtime"));
            tmp.put("message",tmp1.getString("message"));
            Float value=tmp1.getFloat("value");
            tmp.put("value",value);
            if(value<0)
                tmp.put("color","#F56C6C");
            else
                tmp.put("color","#67C23A");
            ans.add(tmp);
            tmp=new JSONObject();
        }
        return ans;
    }
}
