package com.sds.hakli.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
public class Msubrumpunsdmk {

	@JsonbProperty("id")
	private Long msubrumpunsdmkpk;
	
	private String subrumpunsdmk;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;

	@JsonbProperty("rumpunsdmk")
	private Mrumpunsdmk mrumpunsdmk;

	@Id
	@SequenceGenerator(name = "mrumpunsdmk_seq", sequenceName = "mrumpunsdmk_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mrumpunsdmk_seq")
	public Long getMsubrumpunsdmkpk() {
		return msubrumpunsdmkpk;
	}

	public void setMsubrumpunsdmkpk(Long msubrumpunsdmkpk) {
		this.msubrumpunsdmkpk = msubrumpunsdmkpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getSubrumpunsdmk() {
		return subrumpunsdmk;
	}

	public void setSubrumpunsdmk(String subrumpunsdmk) {
		this.subrumpunsdmk = subrumpunsdmk;
	}

	public Date getCreatetime() {
		return createtime;
	}

	@JsonbTransient
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getCreatedby() {
		return createdby;
	}

	@JsonbTransient
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	@JsonbTransient
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getUpdatedby() {
		return updatedby;
	}

	@JsonbTransient
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@ManyToOne
	@JoinColumn(name = "mrumpunsdmkfk")
	public Mrumpunsdmk getMrumpunsdmk() {
		return mrumpunsdmk;
	}

	public void setMrumpunsdmk(Mrumpunsdmk mrumpunsdmk) {
		this.mrumpunsdmk = mrumpunsdmk;
	}
	
}
