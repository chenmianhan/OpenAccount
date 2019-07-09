package com.practice.open_account.util.constants;

/**
 * @author: hxy
 * @description: 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 * @date: 2017/10/24 10:15
 */
public class LoginConstants {

	public static final String OLD_CODE = "100";
	public static final String FAIL_CODE = "101";
	public static final String NEW_CODE = "102";
	public static final String ERROR_CODE="103";
	public static final String LOGOUT_CODE="104";
	public static final String LOGOUT_ERROR_CODE="105";
	//public static final String SUCCESS_MSG = "请求成功";
	//public static final String FAIL_MSG = "请求失败";
	public static final String VALID_CODE="800";
	public static final String INVALID_CODE="801";
	//session中存放用户信息的key值xx
	//302-验证码正确，303-验证码错误
	//300-未被注册301-已被注册
	//304-密码错误305-密码正确
	//306-修改成功307-异常
	public  static final String USEREXIST="300";
	public  static final String USERNOTEXIST="301";
	public  static final String CHECKNUMTRUE="302";
	public  static final String CHECKNUMFALSE="303";
	public static final String PASSWORDFALSE="304";
	public static final String PASSWORDTRUE="305";
	public static final String UPDATESUCCESS="306";
	public static final String EXCEPTION="307";
	public  static final String SMSSUCCESS="308";
	public static final String  SMSERROR="100015";
	public static final String SESSION_USER_INFO = "userInfo";
	public static final String SESSION_USER_PERMISSION = "userPermission";
	public static final String SESSION_CHECKNUM = "checkNum";

}
