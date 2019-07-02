package com.practice.open_account.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class FundAccount implements	Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customer_id;
	private String fund_id;
	private String bank_account;
	private String bank;
	private String type;
}
