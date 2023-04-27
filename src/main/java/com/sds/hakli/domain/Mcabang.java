package com.sds.hakli.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mcabang {

	private Integer mcabangpk;
	
	private String cabang;
	
	private String provcode;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;
	
	private Mprovinsi mprovinsi;

	@Id
	@SequenceGenerator(name = "mcabang_seq", sequenceName = "mcabang_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mcabang_seq")
	public Integer getMcabangpk() {
		return mcabangpk;
	}

	public void setMcabangpk(Integer mcabangpk) {
		this.mcabangpk = mcabangpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getCabang() {
		return cabang;
	}

	public void setCabang(String cabang) {
		this.cabang = cabang;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

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

	@ManyToOne
	@JoinColumn(name="mprovinsifk")
	public Mprovinsi getMprovinsi() {
		return mprovinsi;
	}

	public void setMprovinsi(Mprovinsi mprovinsi) {
		this.mprovinsi = mprovinsi;
	}

}
