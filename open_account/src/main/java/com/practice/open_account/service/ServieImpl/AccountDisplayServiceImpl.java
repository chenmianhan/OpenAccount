package com.practice.open_account.service.ServieImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.practice.open_account.service.AccountDisplayService;
import org.apache.http.annotation.Obsolete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.AccountDisplayDAO;
import com.practice.open_account.dao.AccountInfoDAO;
import com.practice.open_account.entity.FundAccount;

import javax.annotation.Resource;

@Service
public class AccountDisplayServiceImpl implements AccountDisplayService {
	@Autowired
	private AccountDisplayDAO accountDisplayDAO;
	@Override
	@Transactional
	public String getCustomerIdByUserId(int user_id) {
		return accountDisplayDAO.getCustomerIdByUserId(user_id);
	}
	@Override
	@Transactional
	public List<FundAccount> getFundAccountByCustomerId(String customer_id) {
		try {return accountDisplayDAO.getFundAccountByCustomerId(customer_id);}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	@Override
	@Transactional
	public List<JSONObject> getCurrencyByFundId(String fund_id) {
		return accountDisplayDAO.getCurrencyByFundId(fund_id);
	}
	@Override
	@Transactional
	public String getMaxFundId() {
		// TODO Auto-generated method stub
		return accountDisplayDAO.getMaxFundId();
	}
	@Override
	@Transactional
	public int addFundAccount(FundAccount fundAccount) {
		// TODO Auto-generated method stub
		return accountDisplayDAO.addFundAccount(fundAccount);
	}
	@Override
	@Transactional
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
	@Override
	public JSONObject updateUserInfo(JSONObject info, int user_id) {
		JSONObject res = new JSONObject();
		JSONObject preInfo = accountDisplayDAO.getAccountInfo(user_id);
		System.out.println(preInfo);
		JSONObject preContactAddress = accountDisplayDAO.getAddressByAId(preInfo.getInteger("contact_address_id"));
		JSONObject prePostAddress = accountDisplayDAO.getAddressByAId(preInfo.getInteger("postal_address_id"));

		String name = info.getString("name");
		String profession = info.getString("profession");
		Date valDateStart = info.getSqlDate("valDateStart");
		Date valDateEnd = info.getSqlDate("valDateEnd");
		String idCardAuthority = info.getString("idCardAuthority");
		String education = info.getString("education");
		String email = info.getString("email");
		JSONObject contactAddress = info.getJSONObject("contactAddress");
		String contactAddressProvince = contactAddress.getString("province");
		String contactAddressCity = contactAddress.getString("city");
		String contactAddressStreet = contactAddress.getString("street");
		String contactAddressDetail = contactAddress.getString("detail");
		JSONObject postAddress = info.getJSONObject("postAddress");
		String postAddressProvince = postAddress.getString("province");
		String postAddressCity = postAddress.getString("city");
		String postAddressStreet = postAddress.getString("street");
		String postAddressDetail = postAddress.getString("detail");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");



		if (name == null) {
			name = preInfo.getString("name");
		}
		if (profession == null) {
			profession = preInfo.getString("profession");
		}
		if (valDateStart == null) {
			valDateStart = preInfo.getSqlDate("id_issuance_date");
		}
		if (valDateEnd == null) {
			valDateEnd = preInfo.getSqlDate("id_overdue_date");
		}
		if (idCardAuthority == null) {
			idCardAuthority = preInfo.getString("id_licensing_authority");
		}
		if (education == null) {
			education = preInfo.getString("education");
		}
		if (email == null) {
			email = preInfo.getString("email");
		}
		if (contactAddressProvince == null) {
			contactAddressProvince = preContactAddress.getString("province");
		}
		if (contactAddressCity == null) {
			contactAddressCity = preContactAddress.getString("city");
		}
		if (contactAddressStreet == null) {
			contactAddressStreet = preContactAddress.getString("street");
		}
		if (contactAddressDetail == null) {
			contactAddressDetail = preContactAddress.getString("detail");
		}
		if (postAddressProvince == null) {
			postAddressProvince = prePostAddress.getString("province");
		}
		if (postAddressCity == null) {
			postAddressCity = prePostAddress.getString("city");
		}
		if (postAddressStreet == null) {
			postAddressStreet = prePostAddress.getString("street");
		}
		if (postAddressDetail == null) {
			postAddressDetail = prePostAddress.getString("detail");
		}

		int status;
		status = accountDisplayDAO.updateUserInfo(user_id, name, profession, valDateStart, valDateEnd, idCardAuthority, education, email);
		status = status * accountDisplayDAO.updateAddress(preInfo.getInteger("contact_address_id"), contactAddressProvince, contactAddressCity, contactAddressStreet, contactAddressDetail);
		status = status * accountDisplayDAO.updateAddress(preInfo.getInteger("postal_address_id"), postAddressProvince, postAddressCity, postAddressStreet, postAddressDetail);

		res.put("code", 101-status);
		return res;

	}

	@Override
	public int recharge(JSONObject js) {
		String fund_id = js.getString("fund_id");
		double amount = js.getDouble("amount");
		int status = accountDisplayDAO.recharge(fund_id, amount);
		String customer_id = accountDisplayDAO.getCustomerId(fund_id);
		Timestamp datetime = new Timestamp(System.currentTimeMillis());
		return status * accountDisplayDAO.addTransaction(customer_id, amount, "充值", datetime);
	}

	@Override
	public int withdrawal(JSONObject js) {
		String fund_id = js.getString("fund_id");
		double amount = js.getDouble("amount");
		List<JSONObject> currencyList = accountDisplayDAO.getCurrencyByFundId(fund_id);
		double balance = currencyList.get(0).getDouble("balance");
		if (amount > balance) {
			return 0;
		}
		int status = accountDisplayDAO.withdrawal(fund_id, amount);
		String customer_id = accountDisplayDAO.getCustomerId(fund_id);
		Timestamp datetime = new Timestamp(System.currentTimeMillis());
		return status * accountDisplayDAO.addTransaction(customer_id, -amount, "提现", datetime);
	}
	@Override
	public int checkFund(String trade_password,String user_id)
	{
		try{
			int i= accountDisplayDAO.checkFund(trade_password, user_id);
			System.out.println(i);
			return  i;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
    @Override
    public  String getFundId(String bank_account)
    {
        return accountDisplayDAO.getFundId(bank_account);
    }
    @Override
    public int addCurrency(String fund_id,
                    String currency_type,
                    String balance)
    {
    	try{
        return accountDisplayDAO.addCurrency(fund_id, currency_type, balance);}
    	catch (Exception e)
		{
			e.printStackTrace();
		}
    	return 0;
    }
}
