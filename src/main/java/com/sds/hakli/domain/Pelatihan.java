package com.sds.hakli.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pelatihan {
	
	private Long pelatihanid;
	
	private Long anggotaid;
	
	private String namadiklat;
	
	private String tempatpelaksanaan;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglmulai;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tglakhir;
	
	private Integer lamahari;
	
	private Integer lamajam;
	
	private Long rumpundiklatid;
	
	private String rumpundiklat;
	
	private Long jenisdiklatid;
	
	private String jenisdiklat;
	
	private Long akreditasiid;
	
	private String akreditasi;

	public Long getPelatihanid() {
		return pelatihanid;
	}

	public void setPelatihanid(Long pelatihanid) {
		this.pelatihanid = pelatihanid;
	}

	public Long getAnggotaid() {
		return anggotaid;
	}

	public void setAnggotaid(Long anggotaid) {
		this.anggotaid = anggotaid;
	}

	public String getNamadiklat() {
		return namadiklat;
	}

	public void setNamadiklat(String namadiklat) {
		this.namadiklat = namadiklat;
	}

	public String getTempatpelaksanaan() {
		return tempatpelaksanaan;
	}

	public void setTempatpelaksanaan(String tempatpelaksanaan) {
		this.tempatpelaksanaan = tempatpelaksanaan;
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

	public Integer getLamahari() {
		return lamahari;
	}

	public void setLamahari(Integer lamahari) {
		this.lamahari = lamahari;
	}

	public Integer getLamajam() {
		return lamajam;
	}

	public void setLamajam(Integer lamajam) {
		this.lamajam = lamajam;
	}

	public Long getRumpundiklatid() {
		return rumpundiklatid;
	}

	public void setRumpundiklatid(Long rumpundiklatid) {
		this.rumpundiklatid = rumpundiklatid;
	}

	public String getRumpundiklat() {
		return rumpundiklat;
	}

	public void setRumpundiklat(String rumpundiklat) {
		this.rumpundiklat = rumpundiklat;
	}

	public Long getJenisdiklatid() {
		return jenisdiklatid;
	}

	public void setJenisdiklatid(Long jenisdiklatid) {
		this.jenisdiklatid = jenisdiklatid;
	}

	public String getJenisdiklat() {
		return jenisdiklat;
	}

	public void setJenisdiklat(String jenisdiklat) {
		this.jenisdiklat = jenisdiklat;
	}

	public Long getAkreditasiid() {
		return akreditasiid;
	}

	public void setAkreditasiid(Long akreditasiid) {
		this.akreditasiid = akreditasiid;
	}

	public String getAkreditasi() {
		return akreditasi;
	}

	public void setAkreditasi(String akreditasi) {
		this.akreditasi = akreditasi;
	}

}
