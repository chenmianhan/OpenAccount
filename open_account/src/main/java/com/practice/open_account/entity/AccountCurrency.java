package com.practice.open_account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AccountCurrency {
	private String fund_id;
	private String currency_type;
	private double balance;
}
