package com.shixun.open_account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor @NoArgsConstructor
public class Address {
	private Integer aid;
	private String province;
	private String city;
	private String street;
	private String detail;
	public Address(String[] address,String detail) throws Exception {
		if(address.length<3) throw new Exception("length of address json array is not enough");
		this.province = address[0];
		this.city = address[1];
		this.street = address[2];
	}
	public Address(Integer aid,String[] address) throws Exception {
		if(address.length<3) throw new Exception("length of address json array is not enough");
		this.aid = aid;
		this.province = address[0];
		this.city = address[1];
		this.street = address[2];
	}
}
