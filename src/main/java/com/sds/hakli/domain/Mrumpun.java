package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
public class Mrumpun {

	private Integer mrumpunpk;
	
	private String rumpun;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mrumpun_seq", sequenceName = "mrumpun_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mrumpun_seq")
	public Integer getMrumpunpk() {
		return mrumpunpk;
	}

	public void setMrumpunpk(Integer mrumpunpk) {
		this.mrumpunpk = mrumpunpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getRumpun() {
		return rumpun;
	}

	public void setRumpun(String rumpun) {
		this.rumpun = rumpun;
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
