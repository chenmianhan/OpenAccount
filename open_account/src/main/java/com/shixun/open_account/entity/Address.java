package com.shixun.open_account.entity;

public class Address {
	private Integer aid;
	private String province;
	private String city;
	private String street;
	public Address(Integer aid, String province, String city, String street) {
		super();
		this.aid = aid;
		this.province = province;
		this.city = city;
		this.street = street;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
}
