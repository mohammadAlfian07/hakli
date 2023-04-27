package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mkabupaten {

	private Integer mkabupatenpk;
	
	private String provcode;
	
	private String kabcode;
	
	private String kabname;

	@Id
	@SequenceGenerator(name = "mkabupaten_seq", sequenceName = "mkabupaten_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mkabupaten_seq")
	public Integer getMkabupatenpk() {
		return mkabupatenpk;
	}

	public void setMkabupatenpk(Integer mkabupatenpk) {
		this.mkabupatenpk = mkabupatenpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}
	
	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getKabcode() {
		return kabcode;
	}

	public void setKabcode(String kabcode) {
		this.kabcode = kabcode;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getKabname() {
		return kabname;
	}

	public void setKabname(String kabname) {
		this.kabname = kabname;
	}

}
