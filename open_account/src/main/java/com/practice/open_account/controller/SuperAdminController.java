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

//    @RequestMapping(value = "/superadmin/get_securityUnderAdmins", method = GET, produces = "application/json;charset=UTF-8")
//    public int getSecurityUnderAdmin(@RequestParam(value = "admin_id") int admin_id){
//        return superAdminService.getSecurityUnderAdmin(admin_id);
//    }

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

    @RequestMapping(value = "/superadmin/deleteAdmin", method = POST, produces = "application/json;charset=UTF-8")
    public int deleteAdmin(@RequestBody JSONObject jsonObject){
        int admin_id = jsonObject.getInteger("admin_id");
        return superAdminService.deleteAdmin(admin_id);
    }

    @RequestMapping(value = "/superadmin/getStore", method = POST, produces = "application/json;charset=UTF-8")
    public JSONArray getStore(@RequestBody JSONObject jsonObject){
        String store = jsonObject.getString("store");
        return superAdminService.getStore(store);
    }

    @RequestMapping(value = "/superadmin/getAllStore", method = GET, produces = "application/json;charset=UTF-8")
    public JSONArray getAllStore(){
        return superAdminService.getAllStore();
    }

    @RequestMapping(value = "/superadmin/getAdminByStore", method = POST, produces = "application/json;charset=UTF-8")
    public JSONArray getAdminByStore(@RequestBody JSONObject jsonObject){
        String store = jsonObject.getString("store");
        return superAdminService.getAdminByStore(store);
    }

    @RequestMapping(value = "/superadmin/getAdminByName", method = POST, produces = "application/json;charset=UTF-8")
    public JSONArray getAdminByName(@RequestBody JSONObject jsonObject){
        String admin_name = jsonObject.getString("admin_name");
        return superAdminService.getAdminByName(admin_name);
    }

    @RequestMapping(value = "/superadmin/getAllAdmin", method = GET, produces = "application/json;charset=UTF-8")
    public JSONArray getAllAdmin(){
        return superAdminService.getAllAdmin();
    }

    @RequestMapping(value = "/superadmin", method = GET, produces = "application/json;charset=UTF-8")
    public JSONObject getSuperAdminName() {
        return superAdminService.getSuperAdminName();
    }
    @RequestMapping(value="/admin/getUserInfo",method=POST,produces="application/json;charset=UTF-8")
    public JSONObject getUserInfo(@RequestBody JSONObject js){
        int usrId=js.getInteger("user_id");
        JSONObject tmp1=superAdminService.getUserInfo(usrId);
        // System.out.println(tmp1);
        int aId=tmp1.getInteger("id_address_id");
        // System.out.println(tmp1);
        tmp1.remove("id_address_id");
        // System.out.println(tmp1);
        JSONObject tmp2=superAdminService.getPhoneAndTime(usrId);
        // System.out.println(tmp2);
        String addr=superAdminService.getAddressInfo(aId);
        tmp1.put("contact",tmp2.getString("phone"));
        tmp1.put("date",tmp2.getString("update_time"));
        tmp1.put("address",addr);
        return tmp1;
    }
    @RequestMapping(value="/admin/getUserId",method = GET,produces = "application/json;charset=UTF-8")
    public JSONArray getUserList()
    {
        return superAdminService.getUserList();
    }
}