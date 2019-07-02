package com.practice.open_account.config.shiro.realm;


import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.service.EmployeeService;
import com.practice.open_account.util.constants.LoginConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Adam
 * @Description:  学校管理员shiro登陆Realm
 * @Title: DeviceController
 * @ProjectName wanmo
 * @date 2018/8/17 16:51
 */
public class SuperRealm extends AuthorizingRealm {
	
	@Autowired
	private EmployeeService employeeService;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       // System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * @Author Adam
     * @Description  主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     * @Date 14:06 2018/9/28
     * @Param [token]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {

        String account = (String) authcToken.getPrincipal();

        // 获取用户密码

        String password = new String((char[]) authcToken.getCredentials());
        JSONObject superAdmin=null;
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        try
        {
            superAdmin=employeeService.getEmployee(account,password,"3");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        if (superAdmin == null) {

            throw new UnknownAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                superAdmin.getString("employee_account"),
                superAdmin.getString("employee_password"),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        //session中不需要保存密码
        superAdmin.remove("employee_password");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(LoginConstants.SESSION_USER_INFO, superAdmin);
        return authenticationInfo;
    }

}