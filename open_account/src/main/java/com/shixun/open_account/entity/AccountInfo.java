package com.shixun.open_account.entity;

import java.io.Serializable;

public class AccountInfo implements Serializable {
	/**
	 * 
	 */
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
	


	public AccountInfo() {
		// TODO Auto-generated constructor stub
	}

	public Integer getID_address_id() {
		return ID_address_id;
	}

	public void setID_address_id(Integer iD_address_id) {
		ID_address_id = iD_address_id;
	}

	
	public String getName() {
		return name;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAccount_info_id() {
		return account_info_id;
	}
	public void setAccount_info_id(Integer account_info_id) {
		this.account_info_id = account_info_id;
	}
	public String getID_type() {
		return ID_type;
	}
	public void setID_type(String iD_type) {
		ID_type = iD_type;
	}
	public String getID_number() {
		return ID_number;
	}
	public void setID_number(String iD_number) {
		ID_number = iD_number;
	}
	public String getID_issuance_date() {
		return ID_issuance_date;
	}
	public void setID_issuance_date(String iD_issuance_date) {
		ID_issuance_date = iD_issuance_date;
	}
	public String getID_overdue_date() {
		return ID_overdue_date;
	}
	public void setID_overdue_date(String iD_overdue_date) {
		ID_overdue_date = iD_overdue_date;
	}
	public String getID_licensing_authority() {
		return ID_licensing_authority;
	}
	public void setID_licensing_authority(String iD_licensing_authority) {
		ID_licensing_authority = iD_licensing_authority;
	}
	
	public String getTrans_password() {
		return trans_password;
	}
	public void setTrans_password(String trans_password) {
		this.trans_password = trans_password;
	}
	public String getFund_password() {
		return Fund_password;
	}
	public void setFund_password(String fund_password) {
		Fund_password = fund_password;
	}

	
	public String getDeposit_account() {
		return deposit_account;
	}
	public void setDeposit_account(String deposit_account) {
		this.deposit_account = deposit_account;
	}
	public String getDeposit_password() {
		return deposit_password;
	}
	public void setDeposit_password(String deposit_password) {
		this.deposit_password = deposit_password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getID_picture() {
		return ID_picture;
	}
	public void setID_picture(String iD_picture) {
		ID_picture = iD_picture;
	}
	public Integer getRisk_assessment_mark() {
		return risk_assessment_mark;
	}
	public void setRisk_assessment_mark(Integer risk_assessment_mark) {
		this.risk_assessment_mark = risk_assessment_mark;
	}
	public String getID_card_inverse_side() {
		return ID_card_inverse_side;
	}
	public void setID_card_inverse_side(String iD_card_inverse_side) {
		ID_card_inverse_side = iD_card_inverse_side;
	}
	public String getDeposit_bank() {
		return deposit_bank;
	}
	public void setDeposit_bank(String deposit_bank) {
		this.deposit_bank = deposit_bank;
	}

	public Integer getContact_address_id() {
		return contact_address_id;
	}

	public void setContact_address_id(Integer contact_address_id) {
		this.contact_address_id = contact_address_id;
	}

	public Integer getPostal_address_id() {
		return postal_address_id;
	}

	public void setPostal_address_id(Integer postal_address_id) {
		this.postal_address_id = postal_address_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getN_security_id() {
		return n_security_id;
	}

	public void setN_security_id(Integer n_security_id) {
		this.n_security_id = n_security_id;
	}

	public Integer getS_security_id() {
		return s_security_id;
	}

	public void setS_security_id(Integer s_security_id) {
		this.s_security_id = s_security_id;
	}

	public AccountInfo(Integer account_info_id, Integer user_id, String name, String iD_type, String iD_number,
			Integer iD_address_id, String iD_issuance_date, String iD_overdue_date, String iD_licensing_authority,
			Integer contact_address_id, Integer postal_address_id, String trans_password, String fund_password,
			Integer n_security_id, Integer s_security_id, String deposit_bank, String deposit_account,
			String deposit_password, String status, String profession, String education, String email,
			String iD_picture, String iD_card_inverse_side, Integer risk_assessment_mark) {
		super();
		this.account_info_id = account_info_id;
		this.user_id = user_id;
		this.name = name;
		ID_type = iD_type;
		ID_number = iD_number;
		ID_address_id = iD_address_id;
		ID_issuance_date = iD_issuance_date;
		ID_overdue_date = iD_overdue_date;
		ID_licensing_authority = iD_licensing_authority;
		this.contact_address_id = contact_address_id;
		this.postal_address_id = postal_address_id;
		this.trans_password = trans_password;
		Fund_password = fund_password;
		this.n_security_id = n_security_id;
		this.s_security_id = s_security_id;
		this.deposit_bank = deposit_bank;
		this.deposit_account = deposit_account;
		this.deposit_password = deposit_password;
		this.status = status;
		this.profession = profession;
		this.education = education;
		this.email = email;
		ID_picture = iD_picture;
		ID_card_inverse_side = iD_card_inverse_side;
		this.risk_assessment_mark = risk_assessment_mark;
	}
}
