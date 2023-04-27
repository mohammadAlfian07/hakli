package com.sds.hakli.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pekerjaan {
	
	private Long pekerjaanid;
	
	private Long anggotaid;
	
	private String nip;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglmulai;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglakhir;
	
	private Long rumpunid;
	
	private String rumpun;
	
	private Long rumpunsdmkid;
	
	private String rumpunsdmk;
	
	private Long subrumpunsdmkid;
	
	private String subrumpunsdmk;
	
	private Long kepegawaianid;
	
	private String kepegawaian;
	
	private Long kepegawaiansdmkid;
	
	private String kepegawaiansdmk;
	
	private String provcode;
	
	private String provname;
	
	private String kabcode;
	
	private String kabname;

	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	public String getKabcode() {
		return kabcode;
	}

	public void setKabcode(String kabcode) {
		this.kabcode = kabcode;
	}

	public String getKabname() {
		return kabname;
	}

	public void setKabname(String kabname) {
		this.kabname = kabname;
	}

	public Long getPekerjaanid() {
		return pekerjaanid;
	}

	public void setPekerjaanid(Long pekerjaanid) {
		this.pekerjaanid = pekerjaanid;
	}

	public Long getAnggotaid() {
		return anggotaid;
	}

	public void setAnggotaid(Long anggotaid) {
		this.anggotaid = anggotaid;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public Date getTglmulai() {
		return tglmulai;
	}

	public void setTglmulai(Date tglmulai) {
		this.tglmulai = tglmulai;
	}

	public Date getTglakhir() {
		return tglakhir;
	}

	public void setTglakhir(Date tglakhir) {
		this.tglakhir = tglakhir;
	}

	public Long getRumpunid() {
		return rumpunid;
	}

	public void setRumpunid(Long rumpunid) {
		this.rumpunid = rumpunid;
	}

	public String getRumpun() {
		return rumpun;
	}

	public void setRumpun(String rumpun) {
		this.rumpun = rumpun;
	}

	public Long getRumpunsdmkid() {
		return rumpunsdmkid;
	}

	public void setRumpunsdmkid(Long rumpunsdmkid) {
		this.rumpunsdmkid = rumpunsdmkid;
	}

	public String getRumpunsdmk() {
		return rumpunsdmk;
	}

	public void setRumpunsdmk(String rumpunsdmk) {
		this.rumpunsdmk = rumpunsdmk;
	}

	public Long getSubrumpunsdmkid() {
		return subrumpunsdmkid;
	}

	public void setSubrumpunsdmkid(Long subrumpunsdmkid) {
		this.subrumpunsdmkid = subrumpunsdmkid;
	}

	public String getSubrumpunsdmk() {
		return subrumpunsdmk;
	}

	public void setSubrumpunsdmk(String subrumpunsdmk) {
		this.subrumpunsdmk = subrumpunsdmk;
	}

	public Long getKepegawaianid() {
		return kepegawaianid;
	}

	public void setKepegawaianid(Long kepegawaianid) {
		this.kepegawaianid = kepegawaianid;
	}

	public String getKepegawaian() {
		return kepegawaian;
	}

	public void setKepegawaian(String kepegawaian) {
		this.kepegawaian = kepegawaian;
	}

	public Long getKepegawaiansdmkid() {
		return kepegawaiansdmkid;
	}

	public void setKepegawaiansdmkid(Long kepegawaiansdmkid) {
		this.kepegawaiansdmkid = kepegawaiansdmkid;
	}

	public String getKepegawaiansdmk() {
		return kepegawaiansdmk;
	}

	public void setKepegawaiansdmk(String kepegawaiansdmk) {
		this.kepegawaiansdmk = kepegawaiansdmk;
	}
	
	
}
