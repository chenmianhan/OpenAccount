package com.shixun.open_account.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface AdminService {
	List<JSONObject> getSecurityIdBySuperAdmin();
}
