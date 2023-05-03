package com.sds.hakli.viewmodel;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Messagebox;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.hakli.domain.Rumpun;
import com.sds.hakli.extension.BriApiExt;
import com.sds.hakli.pojo.BriApiToken;
import com.sds.hakli.pojo.BrivaStatus;
import com.sds.hakli.pojo.BrivaStatusResp;

public class VaStatusVm {
	
	private BrivaStatusResp obj;
	private String vano;
	
	@Wire
	private Groupbox gbResult;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		
	}
	
	@Command
	@NotifyChange("*")
	public void doSubmit() {
		if (vano != null && vano.trim().length() > 5) {
			try {
				BriApiExt briapi = new BriApiExt();
				BriApiToken briapiToken = briapi.getToken();
				if (briapiToken != null && briapiToken.getStatus().equals("approved")) {
					String cid = vano.substring(0, 5);
					String custcode = vano.substring(5);
					obj = briapi.getBriva(briapiToken.getAccess_token(), "j104408", cid, custcode);
					System.out.println("nama : " + obj.getData().getNama());
					if (obj.getStatus()) {
//						ObjectMapper mapper = new ObjectMapper();
//						BrivaStatus data = mapper.readValue(mapper.writeValueAsString(obj.getData()),
//								new TypeReference<BrivaStatus>() {
//								});
//						obj.setData(data);
						gbResult.setVisible(true);
						vano = null;
					} else 
						Messagebox.show(obj.getResponseDescription(), WebApps.getCurrent().getAppName(), Messagebox.OK,
							Messagebox.ERROR);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK,
						Messagebox.ERROR);
			}
		} else {
			Messagebox.show("Silahkan masukkan nomor virtual account yang ingin anda lakukan pengecekan", WebApps.getCurrent().getAppName(), Messagebox.OK,
					Messagebox.EXCLAMATION);
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void doReset() {
		gbResult.setVisible(false);
		vano = null;
	}

	public String getVano() {
		return vano;
	}

	public void setVano(String vano) {
		this.vano = vano;
	}

	public BrivaStatusResp getObj() {
		return obj;
	}

	public void setObj(BrivaStatusResp obj) {
		this.obj = obj;
	}
}
