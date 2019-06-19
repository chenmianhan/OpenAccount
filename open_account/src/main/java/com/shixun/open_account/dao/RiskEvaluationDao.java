package com.shixun.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/****
 *@author:hwy
 *@date: 2019/6/180911
 *@descrption:
 */

public interface RiskEvaluationDao {
    int addRiskEvaluation(@Param("RE_id") int RE_id, @Param("content") String content,
                          @Param("option_1") String option_1, @Param("mark_1") int mark_1,
                          @Param("option_2") String option_2, @Param("mark_2") int mark_2,
                          @Param("option_3") String option_3, @Param("mark_3") int mark_3,
                          @Param("option_4") String option_4, @Param("mark_4") int mark_4,
                          @Param("option_5") String option_5, @Param("mark_5") int mark_5,
                          @Param("is_radio") boolean is_radio, @Param("type") String type);
    int deleteRiskEvaluation(@Param("RE_id") int RE_id);
    List<JSONObject> getRiskEvaluations();
    int getMark(@Param("RE_id") int RE_id, @Param("option_id") int option_id);
}
