package com.practice.open_account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AccountDisplay {
	private String customer_id;
	private String fund_id;
	private String bank_account;
	private String bank;
	private String type;
}
