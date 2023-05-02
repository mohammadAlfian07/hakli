package com.sds.hakli.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import com.sds.utils.AppUtils;

public class NaskahVm {
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		
	}
	
	@Command
	public void doDownload(@BindingParam("arg") String arg) {
		try {
			String filepath = "";
			
			Sessions.getCurrent().setAttribute("reportPath", Executions.getCurrent().getDesktop().getWebApp()
					.getRealPath(AppUtils.PATH_JASPER + "/" + filepath));
			Executions.getCurrent().sendRedirect("/view/jasperviewer.zul", "_blank");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
