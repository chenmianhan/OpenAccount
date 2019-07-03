package com.practice.open_account.service;

import com.alibaba.fastjson.JSONObject;

/****
 *@author:cmh
 *@date: 2019/7/11134
 *@descrption:
 */
public interface ReviewResultService {
    JSONObject getReviewResult(String user_id);
    int addReviewResult(String user_id,String reviewerId,String result);
    String checkExitWaitForReviewByAuditor(String reviewerId);
}
