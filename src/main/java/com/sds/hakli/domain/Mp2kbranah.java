package com.sds.hakli.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mp2kbranah {

	private Integer mp2kbranahpk;
	
	private String ranah;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mp2kbranah_seq", sequenceName = "mp2kbranah_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mp2kbranah_seq")
	public Integer getMp2kbranahpk() {
		return mp2kbranahpk;
	}

	public void setMp2kbranahpk(Integer mp2kbranahpk) {
		this.mp2kbranahpk = mp2kbranahpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getRanah() {
		return ranah;
	}

	public void setRanah(String ranah) {
		this.ranah = ranah;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

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
