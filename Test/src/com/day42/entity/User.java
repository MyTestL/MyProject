package com.day42.entity;

public class User {
	private String username;
	private String password;
	private String url;
	private String loves;  //eat,drink
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLoves() {
		return loves;
	}
	public void setLoves(String loves) {
		this.loves = loves;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", url=" + url + ", loves=" + loves + "]";
	}
	public User(String username, String password, String url, String loves) {
		super();
		this.username = username;
		this.password = password;
		this.url = url;
		this.loves = loves;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
