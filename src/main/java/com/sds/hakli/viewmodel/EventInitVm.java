package com.sds.hakli.viewmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.sds.hakli.dao.TanggotaDAO;
import com.sds.hakli.dao.TeventregDAO;
import com.sds.hakli.domain.Tanggota;
import com.sds.hakli.domain.Teventreg;

public class EventInitVm {
	
	private TanggotaDAO oDao = new TanggotaDAO();
	private TeventregDAO eventregDao = new TeventregDAO();
	
	private String email;
	
	@Wire
	private Window winInit;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	public void doSubmit() {
		if (email == null || email.trim().length() == 0) {
			Messagebox.show("Silahkan masukkan alamat e-mail Anda", WebApps.getCurrent().getAppName(), Messagebox.OK,
					Messagebox.INFORMATION);
		} else {
			try {
				Map<String, Object> map = new HashMap<>();
				String page = "/view/event/eventreg.zul";
				Tanggota obj = null;
				List<Tanggota> objList = oDao.listByFilter("email = '" + email.trim() + "'", "tanggotapk desc");
				if (objList.size() > 0) {
					obj = objList.get(0);
					
					Teventreg eventreg = eventregDao.findByFilter("tanggota.tanggotapk = " + obj.getTanggotapk());
					if (eventreg != null) {
						map.put("obj", eventreg);
						page = "/view/event/eventregdone.zul";
					} else map.put("obj", obj);
				} else {
					obj = new Tanggota();
					obj.setEmail(email);
					map.put("obj", obj);
				}
				winInit.getChildren().clear();
				Executions.createComponents(page, winInit, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
