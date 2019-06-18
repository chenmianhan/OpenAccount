package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.shixun.open_account.dao.RiskEvaluationDao;
//import com.shixun.open_account.entity.RiskEvaluation;
import com.shixun.open_account.service.RiskEvaluationService;
import com.shixun.open_account.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.util.List;

/****
 *@author:hwy
 *@date: 2019/6/171549
 *@descrption:
 */

@RestController
public class RiskEvaluationController {
    @Resource
    RiskEvaluationService riskEvaluationService;
//    @Resource
//    private UserService userService;
    @RequestMapping(value="/admin/add_RE", method=POST, produces = "application/json;charset=UTF-8")
    public int addRiskEvaluation(@RequestBody JSONObject js) throws Exception {
        int RE_id = js.getInteger("RE_id");
        String content = js.getString("content");
        String option_1 = js.getString("option_1");
        int mark_1 = js.getInteger("mark_1");
        String option_2 = js.getString("option_2");
        int mark_2 = js.getInteger("mark_2");
        String option_3 = js.getString("option_3");
        int mark_3 = js.getInteger("mark_3");
        String option_4 = js.getString("option_4");
        int mark_4 = js.getInteger("mark_4");
        String option_5 = js.getString("option_5");
        int mark_5 = js.getInteger("mark_5");
        boolean is_radio = js.getBoolean("is_radio");
        String type = js.getString("type");
        int successfulId = riskEvaluationService.addRiskEvaluation(RE_id, content, option_1, mark_1, option_2, mark_2, option_3, mark_3, option_4, mark_4, option_5, mark_5, is_radio, type);
        return successfulId;
    }

    @RequestMapping(value="risk_evaluation/get_questions", method=GET, produces = "application/json;charset=UTF-8")
    public JSONArray getRiskEvaluations() throws Exception {
        List<JSONObject> js = riskEvaluationService.getRiskEvaluations();
        JSONArray res = new JSONArray();
        res.addAll(js);
        return res;
    }
}
