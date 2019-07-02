package com.practice.open_account.service;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;
import java.util.List;
import java.util.Date;
public interface AccountAllocService {
    boolean openAccount(int usr_id);
    void insertCusInfo(String cusId,int usrId,Date openDate,Date insDate);
    void insertFundInfo(String cusId,String fundId,String bankAcc,String bank,String type);
    void insertTradeInfo(String cusId,String tradeId,int seId,String type);
    JSONObject getInfo(int usrId);
    String getNewCusAcc();
    String getNewFundAcc();
    String getNewSHTradeAcc();
    String getNewSZTradeAcc();
    boolean addNewFundAcc(String customer_id, String bank_account, String bank);
}

