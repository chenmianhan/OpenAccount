package com.practice.open_account.service.ServieImpl;

import java.util.List;

import com.practice.open_account.service.AccountDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.AccountDisplayDAO;
import com.practice.open_account.entity.FundAccount;

@Service
public class AccountDisplayServiceImpl implements AccountDisplayService {
	@Autowired
	private AccountDisplayDAO accountDisplayDAO;
	@Override
	public String getCustomerIdByUserId(int user_id) {
		return accountDisplayDAO.getCustomerIdByUserId(user_id);
	}
	@Override
	public List<FundAccount> getFundAccountByCustomerId(String customer_id) {
		try {return accountDisplayDAO.getFundAccountByCustomerId(customer_id);}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<JSONObject> getCurrencyByFundId(String fund_id) {
		return accountDisplayDAO.getCurrencyByFundId(fund_id);
	}
	@Override
	public String getMaxFundId() {
		// TODO Auto-generated method stub
		return accountDisplayDAO.getMaxFundId();
	}
	@Override
	public int addFundAccount(FundAccount fundAccount) {
		// TODO Auto-generated method stub
		return accountDisplayDAO.addFundAccount(fundAccount);
	}
	@Override
	public int deleteFundAccount(String fund_id) {
		// TODO Auto-generated method stub
		return accountDisplayDAO.deleteFundAccount(fund_id);
	}
	@Override
	//@Transactional
	public  String  getTradeType( String user_id)
	{
		try {return  accountDisplayDAO.getTradeType(user_id);}
		catch (Exception e)
		{e.printStackTrace();}
		return null;
	}
}
