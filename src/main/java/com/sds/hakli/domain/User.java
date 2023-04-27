package com.sds.hakli.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

	private Long id;
	private String userid;
	private String username;
	private String password;
	private String rolecode;
	private String token;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date lastlogin;
	private Long branchid;
	private String branchname;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
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
	public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Long getBranchid() {
		return branchid;
	}
	public void setBranchid(Long branchid) {
		this.branchid = branchid;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	
	
}
