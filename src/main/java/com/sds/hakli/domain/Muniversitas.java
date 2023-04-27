package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
public class Muniversitas {

	private Long muniversitaspk;
	
	private String universitas;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "muniversitas_seq", sequenceName = "muniversitas_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "muniversitas_seq")
	public Long getMuniversitaspk() {
		return muniversitaspk;
	}

	public void setMuniversitaspk(Long muniversitaspk) {
		this.muniversitaspk = muniversitaspk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getUniversitas() {
		return universitas;
	}

	public void setUniversitas(String universitas) {
		this.universitas = universitas;
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
