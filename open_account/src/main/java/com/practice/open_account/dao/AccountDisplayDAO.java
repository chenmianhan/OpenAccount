package com.practice.open_account.dao;

import java.sql.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.FundAccount;
import org.apache.ibatis.annotations.Param;

public interface AccountDisplayDAO {
	String getCustomerIdByUserId(int user_id);
	List<FundAccount> getFundAccountByCustomerId(String customer_id);
	List<JSONObject> getCurrencyByFundId(String fund_id);
	String getMaxFundId();
	int addFundAccount(FundAccount fundAccount);
	int deleteFundAccount(String fund_id);
	String  getTradeType(@Param("user_id") String user_id);
	JSONObject getAccountInfo(int user_id);
	JSONObject getAddressByAId(int aid);
	int updateUserInfo(int user_id, String name, String profession, Date valDateStart, Date valDateEnd, String idCardAuthority, String education, String email);
	int updateAddress(int aid, String province, String city, String street, String detail);
	int recharge(String fund_id, double amount);
	int withdrawal(String fund_id, double amount);
	int checkFund(String trade_password,String user_id);
}
