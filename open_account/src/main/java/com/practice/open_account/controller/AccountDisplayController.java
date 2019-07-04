package com.practice.open_account.controller;

import java.util.List;

import com.practice.open_account.service.AccountDisplayService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.FundAccount;
import com.practice.open_account.service.AccountInfoService;
import com.practice.open_account.service.SecurityService;
import com.practice.open_account.util.constants.LoginConstants;

@RestController
public class AccountDisplayController {
	@Autowired
	private AccountDisplayService accountDisplayService;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private SecurityService securityService;
	@GetMapping(value = "/account/accountDisplay", produces = "application/json;charset=UTF-8")
	public JSONObject accountDisplay() {
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int  user_id = sessonJsonObject.getIntValue("user_id");
		String customer_id = accountDisplayService.getCustomerIdByUserId(user_id);
		List<FundAccount> data = accountDisplayService
				.getFundAccountByCustomerId(customer_id
						);
		JSONObject res = new JSONObject();
		//	put sub elements into res
		res.put("primaryAccount", new JSONObject(3));
		res.put("secondaryAccount", new JSONArray());
		res.put("netPoint", new JSONObject());
		
		JSONArray array = res.getJSONArray("secondaryAccount");
		//	input
		for(int i=0;i<data.size();i++) {
			//	primaryAccount
			if(i==0) {
				JSONObject temp = res.getJSONObject("primaryAccount");
				temp.put("id",Integer.valueOf(0));
				temp.put("cardID", data.get(0).getBank_account());
				List<JSONObject> currencyList = 
						accountDisplayService.getCurrencyByFundId(
								data.get(0).getFund_id()
								);
				temp.put("balance", currencyList.get(0));
			}
			//	secondaryAccount
			else {
				JSONObject temp = new JSONObject(3);
				temp.put("id", Integer.valueOf(i));
				temp.put("cardID", data.get(i).getBank_account());
				List<JSONObject> currencyList = 
						accountDisplayService.getCurrencyByFundId(
								data.get(i).getFund_id()
								);
				if(currencyList.size()==0) {
					JSONObject object = new JSONObject();
					object.put("currency_type", "¥");
					object.put("balance", 0);
					currencyList.add(object);
				}
				temp.put("balance", currencyList.get(0));
				array.add(temp);
			}
		}
		//	input security info
		AccountInfo info = accountInfoService.getAccountInfoByUserId(user_id);
		Integer security_id = info.getSecurity_id();
		res.getJSONObject("netPoint").put("netpoint", 
				security_id==null?"":
				securityService.getSecurityBySecurityId(security_id).getString("name"));
		return res;
	}
	
	@PostMapping(value = "/account/addFundAccount",produces = "application/json;charset=UTF-8")
	public int addFundAccount(@RequestBody JSONObject jsonObject) {
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		System.out.println(sessonJsonObject);
		if((sessonJsonObject.getString("status")).equals("4")==false) {
			//	get user_id from session
			int user_id = sessonJsonObject.getIntValue("user_id");
			String customer_id = accountDisplayService.getCustomerIdByUserId(user_id);
			if(customer_id==null) return 0;
			//	get max fund_id in database
			String maxFundId = accountDisplayService.getMaxFundId();
			//	get next fund_id
			//	clear the 0s before fund_id String
			StringBuilder number = new StringBuilder(12);
			StringBuilder prefix = new StringBuilder(12);
			int index = 0;
			while(index<maxFundId.length() ) {
				if(maxFundId.charAt(index)=='u'||maxFundId.charAt(index)=='0') {
					index++;
					continue;
				}
				else {
					while(index<maxFundId.length()) {
						number.append(maxFundId.charAt(index));
						index++;
					}
				}
			}
			number = new StringBuilder(String.valueOf(1+Integer.valueOf(number.toString())));
			//	input prefix of next fund_id
			prefix.append('u');
			for(int i=number.length()+2;i<=12;i++) {
				prefix.append("0");
			}
			String nextFundId = prefix.append(number.toString()).toString();
			return accountDisplayService.addFundAccount(
					new FundAccount(
							customer_id, 
							nextFundId,
							jsonObject.getString("bank_account"), 
							jsonObject.getString("bank"), 
							"1")
					);
		}
		return 0;
		
	}
	
	@GetMapping(value = "/account/deleteFundAccount",produces = "application/json;charset=UTF-8")
	public int deleteFundAccount(@RequestParam int id) {
		//	get user_id
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
		int  user_id = sessonJsonObject.getIntValue("user_id");
		//	get customer_id
		String customer_id = accountDisplayService.getCustomerIdByUserId(user_id);
		//	get fundAccount
		List<FundAccount> fundAccount =accountDisplayService.getFundAccountByCustomerId(customer_id);
		if(fundAccount.get(id)!=null
				&&fundAccount.get(id).getType().equals("1")==true) {
			return accountDisplayService.
					deleteFundAccount(
							fundAccount.get(id).getFund_id()
							);
		}
		return 0;
	}
}
