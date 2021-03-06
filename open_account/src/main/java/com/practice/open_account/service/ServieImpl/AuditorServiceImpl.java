package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.AuditorDAO;
import com.practice.open_account.dao.GradeDao;
import com.practice.open_account.entity.Employee;
import com.practice.open_account.service.AuditorService;
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
   public String getOneUserToReview(String security_id)
   {
       try{
           //System.out.println(getClass()+auditorDAO.getOneUserToReview( security_id));
           return auditorDAO.getOneUserToReview( security_id);
       }
       catch (Exception e)
       {
           e.printStackTrace();
           return null;
       }

   }

    @Override
    @Transactional
    public List<Map<String,Object>>  getUserIdByTime(String auditor_id,
                                       String start,
                                       String end) {
        return auditorDAO.getUserIdByTime(auditor_id,start,end);
    }
    @Override
    @Transactional
    public JSONObject getUserInfo(String user_id)
    {
        JSONObject temp=null;
        try {
             temp= auditorDAO.getUserInfo(user_id);
             //System.out.println(temp);
            String openDate = auditorDAO.getReviewDate(user_id);
        temp.put("accTime",openDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       // System.out.println(temp);
        return temp;

    }
    /*@Override
    @Transactional
    public List<Map<String,Object>> getWaitForReview(String security_id)
    {
        List<Map<String,Object>> lsm=null;
        try {
            lsm= auditorDAO.getWaitForReview(security_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return lsm;
    }*/
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
    public  List<Map<String, Object>> getAllUserByAuditorId( String security_id, String auditor_id, String start, String end)
    {
        try
        {
            return auditorDAO.getAllUserByAuditorId(security_id, auditor_id, start, end);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public int insertEmployee(Employee employee) throws Exception
    {
    	try {
    		auditorDAO.insertEmployee(employee);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("duplicate key");
		}
    	return 1;
    }
    @Override
    public int insertAuditor(int security_id, int auditor_id) {
    	return auditorDAO.insertAuditor(security_id, auditor_id);
    }


    @Override
    public int updateEmployee(int employee_id, String employee_account, String employee_password, String employee_type,
                              String employee_name) {
        return auditorDAO.updateEmployee(employee_id, employee_account, employee_password, employee_type, employee_name);
    }
    @Override
    public int updateAuditor(int security_id, int auditor_id) {
        return auditorDAO.updateAuditor(security_id, auditor_id);
    }


}
