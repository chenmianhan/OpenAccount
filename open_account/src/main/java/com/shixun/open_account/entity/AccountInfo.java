package com.shixun.open_account.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class AccountInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer account_info_id;
	private Integer user_id;
	private String name;
	private String ID_type;
	private String ID_number;
	private Integer ID_address_id;
	private String ID_issuance_date;
	private String ID_overdue_date;
	private String ID_licensing_authority;
	private Integer contact_address_id;
	private Integer postal_address_id;
	private String trans_password;
	private String Fund_password;
	private Integer n_security_id;
	private Integer s_security_id;
	private String deposit_bank;
	private String deposit_account;
	private String deposit_password;
	private String status;
	private String profession;
	private String education;
	private String email;
	private String ID_picture;
	private String ID_card_inverse_side;
	private Integer risk_assessment_mark;

}
