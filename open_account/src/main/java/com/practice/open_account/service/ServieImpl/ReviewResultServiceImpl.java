package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.ReviewResultDao;
import com.practice.open_account.service.ReviewResultService;
import com.practice.open_account.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/7/11136
 *@descrption:
 */
@Service
public class ReviewResultServiceImpl implements ReviewResultService {
    @Resource
    ReviewResultDao reviewResultDao;
    @Resource
    UserDao userDao;
    @Override
    @Transactional
    public JSONObject getReviewResult(String user_id) {System.out.println(getClass()+user_id);
        JSONObject jsonObject=new JSONObject();
        try
        {
            String msg=reviewResultDao.getReviewMsg(user_id);
            String status=userDao.getStatus(user_id);
            jsonObject.put("msg",msg);
            jsonObject.put("status",status);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }
    @Override
    @Transactional
    public  int addReviewResult(String user_id,String reviewerId,String result)
    {
        try {
            return reviewResultDao.addReviewResult(user_id, reviewerId, result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    @Transactional
    public String checkExitWaitForReviewByAuditor(String reviewerId)
    {
        try{
            return reviewResultDao.checkExitWaitForReviewByAuditor(reviewerId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    @Transactional
    public  List<Map<String,Object>> getReviewSuccess(String auditor_id, String start, String end)
    {
        try{
            return reviewResultDao.getReviewSuccess(auditor_id,start,end);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    @Transactional
    public  List<Map<String,Object>> getReviewFail(String auditor_id, String start, String end)
    {
        try{
            return reviewResultDao.getReviewFail(auditor_id,start,end);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
