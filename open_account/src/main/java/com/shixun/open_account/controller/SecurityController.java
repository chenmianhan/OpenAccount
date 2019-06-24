package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.SecurityDao;
import com.shixun.open_account.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/security/get_securityall", method = GET, produces = "application/json;charset=UTF-8")
    public JSONArray getSecurityAll()
    {
        List<JSONObject> js=securityService.getSecurityAll();
        JSONArray result=new JSONArray();
        JSONArray ans=new JSONArray();
        JSONObject Ans= new JSONObject();
        JSONArray ansson=new JSONArray();
        JSONObject Ansson=new JSONObject();
        JSONArray anssonson=new JSONArray();
        JSONObject Anssonson=new JSONObject();
        result.addAll(js);
        for(int i=0;i<result.size()-1;i++)
        {
            JSONObject tmp1=result.getJSONObject(i);
            JSONObject tmp2=result.getJSONObject(i+1);
            System.out.println(tmp1);


                if(tmp1.getString("city").equals(tmp2.getString("city")))
                {
                    Anssonson.put("value",tmp1.getString("security_id"));
                    Anssonson.put("label",tmp1.getString("name"));
                    Anssonson.put("type",tmp1.getString("type"));
                    anssonson.add(Anssonson);
                    Anssonson=new JSONObject();
                }            
                else
                {
                    Anssonson.put("value",tmp1.getString("security_id"));
                    Anssonson.put("label",tmp1.getString("name"));
                    Anssonson.put("type",tmp1.getString("type"));
                    anssonson.add(Anssonson);
                    Anssonson=new JSONObject();
                    Ansson.put("label",tmp1.getString("city"));
                    Ansson.put("value",tmp1.getString("city"));
                    Ansson.put("children",anssonson);
                    ansson.add(Ansson);
                    anssonson=new JSONArray();
                    Ansson=new JSONObject();
                }
        if(tmp1.getString("province").equals(tmp2.getString("province")))
        {}
            else
            {   Ans.put("label",tmp1.getString("province"));
                Ans.put("value",tmp1.getString("province"));
                Ans.put("children",ansson);
                ans.add(Ans);
                Ans=new JSONObject();
                ansson=new JSONArray();
            }
        }

        JSONObject tmp1=result.getJSONObject(result.size()-1);
        Anssonson.put("security_id",tmp1.getString("security_id"));
        Anssonson.put("name",tmp1.getString("name"));
        Anssonson.put("type",tmp1.getString("type"));
        anssonson.add(Anssonson);
        Ansson.put("city_name",tmp1.getString("city"));
        Ansson.put("securities",anssonson);
        ansson.add(Ansson);
        Ans.put("province_name",tmp1.getString("province"));
        Ans.put("cities",ansson);
        ans.add(Ans);

        return ans;
    }

}