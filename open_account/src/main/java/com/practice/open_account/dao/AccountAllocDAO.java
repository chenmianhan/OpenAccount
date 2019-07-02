package com.practice.open_account.dao;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/****
  *@author:zwz
  *@date: 2019/6/250945
  *@descrption:
  */


public interface AccountAllocDAO{
    void insertCusInfo(@Param("cusId") String cusId,@Param("usrId") int usrId,@Param("openDate")java.sql.Date openDate,@Param("insDate") java.sql.Date insDate,@Param("auditorId")int auditorId);
    void insertFundInfo(@Param("cusId") String cusId,@Param("fundId") String fundId,@Param("bankAcc")String bankAcc,@Param("bank")String bank,@Param("type")String type);
    void insertTradeInfo(@Param("cusId") String cusId,@Param("tradeId")String tradeId,@Param("seId")int seId,@Param("type") String type);
    JSONObject getInfo(@Param("usrId")int usrId);
    int totCus();
    int totFund();
    int totTradeSZ();
    int totTradeSH();
}