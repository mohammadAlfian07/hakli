package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
public class Mnegara {

	private Integer mnegarapk;
	
	private String negara;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mnegara_seq", sequenceName = "mnegara_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mnegara_seq")
	public Integer getMnegarapk() {
		return mnegarapk;
	}

	public void setMnegarapk(Integer mnegarapk) {
		this.mnegarapk = mnegarapk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getNegara() {
		return negara;
	}

	public void setNegara(String negara) {
		this.negara = negara;
	}

	@Temporal(TemporalType.TIMESTAMP)
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
