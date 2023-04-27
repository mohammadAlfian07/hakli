package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
public class Muser {

	private Integer muserpk;
	
	private String userid;
	
	private String username;
	
	private String password;
	
	private Date lastlogin;
	
	private Date lastupdated;
	
	private String updatedby;
	
	public Muser() {

	}

	@Id
	@SequenceGenerator(name = "muser_seq", sequenceName = "muser_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "muser_seq")
	public Integer getMuserpk() {
		return muserpk;
	}

	public void setMuserpk(Integer muserpk) {
		this.muserpk = muserpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUpperCaseUserType")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Type(type = "com.sds.utils.usertype.TrimUpperCaseUserType")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;// BcryptUtil.bcryptHash(password);;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

}
