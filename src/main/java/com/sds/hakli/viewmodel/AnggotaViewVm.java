package com.sds.hakli.viewmodel;

import java.io.File;
import java.text.SimpleDateFormat;
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

import com.sds.hakli.dao.TanggotaDAO;
import com.sds.hakli.dao.TpekerjaanDAO;
import com.sds.hakli.dao.TpendidikanDAO;
import com.sds.hakli.domain.Tanggota;
import com.sds.hakli.domain.Tpekerjaan;
import com.sds.hakli.domain.Tpendidikan;
import com.sds.hakli.extension.MailHandler;
import com.sds.utils.AppUtils;
import com.sds.utils.db.StoreHibernateUtil;

public class AnggotaViewVm {
	
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
	private Image photo;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("obj") Tanggota obj) {
		Selectors.wireComponents(view, this, false);
		
		pribadi = obj;
		
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
