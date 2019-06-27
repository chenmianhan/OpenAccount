package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.AuditorDAO;
import com.shixun.open_account.dao.GradeDao;
import com.shixun.open_account.service.AuditorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/6/241040
 *@descrption:
 */
@Service
public class AuditorServiceImpl implements AuditorService {
    @Resource
    private AuditorDAO auditorDAO;
    @Resource
    private GradeDao gradeDao;
    @Override
    @Transactional
    public String getSecutityIdbyAuditorId( String auditor_id)
    {
        return auditorDAO.getSecutityIdbyAuditorId(auditor_id);
    }
    @Override
    @Transactional
    public JSONObject getSecurity(String security_id)
    {
        return auditorDAO.getSecurity(security_id);
    }
    @Override
    @Transactional
    public  int gettoReviewNum(Integer type,String security_id)
    {
        if(type==0)
            return auditorDAO.gettoReviewNum_N(security_id);
        else  return auditorDAO.gettoReviewNum_S(security_id);
    }
    @Override
    @Transactional
    public int getreviewedNum( String auditor_id)
    {
        return auditorDAO.getreviewedNum(auditor_id);
    }

    @Override
    @Transactional
    public List<Map<String,Object>>  getUserIdByTime(String auditor_id,
                                       String start,
                                       String end)
    {
        return auditorDAO.getUserIdByTime(auditor_id,start,end);
    }

    @Override
    @Transactional
    public JSONObject getUserInfo(String user_id)
    {
        JSONObject temp=auditorDAO.getUserInfo(user_id);
        String openDate=auditorDAO.getOpenDate(user_id);
        temp.put("accTime",openDate);
        return temp;

    }
    @Override
    @Transactional
    public JSONObject getUserInfoUnreviewed(String user_id)
    {

        JSONObject temp1=auditorDAO.getOtherInfo(user_id);
        String grade=gradeDao.getGradeName(Integer.parseInt(temp1.getString("userGrade"),10));
        temp1.put("userType",grade);
        return temp1;

    }
    @Override
    @Transactional
    public List<Map<String,Object>>  gettoReviewUser_List(Integer type,String security_id)
    {
        if(type==0)
            return auditorDAO.gettoReviewUser_List_N(security_id);
        else
            return  auditorDAO.gettoReviewUser_List_S(security_id);
    }

}
