package com.practice.open_account.config.shiro.realm;

import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.UserService;
import com.practice.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author: hxy
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		//查询用户的权限
		JSONObject permission = (JSONObject) session.getAttribute(LoginConstants.SESSION_USER_PERMISSION);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		String phone = (String) authcToken.getPrincipal();
		//System.out.println("phone");
		// 获取用户密码
		String password = new String((char[]) authcToken.getCredentials());
	//	System.out.println("password");
		JSONObject user = userService.getUser(phone, password);
		if (user == null) {
			throw new UnknownAccountException();
		}
		//System.out.println("sysy");
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getString("phone"),
				user.getString("password"),
				//ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
				getName()
		);
		//session中不需要保存密码
		user.remove("password");
		//将用户信息放入session中
		SecurityUtils.getSubject().getSession().setAttribute(LoginConstants.SESSION_USER_INFO, user);

		return authenticationInfo;
	}
}
