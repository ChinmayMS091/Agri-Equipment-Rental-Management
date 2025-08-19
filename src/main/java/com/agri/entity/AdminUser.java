package com.agri.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AdminUser")

public class AdminUser {

	@Id
	private String userId;
	private String password;
	private String name;
	
	public AdminUser() {}
	
	
	public AdminUser(String userId, String password, String name) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
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
	
}