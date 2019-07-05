package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.AccountAllocDAO;
import com.practice.open_account.service.AccountAllocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:zwz
 *@date: 2019/6/181600
 *@descrption:
 */

@RestController
public class AccountAllocController{
    @Resource
    AccountAllocDAO accountAllocDAO;

    @Autowired
    private AccountAllocService accountAllocService;

    @RequestMapping(value = "/account/get_account", method = POST, produces = "application/json;charset=UTF-8")
    public boolean getNewCusAcc(@RequestBody JSONObject info)throws Exception
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmp=new Date();
        Date openDate=simpleDateFormat.parse(simpleDateFormat.format(tmp));
        tmp.setTime(1000*60*60*24*365*52);
        Date insDate=simpleDateFormat.parse(simpleDateFormat.format(tmp));
        int usrId=info.getInteger("user_id");
       // int auditorId=info.getInteger("auditor_id");
        JSONObject cusInfo=accountAllocService.getInfo(usrId);
        String cusAcc=accountAllocService.getNewCusAcc();
        String fundAcc=accountAllocService.getNewFundAcc();
        accountAllocService.insertCurrency(fundAcc,"0",0);
        System.out.println("1");
        String bankAcc=cusInfo.getString("deposit_account");
        System.out.println("2");
        String bank=cusInfo.getString("deposit_bank");
        System.out.println("3");

        int nSecurityId=-1;

        int sSecurityId=-1;
        if(cusInfo.getInteger("n_security_id")!=null)
            nSecurityId=cusInfo.getInteger("n_security_id");
        if(cusInfo.getInteger("s_security_id")!=null)
            sSecurityId = cusInfo.getInteger("s_security_id");
        System.out.println("4");
        if(nSecurityId!=-1) {
            accountAllocService.insertTradeInfo(cusAcc,accountAllocService.getNewSHTradeAcc(),nSecurityId,"0");
        }
        if(sSecurityId!=-1) {
            accountAllocService.insertTradeInfo(cusAcc,accountAllocService.getNewSZTradeAcc(),nSecurityId,"1");
        }
        accountAllocService.insertCusInfo(cusAcc,usrId,openDate,insDate);
        accountAllocService.insertFundInfo(cusAcc,fundAcc,bankAcc,bank,"0");

        return true;
    }
    @RequestMapping(value = "/account/get_accounttest", method = POST, produces = "application/json;charset=UTF-8")
    public boolean accountTest(@RequestBody JSONObject info)
    {
        int usrId=info.getInteger("user_id");
        return accountAllocService.openAccount(usrId);
    }
    @RequestMapping(value = "/account/get_fund_account", method = POST, produces = "application/json;charset=UTF-8")
    public String getNewFundAcc(@RequestBody JSONObject info)
    {
        String acc=accountAllocService.getNewFundAcc();
        String cusId=info.getString("customer_id");
        String bankAcc=info.getString("bank_account");
        String bank=info.getString("bank");
        String type=info.getString("type");
        accountAllocService.insertFundInfo(cusId,acc,bankAcc,bank,type);
        return acc;
    }
    @RequestMapping(value = "/account/get_shtrade_account", method = POST, produces = "application/json;charset=UTF-8")
    public String getNewSHTradeAcc(@RequestBody JSONObject info)
    {
        String acc=accountAllocService.getNewSHTradeAcc();
        String cusId=info.getString("customer_id");
        int seId=info.getInteger("security_id");
        String type=info.getString("type");
        accountAllocService.insertTradeInfo(cusId,acc,seId,type);
        return acc;
    }
    @RequestMapping(value = "/account/get_sztrade_account", method = POST, produces = "application/json;charset=UTF-8")
    public String getNewSZTradeAcc(@RequestBody JSONObject info)
    {
        String acc=accountAllocService.getNewSZTradeAcc();
        String cusId=info.getString("customer_id");
        int seId=info.getInteger("security_id");
        String type=info.getString("type");
        accountAllocService.insertTradeInfo(cusId,acc,seId,type);
        return acc;
    }


}