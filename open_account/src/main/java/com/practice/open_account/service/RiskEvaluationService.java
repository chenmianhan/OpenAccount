package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

/****
 *@author:hwy
 *@date: 2019/6/181007
 *@descrption:
 */
public interface RiskEvaluationService {
    int addRiskEvaluation(int RE_id, String content,
                          String option_1, int mark_1,
                          String option_2, int mark_2,
                          String option_3, int mark_3,
                          String option_4, int mark_4,
                          String option_5, int mark_5,
                          boolean is_radio, String type);

    int deleteRiskEvaluation(int RE_id);
    List<JSONObject> getRiskEvaluations();
}
