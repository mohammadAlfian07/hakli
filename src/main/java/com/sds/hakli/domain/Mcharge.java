package com.sds.hakli.domain;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Mcharge {

	private Integer mchargepk;
	
	private String chargetype;
	
	private String chargedesc;
	
	private BigDecimal chargeamount;
	
	private Date createtime;
	
	private String createdby;
	
	private String isbase;
	
	private Date lastupdated;
	
	private String updatedby;

	@Id
	@SequenceGenerator(name = "mcharge_seq", sequenceName = "mcharge_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mcharge_seq")
	public Integer getMchargepk() {
		return mchargepk;
	}

	public void setMchargepk(Integer mchargepk) {
		this.mchargepk = mchargepk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getChargedesc() {
		return chargedesc;
	}

	public void setChargedesc(String chargedesc) {
		this.chargedesc = chargedesc;
	}

	public BigDecimal getChargeamount() {
		return chargeamount;
	}

	public void setChargeamount(BigDecimal chargeamount) {
		this.chargeamount = chargeamount;
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

	public String getIsbase() {
		return isbase;
	}

	public void setIsbase(String isbase) {
		this.isbase = isbase;
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
