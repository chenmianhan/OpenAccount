package com.shixun.open_account.service;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;
import java.util.List;
import java.util.Date;
public interface AccountAllocService {
    void insertCusInfo(String cusId,int usrId,Date openDate,Date InsDate,int auditorId);
    void insertFundInfo(String cusId,String fundId,String bankAcc,String bank,String type);
    void insertTradeInfo(String cusId,String tradeId,int seId,String type);
    JSONObject getInfo(int usrId);
    boolean openAccount(int usr_id,int auditor_id);
    String getNewCusAcc();
    String getNewFundAcc();
    String getNewSHTradeAcc();
    String getNewSZTradeAcc();
}
