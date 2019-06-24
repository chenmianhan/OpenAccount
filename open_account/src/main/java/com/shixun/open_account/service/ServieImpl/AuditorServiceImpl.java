package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.AuditorDAO;
import com.shixun.open_account.service.AuditorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/****
 *@author:cmh
 *@date: 2019/6/241040
 *@descrption:
 */
@Service
public class AuditorServiceImpl implements AuditorService {
    @Resource
    private AuditorDAO auditorDAO;
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
    public  int gettoReviewNum()
    {
        return auditorDAO.gettoReviewNum();
    }
    @Override
    @Transactional
    public int getreviewedNum( String auditor_id)
    {
        return auditorDAO.getreviewedNum(auditor_id);
    }

}
