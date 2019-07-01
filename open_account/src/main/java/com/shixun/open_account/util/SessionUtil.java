package com.shixun.open_account.util;

import com.alibaba.fastjson.JSONObject;
import com.shixun.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/****
 *@author:cmh
 *@date: 2019/7/11544
 *@descrption:
 */
public class SessionUtil {
    public static   JSONObject getSessionAttribute() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (JSONObject)session.getAttribute(LoginConstants.SESSION_USER_INFO);
    }
}
