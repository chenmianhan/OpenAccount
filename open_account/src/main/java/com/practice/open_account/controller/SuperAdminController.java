package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.SecurityDao;
import com.practice.open_account.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.alibaba.fastjson.JSONArray;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:hwy
 *@date: 2019/7/011639
 *@descrption:
 */

@RestController
public class SuperAdminController{
    //    @Resource
//    SecurityDao securityDao;
    @Resource
    private SuperAdminService superAdminService;

    @RequestMapping(value = "/superadmin/get_securityUnderAdmins", method = GET, produces = "application/json;charset=UTF-8")
    public int getSecurityUnderAdmin(@RequestParam(value = "admin_id") int admin_id){
        return superAdminService.getSecurityUnderAdmin(admin_id);
    }

    @RequestMapping(value = "/deleteStore", method = POST, produces = "application/json;charset=UTF-8")
    public int deleteStore(@RequestBody JSONObject jsonObject){
        int store = jsonObject.getInteger("store");
        return superAdminService.deleteStore(store);
    }

    @RequestMapping(value = "/deleteUsers", method = POST, produces = "application/json;charset=UTF-8")
    public int deleteUsers(@RequestBody JSONObject jsonObject){
        int user_id = jsonObject.getInteger("user_id");
        return superAdminService.deleteUser(user_id);
    }

}