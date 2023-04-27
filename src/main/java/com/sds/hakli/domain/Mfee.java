package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Mfee {

	private Integer mfeepk;
	
	private String feetype;
	
	private BigDecimal feepusat;
	
	private BigDecimal feeprov;
	
	private BigDecimal feekab;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mfee_seq", sequenceName = "mfee_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mfee_seq")
	public Integer getMfeepk() {
		return mfeepk;
	}

	public void setMfeepk(Integer mfeepk) {
		this.mfeepk = mfeepk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	public BigDecimal getFeepusat() {
		return feepusat;
	}

	public void setFeepusat(BigDecimal feepusat) {
		this.feepusat = feepusat;
	}

	public BigDecimal getFeeprov() {
		return feeprov;
	}

	public void setFeeprov(BigDecimal feeprov) {
		this.feeprov = feeprov;
	}

	public BigDecimal getFeekab() {
		return feekab;
	}

	public void setFeekab(BigDecimal feekab) {
		this.feekab = feekab;
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
