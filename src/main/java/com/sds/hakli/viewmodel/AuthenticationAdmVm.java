package com.sds.hakli.viewmodel;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;

import com.sds.hakli.dao.MuserDAO;
import com.sds.hakli.domain.Muser;
import com.sds.utils.db.StoreHibernateUtil;

public class AuthenticationAdmVm {
	
	private org.zkoss.zk.ui.Session zkSession = Sessions.getCurrent();
	
	private MuserDAO userDao = new MuserDAO();
	
	private String userid;
	private String password;
	private String lblMessage;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
	}
	
	@Command
	@NotifyChange("lblMessage")
	public void doLogin() {
		if (userid != null && !userid.trim().equals("") && password != null && !password.trim().equals("")) {
			try {
				Muser oUser = userDao.findByFilter("upper(userid) = '" + userid.trim().toUpperCase() + "'");
				if (oUser != null) {
					if (oUser.getPassword().equals(password)) {
						
						Session session = StoreHibernateUtil.openSession();
						Transaction trx = session.beginTransaction();
						try {
							oUser.setLastlogin(new Date());
							userDao.save(session, oUser);
							trx.commit();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							session.close();
						}
						
						zkSession.setAttribute("oUser", oUser);
						Executions.sendRedirect("view/indexadm.zul");
					} else {
						lblMessage = "Authentication Failed : Invalid Password";
					}
				} else {
					lblMessage = "Authentication Failed : Invalid User Id";
				}
			} catch (Exception e) {
				lblMessage = "Authentication Failed : " + e.getMessage();
				e.printStackTrace();
			}
		}
	}
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(String lblMessage) {
		this.lblMessage = lblMessage;
	}
}
