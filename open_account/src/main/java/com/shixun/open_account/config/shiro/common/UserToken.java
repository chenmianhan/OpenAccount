package com.shixun.open_account.config.shiro.common;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义token,用于确定登陆者类型
 * <p>Title: UserToken</p>  
 * <p>Description: </p>  
 * @author Adam 
 * @date 2018年8月14日
 */
public class UserToken extends UsernamePasswordToken {
	private String loginType;

    public UserToken() {}

    public UserToken(final String username, final String password, 
            final String loginType) {
        super(username, password);
        if(loginType.equals("0"))
        {this.loginType = "User";}
        else if(loginType.equals("1"))
        { this.loginType="Auditor";}
        else if(loginType.equals("2"))
        {
            this.loginType="Admin";
        }
        else if(loginType.equals("3")){
            this.loginType="Super";
        }
        else {
                this.loginType = "User";
            }
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
