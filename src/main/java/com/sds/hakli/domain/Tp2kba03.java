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
public class Tp2kba03 {
	
	private Integer tp2kba03pk;
	
	private Tanggota tanggota;
	
	private Mp2kbkegiatan mp2kbkegiatan;
	
	private String judul;
	
	private String penyelenggara;
	
	private String docid;
	
	private String docpath;
	
	private Date tglkegiatan;
	
	private BigDecimal nilaiskp;
	
	private String status;
	
	private String memo;
	
	private String createdby;
	
	private Date createtime;
	
	private String checkedby;
	
	private Date checktime;
	
	@Id
	@SequenceGenerator(name = "tp2kba03_seq", sequenceName = "tp2kba03_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tp2kba03_seq")
	public Integer getTp2kba03pk() {
		return tp2kba03pk;
	}

	public void setTp2kba03pk(Integer tp2kba03pk) {
		this.tp2kba03pk = tp2kba03pk;
	}

	@ManyToOne
	@JoinColumn(name = "tanggotafk")
	public Tanggota getTanggota() {
		return tanggota;
	}

	public void setTanggota(Tanggota tanggota) {
		this.tanggota = tanggota;
	}

	@ManyToOne
	@JoinColumn(name = "mp2kbkegiatanfk")
	public Mp2kbkegiatan getMp2kbkegiatan() {
		return mp2kbkegiatan;
	}

	public void setMp2kbkegiatan(Mp2kbkegiatan mp2kbkegiatan) {
		this.mp2kbkegiatan = mp2kbkegiatan;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getPenyelenggara() {
		return penyelenggara;
	}

	public void setPenyelenggara(String penyelenggara) {
		this.penyelenggara = penyelenggara;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getDocpath() {
		return docpath;
	}

	public void setDocpath(String docpath) {
		this.docpath = docpath;
	}

	public Date getTglkegiatan() {
		return tglkegiatan;
	}

	public void setTglkegiatan(Date tglkegiatan) {
		this.tglkegiatan = tglkegiatan;
	}
	
	public BigDecimal getNilaiskp() {
		return nilaiskp;
	}

	public void setNilaiskp(BigDecimal nilaiskp) {
		this.nilaiskp = nilaiskp;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCheckedby() {
		return checkedby;
	}

	public void setCheckedby(String checkedby) {
		this.checkedby = checkedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	
}
