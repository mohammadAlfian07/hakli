package com.sds.hakli.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Sertifikasi {
	
	private Long sertifikasiid;
	
	private Long anggotaid;
	
	private String nostr;
	
	private String nosip;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglterbitstr;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglakhirstr;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglizinpraktek;
	
	private Long profesiid;
	
	private String profesi;

	public Long getSertifikasiid() {
		return sertifikasiid;
	}

	public void setSertifikasiid(Long sertifikasiid) {
		this.sertifikasiid = sertifikasiid;
	}

	public Long getAnggotaid() {
		return anggotaid;
	}

	public void setAnggotaid(Long anggotaid) {
		this.anggotaid = anggotaid;
	}

	public String getNostr() {
		return nostr;
	}

	public void setNostr(String nostr) {
		this.nostr = nostr;
	}

	public String getNosip() {
		return nosip;
	}

	public void setNosip(String nosip) {
		this.nosip = nosip;
	}

	public Date getTglterbitstr() {
		return tglterbitstr;
	}

	public void setTglterbitstr(Date tglterbitstr) {
		this.tglterbitstr = tglterbitstr;
	}

	public Date getTglakhirstr() {
		return tglakhirstr;
	}

	public void setTglakhirstr(Date tglakhirstr) {
		this.tglakhirstr = tglakhirstr;
	}

	public Date getTglizinpraktek() {
		return tglizinpraktek;
	}

	public void setTglizinpraktek(Date tglizinpraktek) {
		this.tglizinpraktek = tglizinpraktek;
	}

	public Long getProfesiid() {
		return profesiid;
	}

	public void setProfesiid(Long profesiid) {
		this.profesiid = profesiid;
	}

	public String getProfesi() {
		return profesi;
	}

	public void setProfesi(String profesi) {
		this.profesi = profesi;
	}
	
}
