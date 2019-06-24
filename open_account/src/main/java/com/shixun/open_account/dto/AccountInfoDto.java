 package com.shixun.open_account.dto;

import com.shixun.open_account.entity.AccountInfo;
import com.shixun.open_account.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AccountInfoDto {
	private AccountInfo account_info;
	private Address id_address;
	private Address contact_address;
	private Address postal_address;
}
