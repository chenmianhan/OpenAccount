package com.practice.open_account.entity;

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
	private String id_type;
	private String id_number;
	private Integer id_address_id;
	private String id_issuance_date;
	private String id_overdue_date;
	private String id_licensing_authority;
	private String id_picture;
	private String id_card_inverse_side;
	private Integer contact_address_id;
	private Integer postal_address_id;
	private Integer security_id;
	private String trade_type;
	private String deposit_bank;
	private String deposit_account;
	private String deposit_password;
	private String profession;
	private String education;
	private String email;
	private Integer risk_assessment_mark;

}
