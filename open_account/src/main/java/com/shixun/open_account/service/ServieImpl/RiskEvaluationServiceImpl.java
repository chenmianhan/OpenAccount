package com.shixun.open_account.service.ServieImpl;

import com.shixun.open_account.dao.RiskEvaluationDao;
import com.shixun.open_account.service.RiskEvaluationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import java.util.List;

/****
 *@author:hwy
 *@date: 2019/6/181007
 *@descrption:
 */
@Service
public class RiskEvaluationServiceImpl implements RiskEvaluationService {
    @Resource
    private RiskEvaluationDao riskEvaluationDao;
    @Override
    @Transactional
    public int addRiskEvaluation(int RE_id, String content,
                          String option_1, int mark_1,
                          String option_2, int mark_2,
                          String option_3, int mark_3,
                          String option_4, int mark_4,
                          String option_5, int mark_5,
                          boolean is_radio, String type) {
        return (riskEvaluationDao.addRiskEvaluation(RE_id, content, option_1, mark_1, option_2, mark_2, option_3, mark_3, option_4, mark_4, option_5, mark_5, is_radio, type));
    }

    public int deleteRiskEvaluation(int RE_id) {
        return riskEvaluationDao.deleteRiskEvaluation(RE_id);
    }

    public List<JSONObject> getRiskEvaluations() {
        return riskEvaluationDao.getRiskEvaluations();
    }
}
