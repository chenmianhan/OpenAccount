package com.practice.open_account.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.FundAccount;


public interface AccountDisplayService {
	String getCustomerIdByUserId(int user_id);
	List<FundAccount> getFundAccountByCustomerId(String customer_id);
	List<JSONObject> getCurrencyByFundId(String fund_id);
	String getMaxFundId();
	int addFundAccount(FundAccount fundAccount);
	int deleteFundAccount(String fund_id);
	String  getTradeType( String user_id);
	JSONObject updateUserInfo(JSONObject info, int user_id);
	int recharge(JSONObject js);
	int withdrawal(JSONObject js);
}
