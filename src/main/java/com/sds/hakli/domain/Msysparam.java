package com.sds.hakli.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;



/**
 * The persistent class for the MSYSPARAM database table.
 * 
 */
@Entity
@Table(name="MSYSPARAM")
@NamedQuery(name="Msysparam.findAll", query="SELECT m FROM Msysparam m")
public class Msysparam implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ismasked;
	private Date lastupdated;
	private int orderno;
	private String paramdesc;
	private String paramcode;
	private String paramgroup;
	private String paramvalue;
	private String updatedby;

	public Msysparam() {
	}

	
	@Id
	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getParamcode() {
		return paramcode;
	}

	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}


	@Column(length = 1)
	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getIsmasked() {
		return ismasked;
	}

	public void setIsmasked(String ismasked) {
		this.ismasked = ismasked;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastupdated() {
		return this.lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	
	public int getOrderno() {
		return orderno;
	}


	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}


	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getParamdesc() {
		return paramdesc;
	}


	public void setParamdesc(String paramdesc) {
		this.paramdesc = paramdesc;
	}


	@Column(length=30)
	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getParamgroup() {
		return this.paramgroup;
	}

	public void setParamgroup(String paramgroup) {
		this.paramgroup = paramgroup;
	}


	@Column(length=100)
	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}


	@Column(length=15)
	@Type(type = "com.sds.utils.usertype.TrimUpperCaseUserType")
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

}