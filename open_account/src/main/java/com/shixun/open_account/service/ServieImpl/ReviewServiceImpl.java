package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.ReviewResultDao;
import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/****
 *@author:cmh
 *@date: 2019/7/11136
 *@descrption:
 */
@Service
public class ReviewServiceImpl implements ReviewService {
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
}
