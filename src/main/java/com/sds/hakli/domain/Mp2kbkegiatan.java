package com.sds.hakli.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class Mp2kbkegiatan {

	private Integer mp2kbkegiatanpk;
	
	private String idkegiatan;
	
	private String kegiatan;
	
	private Date createtime;
	
	private String createdby;
	
	private Date lastupdated;
	
	private String updatedby;
	
	private Mp2kbranah mp2kbranah;

	@Id
	@SequenceGenerator(name = "mp2kbkegiatan_seq", sequenceName = "mp2kbkegiatan_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mp2kbkegiatan_seq")
	public Integer getMp2kbkegiatanpk() {
		return mp2kbkegiatanpk;
	}

	public void setMp2kbkegiatanpk(Integer mp2kbkegiatanpk) {
		this.mp2kbkegiatanpk = mp2kbkegiatanpk;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getIdkegiatan() {
		return idkegiatan;
	}

	public void setIdkegiatan(String idkegiatan) {
		this.idkegiatan = idkegiatan;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getKegiatan() {
		return kegiatan;
	}

	public void setKegiatan(String kegiatan) {
		this.kegiatan = kegiatan;
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
	@JoinColumn(name="mp2kbranahfk")
	public Mp2kbranah getMp2kbranah() {
		return mp2kbranah;
	}

	public void setMp2kbranah(Mp2kbranah mp2kbranah) {
		this.mp2kbranah = mp2kbranah;
	}

}
