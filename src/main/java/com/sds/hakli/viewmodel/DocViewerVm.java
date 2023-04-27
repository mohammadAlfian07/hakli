package com.sds.hakli.viewmodel;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

public class DocViewerVm {
	
	private org.zkoss.zk.ui.Session zkSession = Sessions.getCurrent();
	private String src;
	private String title;
	private Map<String, String> map = new HashMap<>();

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		try {
			map = (Map<String, String>) zkSession.getAttribute("mapDocument");
			if (map != null) {
				src = map.get("src");
			}
			
			zkSession.removeAttribute("mapDocument");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
