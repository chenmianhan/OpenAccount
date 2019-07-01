package com.shixun.open_account.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dto.AccountInfoDto;
import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.service.AccountDisplayService;
import com.shixun.open_account.service.AccountInfoService;
import com.shixun.open_account.service.SecurityService;

@RestController
public class AccountDisplayController {
	@Autowired
	private AccountDisplayService accountDisplayService;
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private SecurityService securityService;
	@GetMapping(value = "/account/accountDisplay", produces = "application/json;charset=UTF-8")
	public JSONObject accountDisplay(@RequestParam("user_id") int user_id) {
		String customer_id = accountDisplayService.getCustomerIdByUserId(user_id);
		List<JSONObject> data = accountDisplayService
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
				temp.put("cardID", data.get(0).getString("bank_account"));
				List<JSONObject> currencyList = 
						accountDisplayService.getCurrencyByFundId(
								data.get(0).getString("fund_id")
								);
				temp.put("balance", currencyList.get(0));
			}
			//	secondaryAccount
			else {
				JSONObject temp = new JSONObject(3);
				temp.put("id", Integer.valueOf(i));
				temp.put("cardID", data.get(i).getString("bank_account"));
				List<JSONObject> currencyList = 
						accountDisplayService.getCurrencyByFundId(
								data.get(i).getString("fund_id")
								);
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
}
