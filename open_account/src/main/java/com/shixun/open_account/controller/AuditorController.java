package com.shixun.open_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.dao.AuditorDAO;
import com.shixun.open_account.dao.UserDao;
import com.shixun.open_account.service.AuditorService;
import com.shixun.open_account.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/****
 *@author:cmh
 *@date: 2019/6/241100
 *@descrption:
 */
@RestController
public class AuditorController {
        @Resource
    AuditorDAO auditorDAO;
        @Resource
        private AuditorService auditorService;
        @RequestMapping(value="/api/statisticData/getReviewerInfo", method=POST, produces = "application/json;charset=UTF-8")
        public JSONObject getReviewerInfo(
                                          @RequestParam(value = "reviewerId") String reviewerId) throws Exception {
int getreviewedNum=auditorService.getreviewedNum(reviewerId);
int gettoReviewNum=auditorService.gettoReviewNum();
String security_id=auditorService.getSecutityIdbyAuditorId(reviewerId);
System.out.println(security_id);
JSONObject security=auditorService.getSecurity(security_id);
            System.out.println(security);
JSONObject js=new JSONObject();
//System.out.println(Security.get("type"));
if((Integer)(security.get("type"))==0)
{
    js.put("exchangeName","上海证券交易所");
}
else
{
    js.put("exchangeName","深圳证券交易所");
}
js.put("branchNetName",security.get("name"));
js.put("toReviewNum",gettoReviewNum);
js.put("reviewedNum",getreviewedNum);
return js;
        }}
