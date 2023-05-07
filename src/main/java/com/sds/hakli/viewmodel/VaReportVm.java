package com.sds.hakli.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.sds.hakli.bean.BriapiBean;
import com.sds.hakli.extension.BriApiExt;
import com.sds.hakli.pojo.BriApiToken;
import com.sds.hakli.pojo.BrivaReport;
import com.sds.hakli.pojo.BrivaReportResp;
import com.sds.utils.AppData;

public class VaReportVm {
	
	private BrivaReportResp obj;
	private Date startperiod;
	private Date endperiod;
	private int pageTotalSize;
	
	private BriapiBean bean;
	
	@Wire
	private Groupbox gbResult;
	@Wire
	private Grid grid;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		try {
			bean = AppData.getBriapibean();
			
			grid.setRowRenderer(new RowRenderer<BrivaReport>() {

				@Override
				public void render(Row row, BrivaReport data, int index) throws Exception {
					row.getChildren().add(new Label(String.valueOf(index + 1)));
					row.getChildren().add(new Label(data.getBrivaNo() + data.getCustCode()));
					row.getChildren().add(new Label(data.getNama()));
					row.getChildren().add(new Label(data.getAmount()));
					row.getChildren().add(new Label(data.getKeterangan()));
					row.getChildren().add(new Label(data.getPaymentDate()));
					row.getChildren().add(new Label(data.getTellerid()));
					row.getChildren().add(new Label(data.getNo_rek()));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Command
	@NotifyChange("*")
	public void doSubmit() {
		if (startperiod != null && endperiod != null) {
			try {
				BriApiExt briapi = new BriApiExt(bean);
				BriApiToken briapiToken = briapi.getToken();
				if (briapiToken != null && briapiToken.getStatus().equals("approved")) {
					String startdate = new SimpleDateFormat("yyyyMMdd").format(startperiod);
					String enddate = new SimpleDateFormat("yyyyMMdd").format(endperiod);
					obj = briapi.getBrivaReport(briapiToken.getAccess_token(), startdate, enddate);
					if (obj != null && obj.getStatus() != null && obj.getStatus()) {
						gbResult.setVisible(true);	
						grid.setModel(new ListModelList<>(obj.getData()));
						pageTotalSize = obj.getData().size();
					} else 
						Messagebox.show("Laporan tidak tersedia", WebApps.getCurrent().getAppName(), Messagebox.OK,
							Messagebox.INFORMATION);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK,
						Messagebox.ERROR);
			}
		} else {
			Messagebox.show("Silahkan masukkan periode tanggal laporan", WebApps.getCurrent().getAppName(), Messagebox.OK,
					Messagebox.EXCLAMATION);
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void doReset() {
		gbResult.setVisible(false);
		startperiod = null;
		endperiod = null;
		pageTotalSize = 0;
	}

	public Date getStartperiod() {
		return startperiod;
	}

	public void setStartperiod(Date startperiod) {
		this.startperiod = startperiod;
	}

	public Date getEndperiod() {
		return endperiod;
	}

	public void setEndperiod(Date endperiod) {
		this.endperiod = endperiod;
	}

	public BrivaReportResp getObj() {
		return obj;
	}

	public void setObj(BrivaReportResp obj) {
		this.obj = obj;
	}

	public int getPageTotalSize() {
		return pageTotalSize;
	}

	public void setPageTotalSize(int pageTotalSize) {
		this.pageTotalSize = pageTotalSize;
	}
}
