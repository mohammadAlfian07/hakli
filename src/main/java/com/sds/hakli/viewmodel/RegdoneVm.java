package com.sds.hakli.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;

import com.sds.hakli.domain.AnggotaReg;

public class RegdoneVm {
	
	private String processinfo;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("obj") AnggotaReg obj) {
		Selectors.wireComponents(view, this, false);
		try {
			processinfo = obj.getPribadi().getNama() + ", Pendaftaran Anda telah diterima, "
					+ "tim pengurus cabang yang bersangkutan akan melakukan verifikasi data "
					+ "yang anda masukkan dan selanjutnya dikonfirmasi kembali melalui "
					+ "e-mail " + obj.getPribadi().getEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProcessinfo() {
		return processinfo;
	}

	public void setProcessinfo(String processinfo) {
		this.processinfo = processinfo;
	}
	
	

}
