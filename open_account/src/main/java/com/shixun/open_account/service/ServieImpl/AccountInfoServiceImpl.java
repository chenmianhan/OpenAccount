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
	public int updateSecurity(Integer user_id, Integer n_security_id, Integer s_security_id) {
		return accountInfoDAO.updateSecurity(user_id, n_security_id, s_security_id);
	}
	@Override
	public int updateDeposit(Integer user_id, String deposit_bank, String deposit_account, String deposit_password) {
		return accountInfoDAO.updateDeposit(user_id, deposit_bank, deposit_account, deposit_password);
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
	@Override
	public int updateAddress(Address address) {
		return accountInfoDAO.updateAddress(address);
	}

}
