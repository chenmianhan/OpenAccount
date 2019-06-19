package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shixun.open_account.dao.DemoDao;
import com.shixun.open_account.dao.GradeDao;
import com.shixun.open_account.dao.SecurityDao;
import com.shixun.open_account.entity.Demo;
import com.shixun.open_account.service.DemoService;
import com.shixun.open_account.service.GradeService;
import com.shixun.open_account.service.SecurityService;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.alibaba.fastjson.JSONArray;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:zwz
 *@date: 2019/6/181600
 *@descrption:
 */

@RestController
public class SecurityController{
    @Resource
    SecurityDao securityDao;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/security/get_security", method = POST, produces = "application/json;charset=UTF-8")
    public JSONArray getSecurity(@RequestBody JSONObject Local)
    {
        String province=Local.getString("province");
        String city=Local.getString("city");
        List<JSONObject> js=securityService.getSecurity(province,city);
        JSONArray result=new JSONArray();
        result.addAll(js);
        return result;
    }

}