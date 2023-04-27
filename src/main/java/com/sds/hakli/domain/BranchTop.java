package com.sds.hakli.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BranchTop {

	private long id;
	private String name;
	private long total;
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
}
