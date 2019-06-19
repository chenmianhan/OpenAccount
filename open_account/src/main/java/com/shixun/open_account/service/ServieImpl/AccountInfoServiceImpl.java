package com.shixun.open_account.service.ServieImpl;


import com.shixun.open_account.dao.AccountInfoDAO;
import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;
import com.shixun.open_account.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/****
 *@author:cmh
 *@date: 2019/6/110947
 *@descrption:
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoDAO accountInfoDAO;
    
    //account_info
	@Override
	public int addAccountInfo(AccountInfo accountInfo) {
		return accountInfoDAO.addAccountInfo(accountInfo);
	}
	@Override
	public AccountInfo getAccountInfoByUserId(Integer user_id) {
		return accountInfoDAO.getAccountInfoByUserId(user_id);
	}
	@Override
	public int updateAccountInfo(AccountInfo accountInfo) {
		return accountInfoDAO.updateAccountInfo(accountInfo);
	}
	@Override
	public int deleteAccountInfoByUserId(Integer user_id) {
		return accountInfoDAO.deleteAccountInfoByUserId(user_id);
	}
	
	
	//	address
	@Override
	public int addAddress(Address address) {
		return accountInfoDAO.addAddress(address);
	}
	@Override
	public Address getAddressByAId(Integer aid) {
		return accountInfoDAO.getAddressByAId(aid);
	}
	@Override
	public int deleteAddressByAid(Integer aid) {
		return accountInfoDAO.deleteAddressByAid(aid);
	}

}
