package com.sds.hakli.viewmodel;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.event.PagingEvent;

import com.sds.hakli.dao.MchargeDAO;
import com.sds.hakli.dao.TcounterengineDAO;
import com.sds.hakli.dao.TinvoiceDAO;
import com.sds.hakli.domain.Mcharge;
import com.sds.hakli.domain.Tanggota;
import com.sds.hakli.domain.Tinvoice;
import com.sds.hakli.extension.MailHandler;
import com.sds.hakli.model.TanggotaListModel;
import com.sds.hakli.model.TinvoiceListModel;
import com.sds.utils.AppUtils;
import com.sds.utils.db.StoreHibernateUtil;

public class PaymentVm {

	private org.zkoss.zk.ui.Session zkSession = Sessions.getCurrent();
	private Tanggota anggota;
	private MchargeDAO chargeDao = new MchargeDAO();
	private TinvoiceDAO invDao = new TinvoiceDAO();
	private List<Mcharge> objList;
	private List<Mcharge> oList;
	private Integer qty;
	private BigDecimal totalpayment = new BigDecimal(0);
	private BigDecimal amountbase;
	private String processinfo;
	
	private TinvoiceListModel model;
	
	private int pageStartNumber;
	private int pageTotalSize;
	private String filter;
	
	private SimpleDateFormat datelocalFormatter = new SimpleDateFormat("dd-MM-yyyy");

	@Wire
	private Groupbox gbForm;
	@Wire
	private Grid gridCharge;
	@Wire
	private Combobox cbCharge;
	@Wire
	private Button btSave;
	@Wire
	private Div divProcessinfo;
	@Wire
	private Grid gridHist;
	@Wire
	private Paging paging;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		try {
			anggota = (Tanggota) zkSession.getAttribute("anggota");
			gridCharge.setRowRenderer(new RowRenderer<Mcharge>() {

				@Override
				public void render(Row row, Mcharge data, int index) throws Exception {
					row.getChildren().add(new Label(data.getChargedesc()));
					row.getChildren().add(new Label(NumberFormat.getInstance().format(data.getChargeamount())));

					totalpayment = totalpayment.add(data.getChargeamount());
					BindUtils.postNotifyChange(PaymentVm.this, "totalpayment");
				}
			});

			objList = chargeDao.listByFilter("chargetype = '" + AppUtils.CHARGETYPE_IURAN + "'", "isbase desc");
			oList = objList;
			amountbase = new BigDecimal(0);
			for (Mcharge obj : objList) {
				if (obj.getIsbase().equals("Y")) {
					amountbase = obj.getChargeamount();
					break;
				}
			}

			for (int i = 1; i <= 12; i++) {
				Comboitem item = new Comboitem();
				item.setValue(i);
				item.setLabel(i + " Bulan / Rp. "
						+ (NumberFormat.getInstance().format(amountbase.multiply(new BigDecimal(i)))));
				cbCharge.appendChild(item);

				if (i == 1)
					cbCharge.setSelectedItem(item);
			}
			qty = 1;

			gridCharge.setModel(new ListModelList<>(objList));
			
			
			if (paging != null) {
				paging.addEventListener("onPaging", new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						PagingEvent pe = (PagingEvent) event;
						pageStartNumber = pe.getActivePage();
						refreshModel(pageStartNumber);
					}
				});
			}
			gridHist.setRowRenderer(new RowRenderer<Tinvoice>() {

				@Override
				public void render(Row row, Tinvoice data, int index) throws Exception {
					row.getChildren().add(new Label(String.valueOf((AppUtils.PAGESIZE * pageStartNumber) + index + 1)));
					row.getChildren().add(new Label(data.getInvoiceno()));
					row.getChildren().add(new Label(datelocalFormatter.format(data.getInvoicedate())));
					row.getChildren().add(new Label(NumberFormat.getInstance().format(data.getInvoiceamount())));
					row.getChildren().add(new Label(datelocalFormatter.format(data.getInvoiceduedate())));
					row.getChildren().add(new Label(data.getVano()));
					row.getChildren().add(new Label(data.getInvoicedesc()));
					row.getChildren().add(new Label(data.getIspaid().equals("Y") ? "Sudah Dibayar" : "Belum dibayar"));
				}
			});
			
			refreshModel(pageStartNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@NotifyChange("pageTotalSize")
	public void refreshModel(int activePage) {
		paging.setPageSize(AppUtils.PAGESIZE);
		model = new TinvoiceListModel(activePage, AppUtils.PAGESIZE, "tanggotafk = " + anggota.getTanggotapk(), "tinvoicepk desc");
		pageTotalSize = model.getTotalSize(filter);
		paging.setTotalSize(pageTotalSize);
		gridHist.setModel(model);
	}

	@Command
	@NotifyChange("totalpayment")
	public void doCal(@BindingParam("qty") Integer qty) {
		if (qty != null) {
			oList = new ArrayList<>();
			for (Mcharge obj : objList) {
				if (obj.getIsbase().equals("Y")) {
					Mcharge charge = obj;
					charge.setChargeamount(obj.getChargeamount().multiply(new BigDecimal(qty)));

					oList.add(charge);
				} else {
					oList.add(obj);
				}
			}
			totalpayment = new BigDecimal(0);
			gridCharge.setModel(new ListModelList<>(oList));
		}
	}

	@Command
	@NotifyChange("*")
	public void doSave() {
		Messagebox.show(
				"Apakah data pembayaran yang anda input sudah benar? Jika sudah benar silahkan pilih OK untuk mengirim data tagihan ke email anda",
				"Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onOK")) {
							Session session = StoreHibernateUtil.openSession();
							Transaction trx = null;
							try {
								String vano = "998100102000035";
								trx = session.beginTransaction();
								Tinvoice inv = new Tinvoice();
								inv.setTanggota(anggota);
								inv.setCreatedby(anggota.getCreatedby());
								inv.setCreatetime(new Date());
								inv.setInvoiceamount(totalpayment);
								inv.setInvoicedate(new Date());
								inv.setInvoicedesc("Pembayaran Iuran " + qty + " bulan");
								Calendar cal = Calendar.getInstance();
								cal.setTime(new Date());
								cal.add(Calendar.DAY_OF_MONTH, 14);
								inv.setInvoiceduedate(cal.getTime());
								inv.setInvoicetype("02");
								inv.setInvoiceno(new TcounterengineDAO().getInvoiceCounter());
								inv.setVano(vano);
								inv.setIspaid("N");
								invDao.save(session, inv);
								trx.commit();

								btSave.setDisabled(true);
								
								String bodymail_path = Executions.getCurrent().getDesktop().getWebApp()
										.getRealPath("/themes/mail/mailinv.html");
								new Thread(new MailHandler(inv, bodymail_path)).start();

								processinfo = "Proses generate pembayaran berhasil. Informasi permintaan pembayaran sudah dikirim ke e-mail anggota dengan Nomor VA "
										+ vano;
								divProcessinfo.setVisible(true);
								
								pageStartNumber = 0;
								refreshModel(pageStartNumber);
								
								BindUtils.postNotifyChange(PaymentVm.this, "*");
								
								gbForm.setOpen(false);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								session.close();
							}
						}
					}
				});

	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(BigDecimal totalpayment) {
		this.totalpayment = totalpayment;
	}

	public String getProcessinfo() {
		return processinfo;
	}

	public void setProcessinfo(String processinfo) {
		this.processinfo = processinfo;
	}

	public Tanggota getAnggota() {
		return anggota;
	}

	public void setAnggota(Tanggota anggota) {
		this.anggota = anggota;
	}

	public int getPageTotalSize() {
		return pageTotalSize;
	}

	public void setPageTotalSize(int pageTotalSize) {
		this.pageTotalSize = pageTotalSize;
	}
	
	
}
