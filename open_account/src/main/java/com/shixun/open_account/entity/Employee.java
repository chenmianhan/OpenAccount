package com.shixun.open_account.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer employee_id;
	@NotNull
	private String employee_name;
	@NotNull
	private String employee_account;
	@NotNull
	private String employee_password;
	private String employee_type;
	private Integer security_id;
}
