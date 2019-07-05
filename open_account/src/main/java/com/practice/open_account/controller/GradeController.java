package com.practice.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dao.GradeDao;
import com.practice.open_account.service.GradeService;
import com.practice.open_account.service.UserService;
import com.practice.open_account.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.alibaba.fastjson.JSONArray;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:zwz
 *@date: 2019/6/181600
 *@descrption:
 */

@RestController
public class GradeController{
    @Resource
    private UserService userService;

    @Autowired
    private GradeService gradeService;
    @RequestMapping(value = "/risk_evaluation/get_grade", method = POST, produces = "application/json;charset=UTF-8")
    public String getGradeName(@RequestBody JSONObject Answers)
    {
        JSONArray jsonArray=Answers.getJSONArray("answers");
        int mark=0;
        for(int i=0;i<jsonArray.size();i++)
        {
            JSONObject partData=jsonArray.getJSONObject(i);
            JSONArray answer=partData.getJSONArray("answer");
            int re_id=partData.getInteger("RE_id");
            mark=mark+gradeService.getMark(re_id,answer);
        }
        String grade=gradeService.getGradeName(mark);
        JSONObject result=new JSONObject();
        result.put("grade",grade);
        result.put("mark",mark);
        String user_id= SessionUtil.getSessionAttribute().getString("user_id");
        userService.setUserStatus(user_id,"2");
        return result.toJSONString();
    }

}