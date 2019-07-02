package com.shixun.open_account.service;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSONArray;
import java.util.List;
import java.util.Date;
public interface AccountAllocService {
    boolean openAccount(int usr_id);

    boolean addNewFundAcc(String customer_id, String bank_account, String bank);
}

