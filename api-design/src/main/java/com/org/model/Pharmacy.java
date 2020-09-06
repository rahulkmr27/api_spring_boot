package com.org.model;

import java.math.BigDecimal;

public class Pharmacy {

	private String pharmacyName;
	private String address;
	private String city;
	private String state;
	private Integer zipcode;
	private Double storeLatitude;
	private Double storeLongitude;
	private BigDecimal distanceFromUser;
	
	public Pharmacy(String pharmacyName, String address, String city, String state, Integer zipcode,
			Double storeLatitude, Double storeLongitude) {
		super();
		this.pharmacyName = pharmacyName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.storeLatitude = storeLatitude;
		this.storeLongitude = storeLongitude;
	}
	
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	public Double getStoreLatitude() {
		return storeLatitude;
	}
	public void setStoreLatitude(Double storeLatitude) {
		this.storeLatitude = storeLatitude;
	}
	public Double getStoreLongitude() {
		return storeLongitude;
	}
	public void setStoreLongitude(Double storeLongitude) {
		this.storeLongitude = storeLongitude;
	}
	public BigDecimal getDistanceFromUser() {
		return distanceFromUser;
	}
	public void setDistanceFromUser(BigDecimal distanceFromUser) {
		this.distanceFromUser = distanceFromUser;
	}
}
