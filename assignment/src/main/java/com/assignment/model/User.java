package com.assignment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tbl_user")
public class User {
	
	public User() {
		super();
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;	
		
	@Column(name="user_name")
	private String  name;
	
	@Column(name="mobile_number", unique=true)
	private String  mobile;
	
	@Column(name="user_password")
	private String  password;
	
	@Column(name="user_role")
	private String  role;
	
	@Column(name="user_enabled")
	private boolean enabled;
		
	@Column(name="user_password_read")
	private String  userPasswordRead;
	
	@Column(name="user_photo")
	private String userPhoto;
	
	
	@Column(name="city_name")
	private String CityName;
	

	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items;


	
	

	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getUserPasswordRead() {
		return userPasswordRead;
	}


	public void setUserPasswordRead(String userPasswordRead) {
		this.userPasswordRead = userPasswordRead;
	}


	public String getUserPhoto() {
		return userPhoto;
	}


	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}


	public String getCityName() {
		return CityName;
	}


	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	
	

}
