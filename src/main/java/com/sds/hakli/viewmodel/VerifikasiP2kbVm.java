package com.sds.hakli.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import com.sds.hakli.domain.Mprovinsi;
import com.sds.hakli.domain.Vp2kb;

public class VerifikasiP2kbVm {
	
	private List<Vp2kb> objList = new ArrayList<>();

	private String filter;

	private String nama;
	private Mprovinsi region;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Mprovinsi getRegion() {
		return region;
	}

	public void setRegion(Mprovinsi region) {
		this.region = region;
	}

}
