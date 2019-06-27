package com.shixun.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.AuditorDAO;
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
        //System.out.println(user_id);
        JSONObject temp=auditorDAO.getUserInfo(user_id);
       // System.out.println();
        String openDate=auditorDAO.getOpenDate(user_id);

        temp.put("accTime",openDate);
       // System.out.println(temp);
        return temp;

    }
    @Override
    public int insertEmployee(
    		String employee_account, 
    		String employee_password,
    		String employee_type,
    		String employee_name
    		) {
    	return auditorDAO.insertEmployee(employee_account, employee_password, employee_type, employee_name);
    }
    @Override
    public int insertAuditor(int security_id, int auditor_id) {
    	return auditorDAO.insertAuditor(security_id, auditor_id);
    }
    
}
