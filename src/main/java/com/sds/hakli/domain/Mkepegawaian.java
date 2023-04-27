package com.sds.hakli.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mkepegawaian {

	private Integer mkepegawaianpk;
	
	private String kepegawaian;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mkepegawaian_seq", sequenceName = "mkepegawaian_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mkepegawaian_seq")
	public Integer getMkepegawaianpk() {
		return mkepegawaianpk;
	}

	public void setMkepegawaianpk(Integer mkepegawaianpk) {
		this.mkepegawaianpk = mkepegawaianpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getKepegawaian() {
		return kepegawaian;
	}

	public void setKepegawaian(String kepegawaian) {
		this.kepegawaian = kepegawaian;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

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

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
}
