package com.practice.open_account.service.ServieImpl;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.AccountAllocDAO;
import com.practice.open_account.service.AccountAllocService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/****
  *@author:zwz
  *@date: 2019/6/250947
  *@descrption:
  */

@Service
public class AccountAllocServiceImpl implements AccountAllocService {
    @Resource
    private AccountAllocDAO accountAllocDAO;

    @Override
    public void insertCusInfo(String cusId,int usrId,Date openDate,Date insDate,int auditorId)
    {
        java.sql.Date openDatestr=new java.sql.Date(openDate.getTime());
        java.sql.Date insDatestr=new java.sql.Date(openDate.getTime());
        accountAllocDAO.insertCusInfo(cusId,usrId,openDatestr,insDatestr,auditorId);
    }
    @Override
    public void insertFundInfo(String cusId,String fundId,String bankAcc,String bank,String type)
    {
        accountAllocDAO.insertFundInfo(cusId,fundId,bankAcc,bank,type);
    }
    @Override
    public void insertTradeInfo(String cusId,String tradeId,int seId,String type)
    {
        accountAllocDAO.insertTradeInfo(cusId,tradeId,seId,type);
    }

    @Override
    public JSONObject getInfo(int usrId)
    {
        return accountAllocDAO.getInfo(usrId);
    }

    @Override

    public String getNewCusAcc() {
        String ans="000000000000";
        int tmp = accountAllocDAO.totCus() + 1;
        int tt = tmp, rec = 0;
        StringBuilder bdans=new StringBuilder(ans);
        while (tt > 0)
        {
            int m=tt%10;
            tt=tt/10;
            rec=rec+1;
            bdans.setCharAt(12-rec,(char)(m+'0'));
        }
        System.out.println(bdans.toString());
        return bdans.toString();
    }
    @Override
    public String getNewFundAcc()
    {
        String ans="u00000000000";
        int tmp = accountAllocDAO.totFund() + 1;
        int tt = tmp, rec = 0;
        StringBuilder bdans=new StringBuilder(ans);
        while (tt > 0)
        {
            int m=tt%10;
            tt=tt/10;
            rec=rec+1;
            bdans.setCharAt(12-rec,(char)(m+'0'));
        }
        return bdans.toString();
    }
    @Override
    public String getNewSHTradeAcc()
    {
        String ans="A000000000";
        int tmp = accountAllocDAO.totTradeSH() + 1;
        int tt = tmp, rec = 0;
        StringBuilder bdans=new StringBuilder(ans);
        while (tt > 0)
        {
            int m=tt%10;
            tt=tt/10;
            rec=rec+1;
            bdans.setCharAt(10-rec,(char)(m+'0'));
        }
        return bdans.toString();
    }
    @Override
    public String getNewSZTradeAcc()
    {
        String ans="00000000";
        int tmp = accountAllocDAO.totTradeSZ() + 1;
        int tt = tmp, rec = 0;
        StringBuilder bdans=new StringBuilder(ans);
        while (tt > 0)
        {
            int m=tt%10;
            tt=tt/10;
            rec=rec+1;
            bdans.setCharAt(8-rec,(char)(m+'0'));
        }
        return bdans.toString();
    }
    @Override
    public boolean openAccount(int usrId,int auditorId)
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmp=new Date();
        Date openDate=null;
        Date insDate=null;
        try {
            openDate = simpleDateFormat.parse(simpleDateFormat.format(tmp));
        }
        catch(Exception e)
        {
            System.out.println("date error");
        }
        tmp.setTime(tmp.getTime()+1000*60*60*24*365*52);
        try {
            insDate = simpleDateFormat.parse(simpleDateFormat.format(tmp));
        }
        catch(Exception e)
        {
            System.out.println("date error");
        }
        JSONObject cusInfo=getInfo(usrId);
        String cusAcc=getNewCusAcc();
        String fundAcc=getNewFundAcc();
        String bankAcc=cusInfo.getString("deposit_account");
        String bank=cusInfo.getString("deposit_bank");
        int nSecurityId=-1;
        int sSecurityId=-1;
        if(cusInfo.getInteger("n_security_id")!=null)
            nSecurityId=cusInfo.getInteger("n_security_id");
        if(cusInfo.getInteger("s_security_id")!=null)
            sSecurityId = cusInfo.getInteger("s_security_id");
        if(nSecurityId!=-1) {
            insertTradeInfo(cusAcc,getNewSHTradeAcc(),nSecurityId,"0");
        }
        if(sSecurityId!=-1) {
            insertTradeInfo(cusAcc,getNewSZTradeAcc(),nSecurityId,"1");
        }
        insertCusInfo(cusAcc,usrId,openDate,insDate,auditorId);
        insertFundInfo(cusAcc,fundAcc,bankAcc,bank,"0");

        return true;
    }
}