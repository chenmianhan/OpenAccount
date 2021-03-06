package com.practice.open_account.service.ServieImpl;

import com.practice.open_account.dao.GradeDao;
import com.practice.open_account.dao.RiskEvaluationDao;
import com.practice.open_account.service.GradeService;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import javax.annotation.Resource;

/****
  *@author:zwz
  *@date: 2019/6/110947
  *@descrption:
  */

@Service
public class GradeServiceImpl implements GradeService{
    @Resource
    private GradeDao gradeDao;
    @Resource
    private RiskEvaluationDao riskEvaluationDao;
    @Override
    public String getGradeName(int mark)
    {
        return gradeDao.getGradeName(mark);
    }

    @Override
    public void deleteGradeTable()
    {
        gradeDao.deleteGradeTable();
    }

    @Override
    public void addGrade(String grade,int mini,int max)
    {
        gradeDao.addGrade(grade,mini,max);
    }

    @Override
    public int getMark(int re_id, JSONArray answer)
    {
        int maxmark = 0;
        for(int i = 0;i<answer.size();i++)
        {
            int option = answer.getInteger(i);
            int rec=riskEvaluationDao.getMark(re_id,option);
            if(rec > maxmark) {
                maxmark = rec;
            }
        }
        return maxmark;
    }
}