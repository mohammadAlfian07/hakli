package com.sds.hakli.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Anggota {

	private Integer id;
	private String noanggota;
	private String nostr;
	private String nama;
	private String noktp;
	private String tempatlahir;
	private Date tgllahir;
	private String gender;
	private String agama;
	private String gelardepan;
	private String gelarbelakang;
	private String alamat;
	private String kota;
	private String kodepos;
	private String telp;
	private String hp;
	private String email;
	private String statusanggota;
	private String peminatan1;
	private String peminatan2;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
	private Date periodeawal;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
	private Date periodeakhir;
	private String namakantor;
	private String alamatkantor;
	private String kotakantor;
	private String statuspegawai;
	private String jabatankantor;
	private String noskkantor;
	private String keterangankerja;
	private String telpkantor;
	private String faxkantor;
	private String tempatpraktik;
	private String alamatpraktik;
	private String kotapraktik;
	private String jabatanpraktik;
	private String noskpraktik;
	private String photolink;
	private Branch branch;
	private University university;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNoanggota() {
		return noanggota;
	}
	public void setNoanggota(String noanggota) {
		this.noanggota = noanggota;
	}
	public String getNostr() {
		return nostr;
	}
	public void setNostr(String nostr) {
		this.nostr = nostr;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNoktp() {
		return noktp;
	}
	public void setNoktp(String noktp) {
		this.noktp = noktp;
	}
	public String getTempatlahir() {
		return tempatlahir;
	}
	public void setTempatlahir(String tempatlahir) {
		this.tempatlahir = tempatlahir;
	}
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
	public Date getTgllahir() {
		return tgllahir;
	}
	public void setTgllahir(Date tgllahir) {
		this.tgllahir = tgllahir;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgama() {
		return agama;
	}
	public void setAgama(String agama) {
		this.agama = agama;
	}
	public String getGelardepan() {
		return gelardepan;
	}
	public void setGelardepan(String gelardepan) {
		this.gelardepan = gelardepan;
	}
	public String getGelarbelakang() {
		return gelarbelakang;
	}
	public void setGelarbelakang(String gelarbelakang) {
		this.gelarbelakang = gelarbelakang;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	public String getKodepos() {
		return kodepos;
	}
	public void setKodepos(String kodepos) {
		this.kodepos = kodepos;
	}
	public String getTelp() {
		return telp;
	}
	public void setTelp(String telp) {
		this.telp = telp;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatusanggota() {
		return statusanggota;
	}
	public void setStatusanggota(String statusanggota) {
		this.statusanggota = statusanggota;
	}
	public String getPeminatan1() {
		return peminatan1;
	}
	public void setPeminatan1(String peminatan1) {
		this.peminatan1 = peminatan1;
	}
	public String getPeminatan2() {
		return peminatan2;
	}
	public void setPeminatan2(String peminatan2) {
		this.peminatan2 = peminatan2;
	}
	public Date getPeriodeawal() {
		return periodeawal;
	}
	public void setPeriodeawal(Date periodeawal) {
		this.periodeawal = periodeawal;
	}
	public Date getPeriodeakhir() {
		return periodeakhir;
	}
	public void setPeriodeakhir(Date periodeakhir) {
		this.periodeakhir = periodeakhir;
	}
	public String getNamakantor() {
		return namakantor;
	}
	public void setNamakantor(String namakantor) {
		this.namakantor = namakantor;
	}
	public String getAlamatkantor() {
		return alamatkantor;
	}
	public void setAlamatkantor(String alamatkantor) {
		this.alamatkantor = alamatkantor;
	}
	public String getKotakantor() {
		return kotakantor;
	}
	public void setKotakantor(String kotakantor) {
		this.kotakantor = kotakantor;
	}
	public String getStatuspegawai() {
		return statuspegawai;
	}
	public void setStatuspegawai(String statuspegawai) {
		this.statuspegawai = statuspegawai;
	}
	public String getJabatankantor() {
		return jabatankantor;
	}
	public void setJabatankantor(String jabatankantor) {
		this.jabatankantor = jabatankantor;
	}
	public String getNoskkantor() {
		return noskkantor;
	}
	public void setNoskkantor(String noskkantor) {
		this.noskkantor = noskkantor;
	}
	public String getKeterangankerja() {
		return keterangankerja;
	}
	public void setKeterangankerja(String keterangankerja) {
		this.keterangankerja = keterangankerja;
	}
	public String getTelpkantor() {
		return telpkantor;
	}
	public void setTelpkantor(String telpkantor) {
		this.telpkantor = telpkantor;
	}
	public String getFaxkantor() {
		return faxkantor;
	}
	public void setFaxkantor(String faxkantor) {
		this.faxkantor = faxkantor;
	}
	public String getTempatpraktik() {
		return tempatpraktik;
	}
	public void setTempatpraktik(String tempatpraktik) {
		this.tempatpraktik = tempatpraktik;
	}
	public String getAlamatpraktik() {
		return alamatpraktik;
	}
	public void setAlamatpraktik(String alamatpraktik) {
		this.alamatpraktik = alamatpraktik;
	}
	public String getKotapraktik() {
		return kotapraktik;
	}
	public void setKotapraktik(String kotapraktik) {
		this.kotapraktik = kotapraktik;
	}
	public String getJabatanpraktik() {
		return jabatanpraktik;
	}
	public void setJabatanpraktik(String jabatanpraktik) {
		this.jabatanpraktik = jabatanpraktik;
	}
	public String getNoskpraktik() {
		return noskpraktik;
	}
	public void setNoskpraktik(String noskpraktik) {
		this.noskpraktik = noskpraktik;
	}
	public String getPhotolink() {
		return photolink;
	}
	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
}
