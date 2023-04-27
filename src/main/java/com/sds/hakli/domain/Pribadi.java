package com.sds.hakli.domain;

import java.util.Date;

import javax.json.bind.annotation.JsonbTransient;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pribadi {

	private Long anggotaid;
	
	private Long branchid;
	
	private String branchname;

	private String noanggota;

	private String nama;

	private String noktp;

	private String tempatlahir;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date tgllahir;

	private String gender;

	private String agama;
	
	private String warganegara;
	
	private Long nationid;
	
	private String nation;

	private String provcode;
	
	private String provname;

	private String kabcode;
	
	private String kabname;

	private String alamat;

	private String kodepos;

	private String telp;

	private String hp;

	private String email;

	private String statusanggota;
	
	private String namadarurat;
	
	private String hubungan;
	
	private String nohpdarurat;
	
	private String photolink;
	
	private String userid;
	
	private String password;
	
	private String token;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date lastlogin;

	public Long getAnggotaid() {
		return anggotaid;
	}

	public void setAnggotaid(Long anggotaid) {
		this.anggotaid = anggotaid;
	}

	public Long getBranchid() {
		return branchid;
	}

	public void setBranchid(Long branchid) {
		this.branchid = branchid;
	}
	
	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getNoanggota() {
		return noanggota;
	}

	public void setNoanggota(String noanggota) {
		this.noanggota = noanggota;
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

	public String getWarganegara() {
		return warganegara;
	}

	public void setWarganegara(String warganegara) {
		this.warganegara = warganegara;
	}
	
	public Long getNationid() {
		return nationid;
	}

	public void setNationid(Long nationid) {
		this.nationid = nationid;
	}

	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

	public String getKabcode() {
		return kabcode;
	}

	public void setKabcode(String kabcode) {
		this.kabcode = kabcode;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	public String getKabname() {
		return kabname;
	}

	public void setKabname(String kabname) {
		this.kabname = kabname;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
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

	public String getNamadarurat() {
		return namadarurat;
	}

	public void setNamadarurat(String namadarurat) {
		this.namadarurat = namadarurat;
	}

	public String getHubungan() {
		return hubungan;
	}

	public void setHubungan(String hubungan) {
		this.hubungan = hubungan;
	}

	public String getNohpdarurat() {
		return nohpdarurat;
	}

	public void setNohpdarurat(String nohpdarurat) {
		this.nohpdarurat = nohpdarurat;
	}

	public String getPhotolink() {
		return photolink;
	}

	@JsonbTransient
	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	

}
