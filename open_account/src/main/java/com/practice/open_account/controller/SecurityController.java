package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/****
 *@author:zwz
 *@date: 2019/6/181600
 *@descrption:
 */

@RestController
public class SecurityController{
//    @Resource
//    SecurityDao securityDao;
    @Autowired
    private SecurityService securityService;
    
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



                if(tmp1.getString("city").equals(tmp2.getString("city")))
                {
                    Anssonson.put("value",tmp1.getString("security_id"));
                    Anssonson.put("label",tmp1.getString("name"));
                    anssonson.add(Anssonson);
                    Anssonson=new JSONObject();
                }            
                else
                {
                    Anssonson.put("value",tmp1.getString("security_id"));
                    Anssonson.put("label",tmp1.getString("name"));
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