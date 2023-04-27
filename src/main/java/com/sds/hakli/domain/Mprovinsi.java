package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mprovinsi {

	private Integer mprovinsipk;
	
	private String provcode;
	
	private String provname;

	@Id
	@SequenceGenerator(name = "mprovinsi_seq", sequenceName = "mprovinsi_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mprovinsi_seq")
	public Integer getMprovinsipk() {
		return mprovinsipk;
	}

	public void setMprovinsipk(Integer mprovinsipk) {
		this.mprovinsipk = mprovinsipk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

}
