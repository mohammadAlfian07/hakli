package com.sds.hakli.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the TCOUNTERENGINE database table.
 * 
 */
@Entity
@Table(name="TCOUNTERENGINE")
@NamedQuery(name="Tcounterengine.findAll", query="SELECT t FROM Tcounterengine t")
public class Tcounterengine implements Serializable {
	private static final long serialVersionUID = 1L;
	private String countername;
	private Integer lastcounter;

	public Tcounterengine() {
	}


	@Id
	@Column(length=30)
	@Type(type = "com.sds.utils.usertype.TrimUserType")
	public String getCountername() {
		return this.countername;
	}

	public void setCountername(String countername) {
		this.countername = countername;
	}


	public Integer getLastcounter() {
		return this.lastcounter;
	}

	public void setLastcounter(Integer lastcounter) {
		this.lastcounter = lastcounter;
	}

}