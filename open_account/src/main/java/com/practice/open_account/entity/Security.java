package com.practice.open_account.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data@AllArgsConstructor@NoArgsConstructor
public class Security implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer security_id;
	private String name;
	private String province;
	private String city;
	private String contact_phone;
}
