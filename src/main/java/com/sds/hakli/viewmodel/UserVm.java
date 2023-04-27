package com.sds.hakli.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sds.hakli.dao.MuserDAO;
import com.sds.hakli.domain.Muser;
import com.sds.utils.db.StoreHibernateUtil;

public class UserVm {
	
	private Session session = Sessions.getCurrent();
	private Muser oUser;
	
	private MuserDAO oDao = new MuserDAO();
	
	private Muser objForm;
	private List<Muser> objList;
	private String nama;
	private Integer totalrecords;
	private boolean isInsert;
	
	private SimpleDateFormat datelocalFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	@Wire
	private Window winUser;
	@Wire
	private Grid grid;
	@Wire
	private Button btAdd;
	@Wire
	private Button btSave;
	@Wire
	private Div divForm;
	@Wire
	private Textbox tbUserid;
	@Wire
	private Textbox tbPassword;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		oUser = (Muser) session.getAttribute("oUser");
		
		grid.setRowRenderer(new RowRenderer<Muser>() {

			@Override
			public void render(Row row, Muser data, int index) throws Exception {
				row.getChildren().add(new Label(String.valueOf(index+1)));
				
				row.getChildren().add(new Label(data.getUserid()));
				row.getChildren().add(new Label(data.getUsername()));
				row.getChildren().add(new Label(data.getLastlogin() != null ? datelocalFormatter.format(data.getLastlogin()) : ""));
				Button btEdit = new Button();
				btEdit.setIconSclass("z-icon-edit");
				btEdit.setSclass("btn btn-primary btn-sm");
				btEdit.setAutodisable("self");
				btEdit.setTooltiptext("Edit");
				btEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						doAdd(data);
						BindUtils.postNotifyChange(UserVm.this, "objForm");
					}
				});
				row.getChildren().add(btEdit);
				
				Button btDel = new Button();
				btDel.setIconSclass("z-icon-trash-o");
				btDel.setSclass("btn btn-danger btn-sm");
				btDel.setAutodisable("self");
				btDel.setTooltiptext("Hapus");
				
				btDel.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						Messagebox.show("Anda ingin menghapus data ini?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {

							@Override
							public void onEvent(Event event)
									throws Exception {
								if (event.getName().equals("onOK")) {
									org.hibernate.Session session = StoreHibernateUtil.openSession();
									Transaction trx = session.beginTransaction();
									try {
										oDao.delete(session, data);
										trx.commit();
										Clients.showNotification("Proses hapus data berhasil", "info", null, "middle_center", 1500);
										doReset();
										BindUtils.postNotifyChange(UserVm.this, "*");
									} catch (Exception e) {	
										Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
										e.printStackTrace();
									} finally {
										session.close();
									}
								} 									
							}
							
						});
					}
				});
				
				Div div = new Div();
				div.appendChild(btEdit);
				div.appendChild(new Separator("vertical"));
				div.appendChild(btDel);
				row.getChildren().add(div);
			}
		});
		
		doReset();
	}
	
	@Command
	public void doReset() {
		nama = null;
		doRefresh();
		divForm.setVisible(false);
		btAdd.setLabel("Tambah User");
		btAdd.setIconSclass("z-icon-plus-square");
	}
	
	@Command
	@NotifyChange("totalrecords")
	public void doRefresh() {
		try {
			objList = oDao.listByFilter("0=0", "userid");
			totalrecords = objList.size();
			grid.setModel(new ListModelList<>(objList) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void doAdd(Muser obj) {
		if (obj != null) {
			isInsert = false;
			objForm = obj;
			divForm.setVisible(true);
			btAdd.setLabel("Cancel");
			btAdd.setIconSclass("z-icon-reply");
			btSave.setLabel("Perbarui");
			
			tbUserid.setDisabled(true);
			tbPassword.setDisabled(true);
		} else if (btAdd.getLabel().equals("Tambah User")) {
			isInsert = true;
			objForm = new Muser();
			divForm.setVisible(true);
			btAdd.setLabel("Cancel");
			btAdd.setIconSclass("z-icon-reply");
			btSave.setLabel("Submit");
			
			tbUserid.setDisabled(false);
			tbPassword.setDisabled(false);
		} else {
			divForm.setVisible(false);
			btAdd.setLabel("Tambah User");
			btAdd.setIconSclass("z-icon-plus-square");
		}
	}
	
	@Command
	@NotifyChange("objForm")
	public void doSave() {
		org.hibernate.Session session = StoreHibernateUtil.openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			objForm.setLastupdated(new Date());
			objForm.setUpdatedby(oUser.getUserid());
			oDao.save(session, objForm);
			trx.commit();
			if (isInsert) {
				Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 1500);
			} else { 
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
			} 
			doReset();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Validator getValidator() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String userid = (String) ctx.getProperties("userid")[0].getValue();
				String username = (String) ctx.getProperties("username")[0].getValue();
				String password = (String) ctx.getProperties("password")[0].getValue();
				
				if (userid == null || "".equals(userid.trim()))
					this.addInvalidMessage(ctx, "userid", Labels.getLabel("common.validator.empty"));
				if (username == null || "".equals(username.trim()))
					this.addInvalidMessage(ctx, "username", Labels.getLabel("common.validator.empty"));
				if (isInsert && (password == null || "".equals(password.trim())))
					this.addInvalidMessage(ctx, "password", Labels.getLabel("common.validator.empty"));
			}
		};
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
	}

	public Muser getObjForm() {
		return objForm;
	}

	public void setObjForm(Muser objForm) {
		this.objForm = objForm;
	}
	
}
