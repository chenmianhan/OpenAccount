package com.practice.open_account.dao;

import org.apache.ibatis.annotations.Param;

/****
 *@author:cmh
 *@date: 2019/7/11425
 *@descrption:
 */
public interface ReviewResultDao {
    String getReviewMsg(@Param("user_id") String user_id);
    int addReviewResult(@Param("user_id")String user_id, @Param("auditor_id")String reviewerId, @Param("result")String result);
    String checkExitWaitForReviewByAuditor(@Param("auditor_id")String reviewerId);

}
