package com.sds.hakli.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mkepegawaiansub {

	private Long mkepegawaiansubpk;
	
	private String kepegawaiansub;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;
	
	private Mkepegawaian mkepegawaian;

	@Id
	@SequenceGenerator(name = "mkepegawaiansub_seq", sequenceName = "mkepegawaiansub_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mkepegawaiansub_seq")
	public Long getMkepegawaiansubpk() {
		return mkepegawaiansubpk;
	}

	public void setMkepegawaiansubpk(Long mkepegawaiansubpk) {
		this.mkepegawaiansubpk = mkepegawaiansubpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getKepegawaiansub() {
		return kepegawaiansub;
	}

	public void setKepegawaiansub(String kepegawaiansub) {
		this.kepegawaiansub = kepegawaiansub;
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

	@ManyToOne
	@JoinColumn(name = "mkepegawaianfk")
	public Mkepegawaian getMkepegawaian() {
		return mkepegawaian;
	}

	public void setMkepegawaian(Mkepegawaian mkepegawaian) {
		this.mkepegawaian = mkepegawaian;
	}
	
}
