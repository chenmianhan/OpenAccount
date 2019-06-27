package com.shixun.open_account.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer employee_id;
	private String employee_account;
	private String employee_password;
	private String Employee_type;
	private String employee_name;
}
