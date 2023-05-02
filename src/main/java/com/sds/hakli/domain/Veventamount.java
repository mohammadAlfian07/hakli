package com.sds.hakli.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Veventamount {

	private Integer teventpk;
	private String eventname;
	private Integer totalreg;
	private BigDecimal invamount;
	private BigDecimal paymentamount;
	
	@Id
	public Integer getTeventpk() {
		return teventpk;
	}
	public void setTeventpk(Integer teventpk) {
		this.teventpk = teventpk;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public Integer getTotalreg() {
		return totalreg;
	}
	public void setTotalreg(Integer totalreg) {
		this.totalreg = totalreg;
	}
	public BigDecimal getInvamount() {
		return invamount;
	}
	public void setInvamount(BigDecimal invamount) {
		this.invamount = invamount;
	}
	public BigDecimal getPaymentamount() {
		return paymentamount;
	}
	public void setPaymentamount(BigDecimal paymentamount) {
		this.paymentamount = paymentamount;
	}
	
	
}
