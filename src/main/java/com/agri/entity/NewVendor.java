package com.agri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="NewVendor")
public class NewVendor {

	@Id
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "VendorName")
	private String VendorName;
	
	@Column(name = "VendorAddress")
	private String VendorAddress;
	
	@Column(name = "VContactNumber")
	private String VContactNumber;
	
	
	@Column(name = "emailto")
	private String emailto;
	
	

	public String getEmailto() {
		return emailto;
	}

	public void setEmailto(String emailto) {
		this.emailto = emailto;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getVendorName() {
		return VendorName;
	}

	public String getVendorAddress() {
		return VendorAddress;
	}

	public String getVContactNumber() {
		return VContactNumber;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	public void setVendorAddress(String vendorAddress) {
		VendorAddress = vendorAddress;
	}

	public void setVContactNumber(String vContactNumber) {
		VContactNumber = vContactNumber;
	}

}
