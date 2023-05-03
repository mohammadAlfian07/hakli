package com.sds.hakli.pojo;

import javax.json.bind.annotation.JsonbProperty;

public class BrivaStatus {

	private String institutionCode;
	private String BrivaNo;
	private String CustCode;
	private String Nama;
	private String Amount;
	private String Keterangan;
	private String statusBayar;
    private String expiredDate;
    private String lastUpdate;
    
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getBrivaNo() {
		return BrivaNo;
	}
	public void setBrivaNo(String brivaNo) {
		BrivaNo = brivaNo;
	}
	public String getCustCode() {
		return CustCode;
	}
	public void setCustCode(String custCode) {
		CustCode = custCode;
	}
	public String getNama() {
		return Nama;
	}
	public void setNama(String nama) {
		Nama = nama;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getKeterangan() {
		return Keterangan;
	}
	public void setKeterangan(String keterangan) {
		Keterangan = keterangan;
	}
	public String getStatusBayar() {
		return statusBayar;
	}
	public void setStatusBayar(String statusBayar) {
		this.statusBayar = statusBayar;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
    
}
