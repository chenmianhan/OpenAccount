package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shixun.open_account.dao.DemoDao;
import com.shixun.open_account.dao.GradeDao;
import com.shixun.open_account.entity.Demo;
import com.shixun.open_account.service.DemoService;
import com.shixun.open_account.service.GradeService;
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
public class GradeController{
    @Resource
    GradeDao gradeDao;

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
        return result.toJSONString();
    }

}