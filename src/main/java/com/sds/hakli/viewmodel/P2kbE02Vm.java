package com.sds.hakli.viewmodel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.sds.hakli.dao.TcounterengineDAO;
import com.sds.hakli.dao.Tp2kbE02DAO;
import com.sds.hakli.dao.Tp2kbDAO;
import com.sds.hakli.domain.Mp2kbkegiatan;
import com.sds.hakli.domain.Tanggota;
import com.sds.hakli.domain.Tp2kb;
import com.sds.hakli.domain.Tp2kbe02;
import com.sds.utils.AppUtils;
import com.sds.utils.db.StoreHibernateUtil;

public class P2kbE02Vm {
	
	private org.zkoss.zk.ui.Session zkSession = Sessions.getCurrent();
	private Tanggota anggota;
	private Tp2kbE02DAO oDao = new Tp2kbE02DAO();
	private Tp2kbDAO p2kbDao = new Tp2kbDAO();
	private TcounterengineDAO counterDao = new TcounterengineDAO();
	
	private Mp2kbkegiatan p2kb;
	private Tp2kbe02 objForm;
	private BigDecimal nilaiskp_curr;
	
	private Media media;
	private String docfilename;
	private boolean isInsert;
	
	@Wire
	private Window winP2kbe02;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, 
			@ExecutionArgParam("obj") Mp2kbkegiatan p2kb, @ExecutionArgParam("objForm") Tp2kbe02 objForm) {
		Selectors.wireComponents(view, this, false);
		anggota = (Tanggota) zkSession.getAttribute("anggota");
		this.p2kb = p2kb;
		if (objForm != null) {
			this.objForm = objForm;
			nilaiskp_curr = objForm.getNilaiskp();
			isInsert = false;
		} else doReset();
	}
	
	@NotifyChange("*")
	public void doReset() {
		isInsert = true;
		objForm = new Tp2kbe02();
		objForm.setTanggota(anggota);
		objForm.setMp2kbkegiatan(p2kb);
	}
	
	@Command
	@NotifyChange("docfilename")
	public void doUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		try {
			UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
			media = event.getMedia();
			docfilename = media.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void doSave() {
		Session session = StoreHibernateUtil.openSession();
		Transaction trx = session.beginTransaction();
		try {
			objForm.setNilaiskp(new BigDecimal(3));
			objForm.setCreatedby(anggota.getNoanggota());
			objForm.setCreatetime(new Date());
			objForm.setStatus("WC");
			
			if (media != null) {
				try {
					String docid = counterDao.getP2kbCounter(objForm.getMp2kbkegiatan().getIdkegiatan());
					String folder = Executions.getCurrent().getDesktop().getWebApp()
							.getRealPath(AppUtils.PATH_P2KB);
					if (media.isBinary()) {
						Files.copy(new File(folder + "/" + docid + "/" + media.getFormat()), media.getStreamData());
					} else {
						BufferedWriter writer = new BufferedWriter(
								new FileWriter(folder + "/" + docid + "/" + media.getFormat()));
						Files.copy(writer, media.getReaderData());
						writer.close();
					}
					objForm.setDocid(docid);
					objForm.setDocpath(AppUtils.PATH_P2KB + "/" + docid + "/" + media.getFormat());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			oDao.save(session, objForm);
			
			Tp2kb book = p2kbDao.findByFilter("tanggota.tanggotapk = " + anggota.getTanggotapk() + " and mp2kbkegiatan.mp2kbkegiatanpk = " + objForm.getMp2kbkegiatan().getMp2kbkegiatanpk());
			if (book == null) {
				book = new Tp2kb();
				book.setTanggota(anggota);
				book.setMp2kbkegiatan(objForm.getMp2kbkegiatan());
				book.setTotalkegiatan(0);
				book.setTotalskp(new BigDecimal(0));
			}
			if (isInsert) {
				book.setTotalkegiatan(book.getTotalkegiatan()+1);
				book.setTotalskp(book.getTotalskp().add(objForm.getNilaiskp()));
			} else {
				book.setTotalskp(book.getTotalskp().subtract(nilaiskp_curr));
				book.setTotalskp(book.getTotalskp().add(objForm.getNilaiskp()));
			}
			book.setLastupdated(new Date());
			p2kbDao.save(session, book);
			
			trx.commit();
			
			if (isInsert) {
				Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 2000);
			} else {
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 2000);
			}
			
			doClose();
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(),
					Messagebox.OK, Messagebox.ERROR);
		} finally {
			session.close();
		}
	}
	
	@Command()
	@NotifyChange("*")
	public void doClose() {
		Map<String, Object> map = new HashMap<>();
		map.put("kegiatan", objForm.getMp2kbkegiatan().getKegiatan());
		Event closeEvent = new Event("onClose", winP2kbe02, map);
		Events.postEvent(closeEvent);
	}
	
	public Validator getValidator() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String judul = (String) ctx.getProperties("judul")[0].getValue();
				if (judul == null || "".equals(judul.trim()))
					this.addInvalidMessage(ctx, "judul", Labels.getLabel("common.validator.empty"));
				Date tglkegiatan = (Date) ctx.getProperties("tglkegiatan")[0].getValue();
				if (tglkegiatan == null)
					this.addInvalidMessage(ctx, "tglkegiatan", Labels.getLabel("common.validator.empty"));
				if (isInsert && media == null)
					this.addInvalidMessage(ctx, "media", "Silahkan upload dokumen bukti kegiatan");
			}
		};
	}

	public Tp2kbe02 getObjForm() {
		return objForm;
	}

	public void setObjForm(Tp2kbe02 objForm) {
		this.objForm = objForm;
	}

	public String getDocfilename() {
		return docfilename;
	}

	public void setDocfilename(String docfilename) {
		this.docfilename = docfilename;
	}

}
