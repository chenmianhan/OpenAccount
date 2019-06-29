package com.shixun.open_account.service.ServieImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.AccountDisplayDAO;
import com.shixun.open_account.entity.AccountCurrency;
import com.shixun.open_account.service.AccountDisplayService;

@Service
public class AccountDisplayServiceImpl implements AccountDisplayService {
	@Autowired
	private AccountDisplayDAO accountDisplayDAO;
	@Override
	public String getCustomerIdByUserId(int user_id) {
		return accountDisplayDAO.getCustomerIdByUserId(user_id);
	}
	@Override
	public List<JSONObject> getFundAccountByCustomerId(String customer_id) {
		return accountDisplayDAO.getFundAccountByCustomerId(customer_id);
	}
	@Override
	public List<JSONObject> getCurrencyByFundId(String fund_id) {
		return accountDisplayDAO.getCurrencyByFundId(fund_id);
	}
	
}
