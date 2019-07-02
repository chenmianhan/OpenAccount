package com.practice.open_account.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface AccountDisplayDAO {
	String getCustomerIdByUserId(int user_id);
	List<JSONObject> getFundAccountByCustomerId(String customer_id);
	List<JSONObject> getCurrencyByFundId(String fund_id);
}
