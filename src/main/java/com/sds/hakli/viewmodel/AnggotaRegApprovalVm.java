package com.sds.hakli.viewmodel;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;

import com.sds.hakli.dao.MchargeDAO;
import com.sds.hakli.dao.TanggotaDAO;
import com.sds.hakli.dao.TcounterengineDAO;
import com.sds.hakli.dao.TinvoiceDAO;
import com.sds.hakli.dao.TpekerjaanDAO;
import com.sds.hakli.dao.TpendidikanDAO;
import com.sds.hakli.domain.Mcharge;
import com.sds.hakli.domain.Muser;
import com.sds.hakli.domain.Tanggota;
import com.sds.hakli.domain.Tinvoice;
import com.sds.hakli.domain.Tpekerjaan;
import com.sds.hakli.domain.Tpendidikan;
import com.sds.hakli.extension.MailHandler;
import com.sds.utils.AppUtils;
import com.sds.utils.db.StoreHibernateUtil;

public class AnggotaRegApprovalVm {
	
	private org.zkoss.zk.ui.Session session = Sessions.getCurrent();
	private Muser oUser;
	
	private TanggotaDAO oDao = new TanggotaDAO();
	private TpendidikanDAO pendidikanDao = new TpendidikanDAO();
	private TpekerjaanDAO pekerjaanDao = new TpekerjaanDAO();
	
	private Tanggota pribadi;
	private List<Tpendidikan> pendidikans;
	private List<Tpekerjaan> pekerjaans;
	private String processinfo;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	@Wire
	private Grid gridPendidikan;
	@Wire
	private Grid gridPekerjaan;
	@Wire
	private Radiogroup rgDecision;
	@Wire
	private Textbox tbMemo;
	@Wire
	private Button btSave;
	@Wire
	private Div divProcessinfo;
	@Wire
	private Image photo;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("obj") Tanggota obj) {
		Selectors.wireComponents(view, this, false);
		
		oUser = (Muser) session.getAttribute("oUser");
		
		pribadi = obj;
		if (pribadi.getStatusreg().equals(AppUtils.STATUS_ANGGOTA_REG)) {
			btSave.setDisabled(false);
		} else {
			btSave.setDisabled(true);
			rgDecision.setDisabled(true);
			tbMemo.setDisabled(true);
			doCheckDecision(pribadi.getStatusreg());
		}
		
		if (pribadi.getPhotolink() != null) {
			File file = new File(Executions.getCurrent().getDesktop().getWebApp()
					.getRealPath(AppUtils.PATH_PHOTO) + "/" + pribadi.getPhotolink());
			if (file.exists()) {
				photo.setSrc(AppUtils.PATH_PHOTO + "/" + pribadi.getPhotolink());
				photo.setVisible(true);
			}
		}
		
		gridPendidikan.setRowRenderer(new RowRenderer<Tpendidikan>() {

			@Override
			public void render(Row row, Tpendidikan data, int index) throws Exception {
				row.getChildren().add(new Label(data.getMuniversitas() != null ? data.getMuniversitas().getUniversitas() : ""));
				row.getChildren().add(new Label(data.getMjenjang() != null ? data.getMjenjang().getJenjang() : ""));
				row.getChildren().add(new Label(data.getPeminatan1()));
				row.getChildren().add(new Label(data.getPeminatan2()));
				String periode = "";
				periode = data.getPeriodeblawal() + " " + data.getPeriodethawal() + " s/d " + data.getPeriodeblakhir() + " " + data.getPeriodethakhir();
				row.getChildren().add(new Label(periode));
				row.getChildren().add(new Label(data.getNoijazah()));
			}
		});
		
		gridPekerjaan.setRowRenderer(new RowRenderer<Tpekerjaan>() {

			@Override
			public void render(Row row, Tpekerjaan data, int index) throws Exception {
				row.getChildren().add(new Label(data.getNamakantor()));
				row.getChildren().add(new Label(data.getProvname()));
				row.getChildren().add(new Label(data.getKabname()));
				row.getChildren().add(new Label(data.getAlamatkantor()));
				row.getChildren().add(new Label(data.getMrumpun() != null ? data.getMrumpun().getRumpun() : ""));
				row.getChildren().add(new Label(data.getMkepegawaian() != null ? data.getMkepegawaian().getKepegawaian() : ""));
				row.getChildren().add(new Label(data.getJabatankantor()));
				row.getChildren().add(new Label(data.getNip()));
				row.getChildren().add(new Label(data.getTglmulai() != null ? dateFormatter.format(data.getTglmulai()) : ""));
				row.getChildren().add(new Label(data.getNoskkantor()));
				row.getChildren().add(new Label(data.getKeterangankerja()));
				row.getChildren().add(new Label(data.getTelpkantor()));
				row.getChildren().add(new Label(data.getFaxkantor()));
			}
		});
		
		refreshModel();
	}
	
	public void refreshModel() {
		try {
			pendidikans = pendidikanDao.listByFilter("tanggota.tanggotapk = " + pribadi.getTanggotapk(), "tpendidikanpk desc");
			pekerjaans = pekerjaanDao.listByFilter("tanggota.tanggotapk = " + pribadi.getTanggotapk(), "tpekerjaanpk desc");
			gridPendidikan.setModel(new ListModelList<>(pendidikans));
			gridPekerjaan.setModel(new ListModelList<>(pekerjaans));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	public void doCheckDecision(@BindingParam("status") String status) {
		if (status != null) {
			if (status.equals("9"))
				tbMemo.setVisible(true);
			else tbMemo.setVisible(false);
				
		}
	}
	
	@Command
	@NotifyChange("*")
	public void doSave() {
		Session session = StoreHibernateUtil.openSession();
		Transaction trx = null;
		try {
			String vano = "998100101000034";
			
			trx = session.beginTransaction();
			pribadi.setRegdecisiontime(new Date());
			pribadi.setVano(vano);
			oDao.save(session, pribadi);
			
			BigDecimal invamount = new BigDecimal(0);
			for (Mcharge charge: new MchargeDAO().listByFilter("chargetype = '" + AppUtils.CHARGETYPE_REG + "'", "isbase desc")) {
				invamount = invamount.add(charge.getChargeamount());
			}
			
			Tinvoice inv = new Tinvoice();
			inv.setTanggota(pribadi);
			inv.setCreatedby(oUser.getUserid());
			inv.setInvoiceamount(invamount);
			inv.setInvoicedate(new Date());
			inv.setInvoicedesc("Pendaftaran Anggota HAKLI");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, 14);
			inv.setInvoiceduedate(cal.getTime());
			inv.setInvoicetype("01");
			inv.setInvoiceno(new TcounterengineDAO().getInvoiceCounter());
			inv.setVano(vano);
			inv.setIspaid("N");
			new TinvoiceDAO().save(session, inv);
			
			trx.commit();
			
			if (pribadi.getStatusreg().equals(AppUtils.STATUS_ANGGOTA_REG_PAYMENT)) {
				String bodymail_path = Executions.getCurrent().getDesktop().getWebApp()
						.getRealPath("/themes/mail/mailinv.html");
				new Thread(new MailHandler(inv, bodymail_path)).start();
				
				processinfo = "Proses persetujuan pendaftaran anggota berhasil. Informasi permintaan pembayaran sudah dikirim ke e-mail anggota dengan Nomor VA " + vano;
				divProcessinfo.setVisible(true);
			} else if (pribadi.getStatusreg().equals(AppUtils.STATUS_ANGGOTA_REG_DECLINE)) {
				processinfo = "Proses penolakan pendaftaran anggota berhasil diproses.";
				divProcessinfo.setVisible(true);
			}
			
			btSave.setDisabled(true);
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(),
					Messagebox.OK, Messagebox.ERROR);
		} finally {
			session.close();
		}
	}
	
	public Validator getValidator() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String statusreg = (String) ctx.getProperties("statusreg")[0].getValue();
				if (statusreg == null || "".equals(statusreg.trim()))
					this.addInvalidMessage(ctx, "statusreg", Labels.getLabel("common.validator.empty"));
				else {
					if (!statusreg.equals(AppUtils.STATUS_ANGGOTA_REG_PAYMENT) && !statusreg.equals(AppUtils.STATUS_ANGGOTA_REG_DECLINE))
						this.addInvalidMessage(ctx, "statusreg", Labels.getLabel("common.validator.empty"));
					else if (statusreg.equals(AppUtils.STATUS_ANGGOTA_REG_DECLINE)) {
						String regmemo = (String) ctx.getProperties("regmemo")[0].getValue();
						if (regmemo == null || "".equals(regmemo.trim()))
							this.addInvalidMessage(ctx, "regmemo", Labels.getLabel("common.validator.empty"));
					}
				}
			}
		};
	}

	public Tanggota getPribadi() {
		return pribadi;
	}

	public void setPribadi(Tanggota pribadi) {
		this.pribadi = pribadi;
	}

	public String getProcessinfo() {
		return processinfo;
	}

	public void setProcessinfo(String processinfo) {
		this.processinfo = processinfo;
	}
	
}
