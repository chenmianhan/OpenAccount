package com.practice.open_account.controller;


import com.alibaba.fastjson.JSONObject;
import com.practice.open_account.dto.AccountInfoDto;
import com.practice.open_account.entity.AccountInfo;
import com.practice.open_account.entity.Address;
import com.practice.open_account.service.AccountInfoService;
import com.practice.open_account.service.SecurityService;
//import com.shixun.open_account.redisUtils.RedisOperation;
import com.practice.open_account.service.ServieImpl.AccountInfoServiceImpl;
import com.practice.open_account.util.PasswordUtil;
import com.practice.open_account.util.constants.LoginConstants;

import java.util.Base64;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.hibernate.Hibernate;

/****
 *@author:cmh
 *@date: 2019/6/110944
 *@description:
 */
@RestController
public class AccountInfoController {
//	@Autowired
//	private ValueOperations<Object, Object> redisOperations;
	@Autowired
    private AccountInfoService accountInfoService;
	@Autowired
	private SecurityService securityService;
    
	//	上传
//	@PostMapping(value="/savePic")
//	public void savePic()
//	
	//	添加用户信息
    @RequestMapping(value = "/addAccountInfo", method=POST,produces = "application/json;charset=UTF-8")
    public int addAccountInfo(@RequestBody JSONObject jsonObject
    		) throws Exception 
    {
    	System.out.println(jsonObject.getString("id_address_detail"));
    	System.out.println(jsonObject);
    	AccountInfoDto accountInfoDto = new AccountInfoDto
    			(jsonObject.getObject("account_info",AccountInfo.class), 
		  		 new Address(jsonObject.getObject("id_address",String[].class),jsonObject.getString("id_address_detail")),
		  		 new Address(jsonObject.getObject("contact_address",String[].class),jsonObject.getString("contact_address_detail")),
		  		 new Address(jsonObject.getObject("postal_address",String[].class),jsonObject.getString("postal_address_detail"))
		   		 );
//    	通过session获取user_id
    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	System.out.println(sessonJsonObject);
    	accountInfoDto.getAccount_info().setUser_id(sessonJsonObject.getInteger("user_id"));
    	// insert address first
    	accountInfoService.addAddress(accountInfoDto.getId_address());
//    	redisOperations.set(accountInfoDto.getId_address(), value);
    	Integer id_address_id = accountInfoDto.getId_address().getAid();
    
    	accountInfoService.addAddress(accountInfoDto.getContact_address());
    	Integer contact_address_id = accountInfoDto.getContact_address().getAid();
    	
    	accountInfoService.addAddress(accountInfoDto.getPostal_address());
		Integer postal_address_id = accountInfoDto.getPostal_address().getAid(); 
		
		//	update ids in account_info after insert addresses
		accountInfoDto.getAccount_info().setId_address_id(id_address_id);
		accountInfoDto.getAccount_info().setContact_address_id(contact_address_id);
		accountInfoDto.getAccount_info().setPostal_address_id(postal_address_id);
		accountInfoService.addAccountInfo(accountInfoDto.getAccount_info());
		System.out.println();
		//	update status
		return accountInfoService.updateUserStatus(accountInfoDto.getAccount_info().getUser_id(), "1");
		//	if successfully insert, then set redis
//		if(temp.getAccount_info_id()!=null) {
//			redisOperations.set("account_info:"+user_id, temp, Duration.ofHours(24L));
			
//			}
//		return 0;
    	}
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
//    	Calendar currTime = Calendar.getInstance();
    	String path = File.separator+"hujoe"+File.separator+"picture";
//        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
//        String path =File.separator+"Users"+File.separator+"iot"+File.separator+"Desktop"+File.separator+"pic";
//        MultipartFile file = request.getFile("upfile");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") ){
            String fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);
            if(!targetFile.getParentFile().exists()){ //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
                return ResponseEntity.ok(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return ResponseEntity.status(409).body("Fail");
    }
    
    @GetMapping(value = "/getAccountInfo",produces = "application/json;charset=UTF-8")
    public AccountInfoDto getAccountInfoByUserId
    	() throws Exception{
    	//	get directly if in redis.
    	//	Otherwise get from mysql and reset in redis
    	AccountInfo temp = null;
//    	if(( temp= (AccountInfo)redisOperations.get("account_info:"+user_id) )==null)
//    	{
    	Integer user_id =((JSONObject) SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO)).getIntValue("user_id");
		temp = accountInfoService.getAccountInfoByUserId(user_id);
//    		redisOperations.set("account_info:"+user_id,temp,Duration.ofHours(24L));
//    	}
    	//System.out.println(temp.getClass().getField("name").get(temp));
    	return new AccountInfoDto(temp, 
				accountInfoService.getAddressByAId(temp.getId_address_id()), 
				accountInfoService.getAddressByAId(temp.getContact_address_id()), 
				accountInfoService.getAddressByAId(temp.getPostal_address_id())
				);
    }
    
    @PostMapping(value = "/deleteAccountInfo",produces = "application/json;charset=UTF-8")
    public int deleteAccountInfoByUserId
    	(@RequestParam("user_id")Integer user_id) {
    	return accountInfoService.deleteAccountInfoByUserId(user_id);
    }
    
    @PostMapping(value = "/updateAccountInfo",produces = "application/json;charset=UTF-8")
    public int updateAccountInfo(@RequestBody JSONObject jsonObject) throws Exception
	{
    	AccountInfo modify = new AccountInfo();
    	Address modifAddress = new Address();
    	//	get user_id
//    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
//    	int user_id = sessonJsonObject.getIntValue("user_id");
    	int user_id = jsonObject.getIntValue("user_id");
    	modify.setUser_id(user_id);
    	//	get modified name
    	modify.setName(jsonObject.getString("name"));
    	if(accountInfoService.updateAccountInfo(modify)!=1) {
    		return 0;
    	}
    	//	get address_id first
    	AccountInfo before = accountInfoService.getAccountInfoByUserId(modify.getUser_id());
    	modifAddress.setAid(before.getContact_address_id());
    	modifAddress.setDetail(jsonObject.getString("contact_address_detail"));
    	String[] addressArray = jsonObject.getObject("contact_address", String[].class);
    	if(addressArray.length==3) {
    		modifAddress.setProvince(addressArray[0]);
    		modifAddress.setCity(addressArray[1]);
    		modifAddress.setStreet(addressArray[2]);
    		return accountInfoService.updateAddress(modifAddress);
    	}
    	return 0;
    	
//    			redisOperations.get("account_info:"+user_id)!=null?(AccountInfo)redisOperations.get("account_info:"+user_id):
	}


	@PostMapping(value = "/updateSecurity",produces = "application/json;charset=UTF-8")
	public int updateSecurity(@RequestBody JSONObject jsonObject) {
		Integer security_id = jsonObject.getInteger("security_id");
		String arrayType = jsonObject.getString("trade_type");
		String trade_type = arrayType.length()>1?"2":arrayType;
    	//	通过session获取user_id
    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	int user_id = sessonJsonObject.getIntValue("user_id");
		return ((accountInfoService.updateSecurity(user_id, security_id, trade_type))&
				accountInfoService.updateUserStatus(user_id, "3"));
//			redisOperations.set("account_info:"+user_id, value);
	}
	
	@PutMapping(value = "/updateDeposit",produces = "application/json;charset=UTF-8")
	public String updateDeposit(@RequestBody JSONObject jsonObject)
	{
		String deposit_bank = jsonObject.getString("deposit_bank");
		String deposit_account = jsonObject.getString("deposit_account");
		String deposit_password = jsonObject.getString("deposit_password");
		//	加密处理
		deposit_password = PasswordUtil.getMD5(deposit_password+deposit_account);
//		通过session获取user_id
    	JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	int user_id = sessonJsonObject.getIntValue("user_id");
    	//	update table account_info 
		if(accountInfoService.updateDeposit(user_id, deposit_bank, deposit_account, deposit_password)==1) {
//			if(redisOperations.get(key))
			//	update user status to 4
			if(accountInfoService.updateUserStatus(user_id, "4")==1) {
				//	return contact_phone of the corresponding security  
				return securityService.getSecurityBySecurityId(
						accountInfoService.getAccountInfoByUserId(user_id).getSecurity_id()
						).getString("contact_phone");
			}
				
				else return "status update fail";
		}
		else return "deposit update fail";
	}

	@GetMapping(value = "/contactNum",produces = "application/json;charset=UTF-8")
	public String getContactNum() {
		JSONObject sessonJsonObject = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(LoginConstants.SESSION_USER_INFO);
    	int user_id = sessonJsonObject.getIntValue("user_id");
		return securityService.getSecurityBySecurityId(
				accountInfoService.getAccountInfoByUserId(user_id).getSecurity_id()
				).getString("contact_phone"); 
	}
	@PostMapping(value = "/uploadImage")
	public String change(@RequestBody JSONObject jsonObject) throws Exception {
		String base64Image = jsonObject.getString("image");
		int type = jsonObject.getInteger("type");
		
    	final Base64.Decoder decoder = Base64.getDecoder();
    	final Base64.Encoder encoder = Base64.getEncoder();
//    	String imageData = null;
    	try {
//			imageData = encoder.encodeToString(file.getBytes());
//			System.out.println(imageData);
//			byte[] bytAfter = decoder.decode(imageData);
//			byte[] bytBefore = file.getBytes();
//			if(bytAfter.length!=bytBefore.length) return 0;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return base64Image;
	}
}
// 22 11
