package com.org.model;

import java.math.BigDecimal;

public class PharmacyLocationResponse {

	private String pharmcyName;
	private String pharamcyNameWithAddress;
	private BigDecimal distance;
	
	public String getPharamcyNameWithAddress() {
		return pharamcyNameWithAddress;
	}
	public void setPharamcyNameWithAddress(String pharamcyNameWithAddress) {
		this.pharamcyNameWithAddress = pharamcyNameWithAddress;
	}

	public String getPharmcyName() {
		return pharmcyName;
	}
	public void setPharmcyName(String pharmcyName) {
		this.pharmcyName = pharmcyName;
	}
	
	public BigDecimal getDistance() {
		return distance;
	}
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
	
}
