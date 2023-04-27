package com.sds.hakli.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
public class Tfeeshare {
	
	private Integer tfeesharepk;
	
	private Tinvoice tinvoice;
	
	private BigDecimal feepusat;
	
	private BigDecimal feeprov;
	
	private BigDecimal feekab;
	
	private Date sharetime;
	
	@Id
	@SequenceGenerator(name = "tfeeshare_seq", sequenceName = "tfeeshare_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tfeeshare_seq")
	public Integer getTfeesharepk() {
		return tfeesharepk;
	}

	public void setTfeesharepk(Integer tfeesharepk) {
		this.tfeesharepk = tfeesharepk;
	}

	@ManyToOne
	@JoinColumn(name = "tinvoicefk")
	public Tinvoice getTinvoice() {
		return tinvoice;
	}

	public void setTinvoice(Tinvoice tinvoice) {
		this.tinvoice = tinvoice;
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
	public Date getSharetime() {
		return sharetime;
	}

	public void setSharetime(Date sharetime) {
		this.sharetime = sharetime;
	}

	

}
