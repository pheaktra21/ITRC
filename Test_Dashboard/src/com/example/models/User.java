package com.example.models;

public class User {

	private String name;
	private String password;
	private String type;
	
	public static final String TYPE = "type"; 
	public static final String TYPE_NURSE = "nurse";
	public static final String TYPE_MONITOR = "monitor";
	public static final String TYPE_PATIENT = "patient";
	public static final String TYPE_GUADIAN = "guadian";
	public static final String TYPE_ADMIN = "admin";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
