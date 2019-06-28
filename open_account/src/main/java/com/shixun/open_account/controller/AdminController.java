package com.shixun.open_account.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.entity.Employee;
import com.shixun.open_account.service.AdminService;
import com.shixun.open_account.service.AuditorService;
import com.shixun.open_account.service.SecurityService;

@RestController
public class AdminController {
	@Autowired
    private AdminService adminService;
	@Autowired
	private SecurityService securityService;
	@Autowired
    private AuditorService auditorService;
    
    @GetMapping(value = "/admin/get_securityUnderAdmin", produces = "application/json;charset=UTF-8")
    public JSONArray getSecurity(@RequestParam Integer admin_id)
    {
    	List<Integer> list = adminService.getSecurityIdByAdminId(admin_id);
    	LinkedList<JSONObject> js = new LinkedList<>();
    	for(Integer i:list) {
    		js.add(securityService.getSecurityBySecurityId(i));
    	}
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

	@PostMapping(value = "/admin/addAuditor")
	public int addAuditor(@RequestBody JSONObject jsonObject) {
		Employee employee = new Employee(null, jsonObject.getString("account"), jsonObject.getString("password"), "2", jsonObject.getString("name"));
		auditorService.insertEmployee(employee);
		return auditorService.insertAuditor(
				jsonObject.getIntValue("security_id"),
				employee.getEmployee_id()
				);
	}
	@PutMapping(value = "/admin/modifyAuditor")
	public int updateEmployee(@RequestBody JSONObject jsonObject) {
		return auditorService.updateEmployee(
				jsonObject.getIntValue("auditor_id"),
				jsonObject.getString("account"),
				jsonObject.getString("password"),
				"2",
				jsonObject.getString("name"))&auditorService.updateAuditor(
				jsonObject.getIntValue("security_id"),
				jsonObject.getIntValue("auditor_id"));

	}
}
