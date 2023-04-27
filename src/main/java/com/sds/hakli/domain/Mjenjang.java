package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
public class Mjenjang {

	private Integer mjenjangpk;
	
	private String jenjang;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mjenjang_seq", sequenceName = "mjenjang_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mjenjang_seq")
	public Integer getMjenjangpk() {
		return mjenjangpk;
	}

	public void setMjenjangpk(Integer mjenjangpk) {
		this.mjenjangpk = mjenjangpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getJenjang() {
		return jenjang;
	}

	public void setJenjang(String jenjang) {
		this.jenjang = jenjang;
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
