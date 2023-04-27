package com.sds.hakli.domain;

public class RegAnggota {

	private Pribadi pribadi;
	private Pekerjaan pekerjaan;
	private Pendidikan pendidikan;
	private Pelatihan pelatihan;
	private Sertifikasi sertifikasi;
	
	public Pribadi getPribadi() {
		return pribadi;
	}
	public void setPribadi(Pribadi pribadi) {
		this.pribadi = pribadi;
	}
	public Pekerjaan getPekerjaan() {
		return pekerjaan;
	}
	public void setPekerjaan(Pekerjaan pekerjaan) {
		this.pekerjaan = pekerjaan;
	}
	public Pendidikan getPendidikan() {
		return pendidikan;
	}
	public void setPendidikan(Pendidikan pendidikan) {
		this.pendidikan = pendidikan;
	}
	public Pelatihan getPelatihan() {
		return pelatihan;
	}
	public void setPelatihan(Pelatihan pelatihan) {
		this.pelatihan = pelatihan;
	}
	public Sertifikasi getSertifikasi() {
		return sertifikasi;
	}
	public void setSertifikasi(Sertifikasi sertifikasi) {
		this.sertifikasi = sertifikasi;
	}
	
}
