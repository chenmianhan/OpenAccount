package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shixun.open_account.dao.DemoDao;
import com.shixun.open_account.entity.Demo;
import com.shixun.open_account.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:cmh
 *@date: 2019/6/110944
 *@descrption:
 */
@RestController
public class demoController {

    @Resource
    DemoDao demoDao;

    @Autowired
    private DemoService demoService;

    @RequestMapping(value="/addDemo", method=POST, produces = "application/json;charset=UTF-8")
    public int addDemo(@RequestParam(value = "name") String name,
                             @RequestParam(value = "age") String age) throws Exception {

        int result= demoService.addDemo("1", name, age);
        return result;
    }
    @RequestMapping(value="/getDemo", method=POST, produces = "application/json;charset=UTF-8")
    public JSONObject getDemo(@RequestParam(value = "id")String id) throws Exception {

        JSONObject demo = demoService.getDemo(id);
        return demo;
    }
    @RequestMapping(value="/editDemo", method=POST, produces = "application/json;charset=UTF-8")
    public int  editDemo(@RequestParam(value = "id")String id,@RequestParam(value = "name") String name,
                               @RequestParam(value = "age") String age) throws Exception {

       int result = demoService.updateDemo(id,name,age);
        return result ;
    }
}
