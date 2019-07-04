package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/****
 *@author:cmh
 *@date: 2019/7/11134
 *@descrption:
 */
public interface ReviewResultService {
    JSONObject getReviewResult(String user_id);
    int addReviewResult(String user_id,String reviewerId,String result);
    int setReviewResultByUseridAndReviewerId(String user_id,String reviewerId,String result);
    String checkExitWaitForReviewByAuditor(String reviewerId);
    List<Map<String,Object>> getReviewSuccess(String auditor_id, String start, String end);
    List<Map<String,Object>> getReviewFail(String auditor_id, String start, String end);
    List<Map<String,Object>> getReviewed(String auditor_id, String start, String end);
}
