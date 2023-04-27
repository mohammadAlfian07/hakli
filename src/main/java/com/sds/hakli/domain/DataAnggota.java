package com.sds.hakli.domain;

import java.util.List;

public class DataAnggota {

	private Pribadi pribadi;
	private List<Pekerjaan> daftarpekerjaan;
	private List<Pendidikan> daftarpendidikan;
	private List<Pelatihan> daftarpelatihan;
	private List<Sertifikasi> daftarsertifikasi;
	
	public Pribadi getPribadi() {
		return pribadi;
	}
	public void setPribadi(Pribadi pribadi) {
		this.pribadi = pribadi;
	}
	public List<Pekerjaan> getDaftarpekerjaan() {
		return daftarpekerjaan;
	}
	public void setDaftarpekerjaan(List<Pekerjaan> daftarpekerjaan) {
		this.daftarpekerjaan = daftarpekerjaan;
	}
	public List<Pendidikan> getDaftarpendidikan() {
		return daftarpendidikan;
	}
	public void setDaftarpendidikan(List<Pendidikan> daftarpendidikan) {
		this.daftarpendidikan = daftarpendidikan;
	}
	public List<Pelatihan> getDaftarpelatihan() {
		return daftarpelatihan;
	}
	public void setDaftarpelatihan(List<Pelatihan> daftarpelatihan) {
		this.daftarpelatihan = daftarpelatihan;
	}
	public List<Sertifikasi> getDaftarsertifikasi() {
		return daftarsertifikasi;
	}
	public void setDaftarsertifikasi(List<Sertifikasi> daftarsertifikasi) {
		this.daftarsertifikasi = daftarsertifikasi;
	}
}
