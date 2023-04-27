package com.sds.hakli.domain;

public class Branch {
	
	private Long id;
	private String branchname;
	private Region mregion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBranchname() {
		return branchname;
	}
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	public Region getMregion() {
		return mregion;
	}
	public void setMregion(Region mregion) {
		this.mregion = mregion;
	}
	
}
