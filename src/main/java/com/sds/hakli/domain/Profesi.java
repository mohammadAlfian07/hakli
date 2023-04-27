package com.sds.hakli.domain;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

import java.util.Date;

public class Profesi {

	private Long id;
	
	private String profesi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfesi() {
		return profesi;
	}

	public void setProfesi(String profesi) {
		this.profesi = profesi;
	}
	
	
}
