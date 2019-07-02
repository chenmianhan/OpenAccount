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


    public void insertCusInfo(String cusId,int usrId,Date openDate,Date insDate)
    {
        java.sql.Date openDatestr=new java.sql.Date(openDate.getTime());
        java.sql.Date insDatestr=new java.sql.Date(insDate.getTime());
        accountAllocDAO.insertCusInfo(cusId,usrId,openDatestr,insDatestr);
    }

    public void insertFundInfo(String cusId,String fundId,String bankAcc,String bank,String type)
    {
        accountAllocDAO.insertFundInfo(cusId,fundId,bankAcc,bank,type);
    }

    public void insertTradeInfo(String cusId,String tradeId,int seId,String type)
    {
        accountAllocDAO.insertTradeInfo(cusId,tradeId,seId,type);
    }


    public JSONObject getInfo(int usrId)
    {
        return accountAllocDAO.getInfo(usrId);
    }



    public String getNewCusAcc() {
        String ans="000000000000";
        String tmp = accountAllocDAO.maxCus();
        if(tmp==null)
            tmp=ans;
        StringBuilder bdans=new StringBuilder(tmp);
        int cur= tmp.charAt(11)-'0'+1;
        int rec=1;
        while (cur > 9)
        {
            bdans.setCharAt(12-rec,'0');
            rec=rec+1;
            cur=(int)tmp.charAt(12-rec)-'0'+1;
        }
        bdans.setCharAt(12-rec,(char)(48+cur));
        System.out.println(bdans.toString());
        return bdans.toString();
    }
    @Override
    public boolean addNewFundAcc(String customer_id,String bank_account,String bank)
    {
        String acc=getNewFundAcc();
        accountAllocDAO.insertFundInfo(customer_id,acc,bank_account,bank,"1");
        return true;
    }
    public String getNewFundAcc()
    {
        String ans="u00000000000";
        String tmp = accountAllocDAO.maxFund();
        if(tmp==null)
            tmp=ans;
        StringBuilder bdans=new StringBuilder(tmp);
      //  System.out.println(tmp.charAt(11));
        int cur= tmp.charAt(11)-'0'+1;
      //  System.out.println(cur);
        int rec=1;
        while (cur > 9)
        {
            bdans.setCharAt(12-rec,'0');
            rec=rec+1;
        //    System.out.println(cur);
          //  System.out.println(rec);
            //System.out.println(bdans.toString());
            cur=(int)tmp.charAt(12-rec)-'0'+1;
        }
        bdans.setCharAt(12-rec,(char)(48+cur));
        System.out.println(bdans.toString());
        return bdans.toString();
    }

    public String getNewSHTradeAcc()
    {
        String ans="A000000000";
        String tmp = accountAllocDAO.maxTradeSH();
        if(tmp==null)
            tmp=ans;
        StringBuilder bdans=new StringBuilder(tmp);
        int cur= tmp.charAt(9)-'0'+1;
        int rec=1;
        while (cur > 9)
        {
            bdans.setCharAt(10-rec,'0');
            rec=rec+1;
            cur=(int)tmp.charAt(10-rec)-'0'+1;
        }
        bdans.setCharAt(10-rec,(char)(48+cur));
        System.out.println(bdans.toString());
        return bdans.toString();
    }

    public String getNewSZTradeAcc()
    {
        String ans="00000000";
        String tmp = accountAllocDAO.maxTradeSZ();
        if(tmp==null)
            tmp=ans;
        StringBuilder bdans=new StringBuilder(tmp);
        int cur= tmp.charAt(7)-'0'+1;
        int rec=1;
        while (cur > 9)
        {
            bdans.setCharAt(8-rec,'0');
            rec=rec+1;
            cur=(int)tmp.charAt(8-rec)-'0'+1;
        }
        bdans.setCharAt(8-rec,(char)(48+cur));
        System.out.println(bdans.toString());
        return bdans.toString();
    }
    @Override
    public boolean openAccount(int usrId)
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tmp=new Date();
        Date openDate=null;
        Date insDate=null;
        try {
            openDate = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        }
        catch(Exception e)
        {
            System.out.println("date error");
        }
        tmp.setTime(System.currentTimeMillis()+(long)(1000*60*60)*(long)(24*365*3));
        try {
            insDate = simpleDateFormat.parse(simpleDateFormat.format(tmp));
            System.out.println(insDate);
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
        String type=cusInfo.getString("trade_type");
        int seId=cusInfo.getInteger("security_id");
        if(type=="0")
        {
            insertTradeInfo(cusAcc,getNewSHTradeAcc(),seId,"0");
        }else
            if(type=="1"){
                insertTradeInfo(cusAcc,getNewSZTradeAcc(),seId,"1");
            }else{
                insertTradeInfo(cusAcc,getNewSHTradeAcc(),seId,"0");
                insertTradeInfo(cusAcc,getNewSZTradeAcc(),seId,"1");
            }

        insertCusInfo(cusAcc,usrId,openDate,insDate);
        insertFundInfo(cusAcc,fundAcc,bankAcc,bank,"0");

        return true;
    }
}