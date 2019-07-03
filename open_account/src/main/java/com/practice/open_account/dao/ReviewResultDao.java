package com.practice.open_account.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/7/11425
 *@descrption:
 */
public interface ReviewResultDao {
    String getReviewMsg(@Param("user_id") String user_id);
    int addReviewResult(@Param("user_id")String user_id, @Param("auditor_id")String reviewerId, @Param("result")String result);
    String checkExitWaitForReviewByAuditor(@Param("auditor_id")String reviewerId);
    List<Map<String,Object>> getReviewSuccess(@Param("auditor_id")String auditor_id,
                                              @Param("start")String start,
                                              @Param("end")String end);
    int setReviewResultByUseridAndReviewerId(@Param("user_id")String user_id,
                                             @Param("auditor_id")String reviewerId,
                                             @Param("result")String result);
    List<Map<String,Object>> getReviewFail(@Param("auditor_id")String auditor_id,
                                           @Param("start")String start,
                                           @Param("end")String end);
}
