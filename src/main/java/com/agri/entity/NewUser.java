package com.agri.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="newuser")
public class NewUser {

	@Id
	private String userId;
	private String password;
	private String name;
	private String address;
	private String contactnumber;
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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	@Override
	public String toString() {
		return "NewUser [userId=" + userId + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", contactnumber=" + contactnumber + ", emailto=" + emailto + "]";
	}
	
	
	
	
	
}
