package com.sds.hakli.viewmodel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
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
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sds.hakli.domain.Akreditasi;
import com.sds.hakli.domain.Branch;
import com.sds.hakli.domain.Darurat;
import com.sds.hakli.domain.DataAnggota;
import com.sds.hakli.domain.DocUpload;
import com.sds.hakli.domain.GenericResp;
import com.sds.hakli.domain.Jenisdiklat;
import com.sds.hakli.domain.Jenjang;
import com.sds.hakli.domain.Kabupaten;
import com.sds.hakli.domain.Kepegawaian;
import com.sds.hakli.domain.Kepegawaiansdmk;
import com.sds.hakli.domain.Nation;
import com.sds.hakli.domain.Pekerjaan;
import com.sds.hakli.domain.Pelatihan;
import com.sds.hakli.domain.Pendidikan;
import com.sds.hakli.domain.Pribadi;
import com.sds.hakli.domain.Prodi;
import com.sds.hakli.domain.Profesi;
import com.sds.hakli.domain.Provinsi;
import com.sds.hakli.domain.RegAnggota;
import com.sds.hakli.domain.Region;
import com.sds.hakli.domain.Rumpun;
import com.sds.hakli.domain.Rumpundiklat;
import com.sds.hakli.domain.Sertifikasi;
import com.sds.hakli.domain.University;
import com.sds.hakli.domain.User;
import com.sds.hakli.extension.ExtensionServices;
import com.sds.utils.AppUtils;
import com.sds.utils.StringUtils;
import com.sds.utils.config.ConfigUtil;

public class AnggotaFormVm_ {

	private Session session = Sessions.getCurrent();
	private User oUser;
	
	private String isreg;
	private RegAnggota objForm;
	private DataAnggota dataForm;
	private Pekerjaan objPekerjaan;
	private Pendidikan objPendidikan;
	private Pelatihan objPelatihan;
	private Sertifikasi objSertifikasi;
	
	private Region region;
	private Branch branch;
	private Nation nation;
	private Provinsi provinsi;
	private Kabupaten kabupaten;
	private Provinsi provpekerjaan;
	private Kabupaten kabpekerjaan;
	private Rumpun rumpun;
	private Kepegawaian kepegawaian;
	private Kepegawaiansdmk kepegawaiansdmk;
	private University university;
	private Jenjang jenjang;
	private Prodi prodi;
	private Profesi profesi;
	private Rumpundiklat rumpundiklat;
	private Jenisdiklat jenisdiklat;
	private Akreditasi akreditasi;

	private ListModelList<Region> regionModel;
	private ListModelList<Branch> branchModel;
	private ListModelList<Nation> nationModel;
	private ListModelList<Provinsi> provinsiModel;
	private ListModelList<Kabupaten> kabupatenModel;
	private ListModelList<Provinsi> provPekerjaanModel;
	private ListModelList<Kabupaten> kabPekerjaanModel;
	private ListModelList<Rumpun> rumpunModel;
	private ListModelList<Kepegawaian> kepegawaianModel;
	private ListModelList<Kepegawaiansdmk> kepegawaiansdmkModel;
	private ListModelList<University> universityModel;
	private ListModelList<Jenjang> jenjangModel;
	private ListModelList<Prodi> prodiModel;
	private ListModelList<Profesi> profesiModel;
	private ListModelList<Rumpundiklat> rumpundiklatModel;
	private ListModelList<Jenisdiklat> jenisdiklatModel;
	private ListModelList<Akreditasi> akreditasiModel;

	public Boolean isInsert;
	public boolean isInsertPekerjaan;
	public boolean isInsertPendidikan;
	public boolean isInsertPelatihan;
	public boolean isInsertSertifikasi;
	public boolean isInsertKontak;
	private byte[] photobyte;
	private Media media;
	private Date dob;
	private Boolean isView;
	private Boolean isReg;

	private ObjectMapper mapper = new ObjectMapper();

	private DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");

	@Wire
	private Window winAnggotaForm;
	@Wire
	private Combobox cbRegion;
	@Wire
	private Combobox cbBranch;
	@Wire
	private Combobox cbProvinsi;
	@Wire
	private Combobox cbKabupaten;
	@Wire
	private Combobox cbProvPekerjaan;
	@Wire
	private Combobox cbKabPekerjaan;
	@Wire
	private Combobox cbStatusanggota;
	@Wire
	private Combobox cbRumpun;
	@Wire
	private Combobox cbKepegawaian;
	@Wire
	private Combobox cbKepegawaiansdmk;
	@Wire
	private Combobox cbUniversity;
	@Wire
	private Combobox cbJenjang;
	@Wire
	private Combobox cbProdi;
	@Wire
	private Combobox cbRumpundiklat;
	@Wire
	private Combobox cbJenisdiklat;
	@Wire
	private Combobox cbAkreditasi;
	@Wire
	private Combobox cbProfesi;
	@Wire
	private Image photo;
	@Wire
	private Button btDeletePhoto;
	@Wire
	private Combobox cbDobDay;
	@Wire
	private Combobox cbDobMonth;
	@Wire
	private Combobox cbDobYear;
	@Wire
	private Combobox cbNation;
	@Wire
	private Combobox cbPeriodStartMonth;
	@Wire
	private Combobox cbPeriodStartYear;
	@Wire
	private Combobox cbPeriodEndMonth;
	@Wire
	private Combobox cbPeriodEndYear;
	@Wire
	private Grid gridPekerjaan;
	@Wire
	private Grid gridPendidikan;
	@Wire
	private Grid gridPelatihan;
	@Wire
	private Grid gridSertifikasi;
	@Wire
	private Button btAddPekerjaan;
	@Wire
	private Groupbox gbPekerjaan;
	@Wire
	private Button btAddPendidikan;
	@Wire
	private Groupbox gbPendidikan;
	@Wire
	private Button btAddPelatihan;
	@Wire
	private Groupbox gbPelatihan;
	@Wire
	private Button btAddSertifikasi;
	@Wire
	private Groupbox gbSertifikasi;
	@Wire
	private Button btAddKontak;
	@Wire
	private Groupbox gbKontak;
	@Wire
	private Button btSavePekerjaan;
	@Wire
	private Button btSavePendidikan;
	@Wire
	private Button btSavePelatihan;
	@Wire
	private Button btSaveSertifikasi;
	@Wire
	private Button btBack;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("obj") DataAnggota obj,
			@ExecutionArgParam("isInsert") Boolean isInsert, @ExecutionArgParam("isView") Boolean isView) {
		Selectors.wireComponents(view, this, false);
		try {
			oUser = (User) session.getAttribute("oUser");
			
			this.isInsert = isInsert;
			this.isView = isView;
			
			isreg = Executions.getCurrent().getParameter("isreg");
			if (isreg != null && isreg.equals("1"))
				setupIsReg();
			
			if (this.isInsert == null && this.isView == null) {
				if (obj == null) {
					String url = ConfigUtil.getConfig().getUrl_base()
							+ ConfigUtil.getConfig().getEndpoint_anggota() + "/" + oUser.getId();
					GenericResp respAll = ExtensionServices.getResource(url, null,
							"get", oUser.getToken());

					if (respAll.getCode() == 200) {
						List<DataAnggota> objList = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
								new TypeReference<List<DataAnggota>>() {
								});
						if (objList.size() > 0) {
							obj = objList.get(0);
							BindUtils.postNotifyChange(AnggotaFormVm.this, "obj");
						}
							
					}
				}
			}
			
			if (isView == null || !isView)
				init();
			doReset();
			if (obj != null) {
				isInsert = false;
				dataForm = obj;
				
				if (dataForm.getPribadi().getPhotolink() != null) {
					photo.setSrc(dataForm.getPribadi().getPhotolink());
					photo.setVisible(true);
				}

				for (Component comp : cbStatusanggota.getChildren()) {
					Comboitem item = (Comboitem) comp;
					if (item.getValue().toString().equals(dataForm.getPribadi().getStatusanggota())) {
						cbStatusanggota.setSelectedItem(item);
						break;
					}
				}

				String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_provinsi() + "/"
						+ dataForm.getPribadi().getProvcode();
				GenericResp respAll = ExtensionServices.getResource(url, null, "get", null);
				if (respAll.getCode() == 200) {
					provinsi = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<Provinsi>() {
							});
					if (cbProvinsi != null)
						cbProvinsi.setValue(provinsi.getProvname());
					if (cbKabupaten != null)
						doLoadKab(provinsi);
				}

				url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_kabupaten() + "/"
						+ dataForm.getPribadi().getProvcode() + "/" + dataForm.getPribadi().getKabcode();
				respAll = ExtensionServices.getResource(url, null, "get", null);
				if (respAll.getCode() == 200) {
					kabupaten = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<Kabupaten>() {
							});
					if (cbKabupaten != null)
						cbKabupaten.setValue(kabupaten.getKabname());
				}

				url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_nation() + "/"
						+ dataForm.getPribadi().getNationid();
				respAll = ExtensionServices.getResource(url, null, "get", null);
				if (respAll.getCode() == 200) {
					nation = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<Nation>() {
							});
					if (cbNation != null)
						cbNation.setValue(nation.getNation());
				}

//				if (objForm.getBranch() != null)
//					region = objForm.getBranch().getRegion();

				if (isView == null || !isView) {
					if (dataForm.getPribadi().getTgllahir() != null) {
						dob = dataForm.getPribadi().getTgllahir();
						cbDobDay.setValue(new SimpleDateFormat("dd").format(dob));
						cbDobMonth.setSelectedIndex(Integer.parseInt(new SimpleDateFormat("MM").format(dob)) - 1);
						cbDobYear.setValue(new SimpleDateFormat("yyyy").format(dob));
					}

					url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_branch() + "/"
							+ dataForm.getPribadi().getBranchid();
					respAll = ExtensionServices.getResource(url, null, "get", null);
					if (respAll.getCode() == 200) {
						branch = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
								new TypeReference<Branch>() {
								});
						cbBranch.setValue(branch.getBranchname());
					}

					// cbRegion.setValue(objForm.getBranch().getRegion().getRegionname());

				}

				if (objForm.getPribadi().getPhotolink() != null) {
					photo.setSrc(objForm.getPribadi().getPhotolink());
					photo.setVisible(true);
				}

				gridPekerjaan.setRowRenderer(new RowRenderer<Pekerjaan>() {

					@Override
					public void render(Row row, Pekerjaan data, int index) throws Exception {
						row.getChildren().add(new Label(data.getRumpun()));
						row.getChildren().add(new Label(data.getKepegawaian()));
						row.getChildren().add(new Label(data.getKepegawaiansdmk()));
						row.getChildren()
								.add(new Label(data.getTglmulai() != null
										? new SimpleDateFormat("dd-MM-yyyy").format(data.getTglmulai())
										: ""));
						row.getChildren().add(new Label(data.getNip()));

						Button btEdit = new Button();
						btEdit.setIconSclass("z-icon-edit");
						btEdit.setSclass("btn btn-primary btn-sm");
						btEdit.setAutodisable("self");
						btEdit.setTooltiptext("Edit");
						btEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								doAddPekerjaan(data);
								BindUtils.postNotifyChange(AnggotaFormVm.this, "objPekerjaan");
							}
						});

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
											try {
												String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pekerjaan() + "/" + data.getPekerjaanid();
												GenericResp respAll = ExtensionServices.getResource(url, null, "delete", oUser.getToken());
												if (respAll.getCode() == 200) {
													Clients.showNotification("Proses hapus data berhasil", "info", null, "middle_center", 1500);
													dataForm.getDaftarpekerjaan().remove(data);
													gridPekerjaan.setModel(new ListModelList<>(dataForm.getDaftarpekerjaan()));
												} else {
													Messagebox.show(respAll.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												}
											} catch (Exception e) {	
												Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												e.printStackTrace();
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
				gridPekerjaan.setModel(new ListModelList<>(dataForm.getDaftarpekerjaan()));

				gridPendidikan.setRowRenderer(new RowRenderer<Pendidikan>() {

					@Override
					public void render(Row row, Pendidikan data, int index) throws Exception {
						row.getChildren().add(new Label(data.getUniversityname()));
						row.getChildren().add(new Label(data.getJenjang()));
						row.getChildren().add(new Label(data.getProdi()));
						row.getChildren().add(new Label(data.getNoijazah()));
						row.getChildren().add(new Label(data.getTahunlulus()));
						Button btEdit = new Button();
						btEdit.setIconSclass("z-icon-edit");
						btEdit.setSclass("btn btn-primary btn-sm");
						btEdit.setAutodisable("self");
						btEdit.setTooltiptext("Edit");
						btEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								doAddPendidikan(data);
								BindUtils.postNotifyChange(AnggotaFormVm.this, "objPendidikan");
							}
						});

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
											try {
												String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pendidikan() + "/" + data.getPendidikanid();
												GenericResp respAll = ExtensionServices.getResource(url, null, "delete", oUser.getToken());
												if (respAll.getCode() == 200) {
													Clients.showNotification("Proses hapus data berhasil", "info", null, "middle_center", 1500);
													dataForm.getDaftarpendidikan().remove(data);
													gridPendidikan.setModel(new ListModelList<>(dataForm.getDaftarpendidikan()));
												} else {
													Messagebox.show(respAll.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												}
											} catch (Exception e) {	
												Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												e.printStackTrace();
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
				gridPendidikan.setModel(new ListModelList<>(dataForm.getDaftarpendidikan()));

				gridPelatihan.setRowRenderer(new RowRenderer<Pelatihan>() {

					@Override
					public void render(Row row, Pelatihan data, int index) throws Exception {
						row.getChildren().add(new Label(data.getRumpundiklat()));
						row.getChildren().add(new Label(data.getJenisdiklat()));
						row.getChildren().add(new Label(data.getNamadiklat()));
						row.getChildren().add(new Label(data.getAkreditasi()));
						row.getChildren().add(new Label(data.getTempatpelaksanaan()));
						row.getChildren()
								.add(new Label(data.getTglmulai() != null
										? new SimpleDateFormat("dd-MM-yyyy").format(data.getTglmulai())
										: ""));
						row.getChildren()
								.add(new Label(data.getTglakhir() != null
										? new SimpleDateFormat("dd-MM-yyyy").format(data.getTglakhir())
										: ""));
						row.getChildren().add(new Label(String.valueOf(data.getLamahari())));
						row.getChildren().add(new Label(String.valueOf(data.getLamajam())));
						
						Button btEdit = new Button();
						btEdit.setIconSclass("z-icon-edit");
						btEdit.setSclass("btn btn-primary btn-sm");
						btEdit.setAutodisable("self");
						btEdit.setTooltiptext("Edit");
						btEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								doAddPelatihan(data);
								BindUtils.postNotifyChange(AnggotaFormVm.this, "objPelatihan");
							}
						});

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
											try {
												String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pelatihan() + "/" + data.getPelatihanid();
												GenericResp respAll = ExtensionServices.getResource(url, null, "delete", oUser.getToken());
												if (respAll.getCode() == 200) {
													Clients.showNotification("Proses hapus data berhasil", "info", null, "middle_center", 1500);
													dataForm.getDaftarpelatihan().remove(data);
													gridPelatihan.setModel(new ListModelList<>(dataForm.getDaftarpelatihan()));
												} else {
													Messagebox.show(respAll.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												}
											} catch (Exception e) {	
												Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												e.printStackTrace();
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
				gridPelatihan.setModel(new ListModelList<>(dataForm.getDaftarpelatihan()));

				gridSertifikasi.setRowRenderer(new RowRenderer<Sertifikasi>() {

					@Override
					public void render(Row row, Sertifikasi data, int index) throws Exception {
						row.getChildren().add(new Label(data.getProfesi()));
						row.getChildren().add(new Label(data.getNostr()));
						row.getChildren().add(new Label(data.getNosip()));
						row.getChildren()
								.add(new Label(data.getTglterbitstr() != null
										? new SimpleDateFormat("dd-MM-yyyy").format(data.getTglterbitstr())
										: ""));
						row.getChildren()
								.add(new Label(data.getTglakhirstr() != null
										? new SimpleDateFormat("dd-MM-yyyy").format(data.getTglakhirstr())
										: ""));
						row.getChildren()
								.add(new Label(data.getTglizinpraktek() != null
										? new SimpleDateFormat("dd-MM-yyyy").format(data.getTglizinpraktek())
										: ""));
						Button btEdit = new Button();
						btEdit.setIconSclass("z-icon-edit");
						btEdit.setSclass("btn btn-primary btn-sm");
						btEdit.setAutodisable("self");
						btEdit.setTooltiptext("Edit");
						btEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								doAddSertifikasi(data);
								BindUtils.postNotifyChange(AnggotaFormVm.this, "objSertifikasi");
							}
						});

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
											try {
												String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_sertifikasi() + "/" + data.getSertifikasiid();
												GenericResp respAll = ExtensionServices.getResource(url, null, "delete", oUser.getToken());
												if (respAll.getCode() == 200) {
													Clients.showNotification("Proses hapus data berhasil", "info", null, "middle_center", 1500);
													dataForm.getDaftarsertifikasi().remove(data);
													gridSertifikasi.setModel(new ListModelList<>(dataForm.getDaftarsertifikasi()));
												} else {
													Messagebox.show(respAll.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												}
											} catch (Exception e) {	
												Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
												e.printStackTrace();
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
				gridSertifikasi.setModel(new ListModelList<>(dataForm.getDaftarsertifikasi()));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void setupIsReg() {
		try {
			isReg = true;
			isInsert = true;
			btBack.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		String url = "";
		GenericResp respAll = null;
		try {
			for (int i = 1; i <= 31; i++) {
				Comboitem item = new Comboitem(String.valueOf(i));
				cbDobDay.appendChild(item);
			}
			for (int i = 1; i <= 12; i++) {
				Comboitem item = new Comboitem(StringUtils.getMonthLabel(i));
				cbDobMonth.appendChild(item);
			}
			int yearend = Calendar.getInstance().get(Calendar.YEAR);
			for (int i = 1940; i <= yearend - 10; i++) {
				Comboitem item = new Comboitem(String.valueOf(i));
				cbDobYear.appendChild(item);
			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_provinsi();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Provinsi> provs = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Provinsi>>() {
						});
				provinsiModel = new ListModelList<>(provs);
				provPekerjaanModel = new ListModelList<>(provs);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_region();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Region> regions = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Region>>() {
						});
				regionModel = new ListModelList<>(regions);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_branch();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Branch> branchs = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Branch>>() {
						});
				branchModel = new ListModelList<>(branchs);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_nation();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200)
			{
				List<Nation> nations = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Nation>>() {
						});
				nationModel = new ListModelList<>(nations);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_rumpun();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Rumpun> rumpuns = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Rumpun>>() {
						});
				rumpunModel = new ListModelList<>(rumpuns);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_kepegawaian();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Kepegawaian> kepegawaians = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Kepegawaian>>() {
						});
				kepegawaianModel = new ListModelList<>(kepegawaians);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_university();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<University> universities = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<University>>() {
						});
				universityModel = new ListModelList<>(universities);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_jenjang();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Jenjang> jenjangs = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Jenjang>>() {
						});
				jenjangModel = new ListModelList<>(jenjangs);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_prodi();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Prodi> prodis = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Prodi>>() {
						});
				prodiModel = new ListModelList<>(prodis);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_profesi();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Profesi> profesis = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Profesi>>() {
						});
				profesiModel = new ListModelList<>(profesis);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_rumpundiklat();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Rumpundiklat> rumpundiklats = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Rumpundiklat>>() {
						});
				rumpundiklatModel = new ListModelList<>(rumpundiklats);

			}

			url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_akreditasi();
			respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Akreditasi> akreditasis = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Akreditasi>>() {
						});
				akreditasiModel = new ListModelList<>(akreditasis);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange("kabupatenModel")
	public void doLoadKab(@BindingParam("prov") Provinsi prov) {
		try {
			cbKabupaten.setValue(null);
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_kabupaten() + "/"
					+ prov.getProvcode();
			GenericResp respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Kabupaten> kabs = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Kabupaten>>() {
						});
				kabupatenModel = new ListModelList<>(kabs);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange("kabPekerjaanModel")
	public void doLoadKabPekerjaan(@BindingParam("prov") Provinsi prov) {
		try {
			cbKabPekerjaan.setValue(null);
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_kabupaten() + "/"
					+ prov.getProvcode();
			GenericResp respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Kabupaten> kabs = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Kabupaten>>() {
						});
				kabPekerjaanModel = new ListModelList<>(kabs);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange("kepegawaiansdmkModel")
	public void doLoadKepegawaiansdmk(@BindingParam("kepegawaian") Kepegawaian kepegawaian) {
		try {
			cbKepegawaiansdmk.setValue(null);
			String url = ConfigUtil.getConfig().getUrl_base()
					+ ConfigUtil.getConfig().getEndpoint_kepegawaiansdmk_grupkepegawaian() + "/" + kepegawaian.getId();
			GenericResp respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Kepegawaiansdmk> kepegawaiansdmks = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Kepegawaiansdmk>>() {
						});
				kepegawaiansdmkModel = new ListModelList<>(kepegawaiansdmks);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange("jenisdiklatModel")
	public void doLoadJenisdiklat(@BindingParam("rumpundiklat") Rumpundiklat rumpundiklat) {
		try {
			cbJenisdiklat.setValue(null);
			String url = ConfigUtil.getConfig().getUrl_base()
					+ ConfigUtil.getConfig().getEndpoint_jenisdiklat_byrumpundiklat() + "/" + rumpundiklat.getId();
			GenericResp respAll = ExtensionServices.getResource(url, null, "get", null);
			if (respAll.getCode() == 200) {
				List<Jenisdiklat> jenisdiklats = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						new TypeReference<List<Jenisdiklat>>() {
						});
				jenisdiklatModel = new ListModelList<>(jenisdiklats);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@NotifyChange("*")
	public void doReset() {
		isInsert = true;
		objForm = new RegAnggota();
		objForm.setPribadi(new Pribadi());
		objForm.setPekerjaan(new Pekerjaan());
		objForm.setPendidikan(new Pendidikan());
		objForm.setPelatihan(new Pelatihan());
		objForm.setSertifikasi(new Sertifikasi());

		dob = null;
		nation = null;
		provinsi = null;
		kabupaten = null;
		branch = null;
		provpekerjaan = null;
		kabpekerjaan = null;
		rumpun = null;
		kepegawaian = null;
		kepegawaiansdmk = null;

		university = null;
		jenjang = null;
		prodi = null;

		rumpundiklat = null;
		jenisdiklat = null;
		akreditasi = null;

		profesi = null;

		media = null;
		photo.setVisible(false);
		if (isView == null || !isView) {
			cbDobDay.setValue(null);
			cbDobMonth.setValue(null);
			cbDobYear.setValue(null);

			btDeletePhoto.setVisible(false);
		}
	}

	@NotifyChange("*")
	public void doResetPekerjaan() {
		isInsertPekerjaan = true;
		objPekerjaan = new Pekerjaan();

		provpekerjaan = null;
		kabpekerjaan = null;
		rumpun = null;
		kepegawaian = null;
		kepegawaiansdmk = null;
		cbProvPekerjaan.setValue(null);
		cbKabPekerjaan.setValue(null);
		cbRumpun.setValue(null);
		cbKepegawaian.setValue(null);
		cbKepegawaiansdmk.setValue(null);
		gbPekerjaan.setVisible(false);
		btAddPekerjaan.setLabel("Tambah Pekerjaan");
		btAddPekerjaan.setIconSclass("z-icon-plus-square");
	}

	@NotifyChange("*")
	public void doResetPendidikan() {
		isInsertPendidikan = true;
		objPendidikan = new Pendidikan();

		university = null;
		jenjang = null;
		prodi = null;
		cbUniversity.setValue(null);
		cbJenjang.setValue(null);
		cbProdi.setValue(null);
		gbPendidikan.setVisible(false);
		btAddPendidikan.setLabel("Tambah Pendidikan");
		btAddPendidikan.setIconSclass("z-icon-plus-square");
	}

	@NotifyChange("*")
	public void doResetPelatihan() {
		isInsertPelatihan = true;
		objPelatihan = new Pelatihan();

		rumpundiklat = null;
		jenisdiklat = null;
		akreditasi = null;
		cbRumpundiklat.setValue(null);
		cbJenisdiklat.setValue(null);
		cbAkreditasi.setValue(null);
		gbPelatihan.setVisible(false);
		btAddPelatihan.setLabel("Tambah Pendidikan");
		btAddPelatihan.setIconSclass("z-icon-plus-square");
	}

	@NotifyChange("*")
	public void doResetSertifikasi() {
		isInsertSertifikasi = true;
		objSertifikasi = new Sertifikasi();

		profesi = null;
		cbProfesi.setValue(null);
		gbSertifikasi.setVisible(false);
		btAddSertifikasi.setLabel("Tambah Sertifikasi");
		btAddSertifikasi.setIconSclass("z-icon-plus-square");
	}

	@Command
	public void doPageList() {
		Component comp = winAnggotaForm.getParent();
		comp.getChildren().clear();
		Executions.createComponents("/view/anggota.zul", comp, null);
	}

	@Command
	public void doUploadPhoto(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		try {
			UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
			media = event.getMedia();
			if (media instanceof org.zkoss.image.Image) {
				photobyte = media.getByteData();
				photo.setContent((org.zkoss.image.Image) media);
				photo.setVisible(true);
				btDeletePhoto.setVisible(true);
			} else {
				media = null;
				Messagebox.show("Not an image: " + media, WebApps.getCurrent().getAppName(), Messagebox.OK,
						Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void doDeletePhoto() {
		media = null;
		photobyte = null;
		photo.setSrc(null);
		btDeletePhoto.setVisible(false);
	}

	@Command
	@NotifyChange("*")
	public void doSave() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_anggota();

			objForm.getPribadi().setBranchid(branch.getId());
			objForm.getPribadi().setBranchname(branch.getBranchname());
			objForm.getPribadi().setNationid(nation.getId());
			objForm.getPribadi().setProvcode(provinsi.getProvcode());
			objForm.getPribadi().setProvname(provinsi.getProvname());
			objForm.getPribadi().setKabcode(kabupaten.getKabcode());
			objForm.getPribadi().setKabname(kabupaten.getKabname());
			objForm.getPribadi().setTgllahir(dob);

			objForm.getPekerjaan().setRumpunid(rumpun.getId());
			objForm.getPekerjaan().setRumpun(rumpun.getRumpun());
			objForm.getPekerjaan().setKepegawaianid(kepegawaian.getId());
			objForm.getPekerjaan().setKepegawaian(kepegawaian.getKepegawaian());
			objForm.getPekerjaan().setKepegawaiansdmkid(kepegawaiansdmk.getId());
			objForm.getPekerjaan().setKepegawaiansdmk(kepegawaiansdmk.getKepegawaiansdmk());

			if (university != null) {
				objForm.getPendidikan().setUniversityid(university.getId());
				objForm.getPendidikan().setUniversityname(university.getUniversityname());

				if (jenjang != null) {
					objForm.getPendidikan().setJenjangid(jenjang.getId());
					objForm.getPendidikan().setJenjang(jenjang.getJenjang());
				}
				if (prodi != null) {
					objForm.getPendidikan().setProdiid(prodi.getId());
					objForm.getPendidikan().setProdi(prodi.getProdi());
				}
			} else {
				objForm.setPendidikan(null);
			}

			if (rumpundiklat != null) {
				objForm.getPelatihan().setRumpundiklatid(rumpundiklat.getId());
				objForm.getPelatihan().setRumpundiklat(rumpundiklat.getRumpundiklat());

				if (jenisdiklat != null) {
					objForm.getPelatihan().setJenisdiklatid(jenisdiklat.getId());
					objForm.getPelatihan().setJenisdiklat(jenisdiklat.getJenisdiklat());
				}
			} else {
				objForm.setPelatihan(null);
			}

			if (profesi != null) {
				objForm.getSertifikasi().setProfesiid(profesi.getId());
				objForm.getSertifikasi().setProfesi(profesi.getProfesi());
			} else {
				objForm.setSertifikasi(null);
			}

			String method = "post";
			if (!isInsert)
				method = "put";

			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(objForm), method, null);

			if (isInsert && respAll.getCode() == 201) {
				RegAnggota data = (RegAnggota) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						RegAnggota.class);

				if (media != null) {
					doDocUploader(data.getPribadi().getAnggotaid());
				}
				
				if (isReg != null && isReg) {
					Executions.sendRedirect("/regdone.zul");
				} else {
					Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 1500);
					doReset();
				}
			} else {
				Messagebox.show("Terjadi kegagalan proses simpan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	private void doDocUploader(Long anggotaid) {
		try {
			String path = Executions.getCurrent().getDesktop().getWebApp().getRealPath(AppUtils.PATH_PHOTO);
			if (media.isBinary()) {
				Files.copy(new File(path + File.separator + media.getName()), media.getStreamData());
			} else {
				BufferedWriter writer = new BufferedWriter(new FileWriter(path + File.separator + media.getName()));
				Files.copy(writer, media.getReaderData());
				writer.close();
			}

			DocUpload doc = new DocUpload();
			doc.setFile(path + File.separator + media.getName());
			doc.setFilename(media.getName());

			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_docupload() + "/"
					+ anggotaid;
			ExtensionServices.getMedia(url, doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange("objPekerjaan")
	public void doAddPekerjaan(Pekerjaan obj) {
		try {
			if (obj != null) {
				isInsertPekerjaan = false;
				objPekerjaan = obj;
				gbPekerjaan.setVisible(true);
				btAddPekerjaan.setLabel("Cancel");
				btAddPekerjaan.setIconSclass("z-icon-reply");
				btSavePekerjaan.setLabel("Perbarui");
				
				cbProvPekerjaan.setValue(objPekerjaan.getProvname());
				cbKabPekerjaan.setValue(objPekerjaan.getKabname());
				cbRumpun.setValue(objPekerjaan.getRumpun());
				cbKepegawaian.setValue(objPekerjaan.getKepegawaian());
				cbKepegawaiansdmk.setValue(objPekerjaan.getKepegawaiansdmk());
				
			} else if (btAddPekerjaan.getLabel().equals("Tambah Pekerjaan")) {
				doResetPekerjaan();
				objPekerjaan = new Pekerjaan();
				gbPekerjaan.setVisible(true);
				btAddPekerjaan.setLabel("Cancel");
				btAddPekerjaan.setIconSclass("z-icon-reply");
				btSavePekerjaan.setLabel("Submit");
			} else {
				objPekerjaan = null;
				gbPekerjaan.setVisible(false);
				btAddPekerjaan.setLabel("Tambah Pekerjaan");
				btAddPekerjaan.setIconSclass("z-icon-plus-square");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Command
	@NotifyChange("*")
	public void doSavePekerjaan() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pekerjaan();

			objPekerjaan.setAnggotaid(dataForm.getPribadi().getAnggotaid());
			if (provpekerjaan != null) {
				objPekerjaan.setProvcode(provpekerjaan.getProvcode());
				objPekerjaan.setProvname(provpekerjaan.getProvname());
			}
			if (kabpekerjaan != null) {
				objPekerjaan.setKabcode(kabpekerjaan.getKabcode());
				objPekerjaan.setKabname(kabpekerjaan.getKabname());
			}
			if (rumpun != null) {
				objPekerjaan.setRumpunid(rumpun.getId());
				objPekerjaan.setRumpun(rumpun.getRumpun());
			}
			if (kepegawaian != null) {
				objPekerjaan.setKepegawaianid(kepegawaian.getId());
				objPekerjaan.setKepegawaian(kepegawaian.getKepegawaian());
			}
			if (kepegawaiansdmk != null) {
				objPekerjaan.setKepegawaiansdmkid(kepegawaiansdmk.getId());
				objPekerjaan.setKepegawaiansdmk(kepegawaiansdmk.getKepegawaiansdmk());
			}
			
			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(objPekerjaan), isInsertPekerjaan ? "post" : "put", oUser.getToken());

			if (isInsertPekerjaan && respAll.getCode() == 201) {
				objPekerjaan = (Pekerjaan) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Pekerjaan.class);

				if (dataForm.getDaftarpekerjaan() == null) {
					List<Pekerjaan> objList = new ArrayList<>();
					dataForm.setDaftarpekerjaan(objList);
				}
				dataForm.getDaftarpekerjaan().add(objPekerjaan);
				gridPekerjaan.setModel(new ListModelList<>(dataForm.getDaftarpekerjaan()));

				Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 1500);
				doResetPekerjaan();
			} else if (!isInsertPekerjaan && respAll.getCode() == 200) {
				objPekerjaan = (Pekerjaan) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Pekerjaan.class);
				
				url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pekerjaan_anggota() + "/" + objPekerjaan.getAnggotaid();
				respAll = ExtensionServices.getResource(url, null, "get", oUser.getToken());
				if (respAll.getCode() == 200) {
					List<Pekerjaan> pekerjaans = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<List<Pekerjaan>>() {
							});
					dataForm.setDaftarpekerjaan(pekerjaans);
					gridPekerjaan.setModel(new ListModelList<>(dataForm.getDaftarpekerjaan()));
				}
				
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
				doResetPekerjaan();
			} else {
				Messagebox.show("Terjadi kegagalan proses simpan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("objPendidikan")
	public void doAddPendidikan(Pendidikan obj) {
		if (obj != null) {
			isInsertPendidikan = false;
			objPendidikan = obj;
			gbPendidikan.setVisible(true);
			btAddPendidikan.setLabel("Cancel");
			btAddPendidikan.setIconSclass("z-icon-reply");
			btSavePendidikan.setLabel("Perbarui");
			
			cbUniversity.setValue(obj.getUniversityname());
			cbJenjang.setValue(obj.getJenjang());
			cbProdi.setValue(obj.getProdi());
		} else if (btAddPendidikan.getLabel().equals("Tambah Pendidikan")) {
			doResetPendidikan();
			objPendidikan = new Pendidikan();
			gbPendidikan.setVisible(true);
			btAddPendidikan.setLabel("Cancel");
			btAddPendidikan.setIconSclass("z-icon-reply");
			btSavePendidikan.setLabel("Submit");
		} else {
			objPendidikan = null;
			gbPendidikan.setVisible(false);
			btAddPendidikan.setLabel("Tambah Pendidikan");
			btAddPendidikan.setIconSclass("z-icon-plus-square");
		}
	}

	@Command
	@NotifyChange("*")
	public void doSavePendidikan() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pendidikan();

			objPendidikan.setAnggotaid(dataForm.getPribadi().getAnggotaid());
			if (jenjang != null) {
				objPendidikan.setJenjangid(jenjang.getId());
				objPendidikan.setJenjang(jenjang.getJenjang());	
			}
			if (prodi != null) {
				objPendidikan.setProdiid(prodi.getId());
				objPendidikan.setProdi(prodi.getProdi());	
			}
			if (university != null) {
				objPendidikan.setUniversityid(university.getId());
				objPendidikan.setUniversityname(university.getUniversityname());	
			}
			objPendidikan.setNoijazah(objPendidikan.getNoijazah());
			objPendidikan.setTahunlulus(objPendidikan.getTahunlulus());
			
			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(objPendidikan), isInsertPendidikan ? "post" : "put", oUser.getToken());

			if (isInsertPendidikan && respAll.getCode() == 201) {
				objPendidikan = (Pendidikan) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Pendidikan.class);

				if (dataForm.getDaftarpendidikan() == null) {
					List<Pendidikan> objList = new ArrayList<>();
					dataForm.setDaftarpendidikan(objList);
				}
				dataForm.getDaftarpendidikan().add(objPendidikan);
				gridPendidikan.setModel(new ListModelList<>(dataForm.getDaftarpendidikan()));

				Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 1500);
				doResetPendidikan();
			} else if (!isInsertPendidikan && respAll.getCode() == 200) {
				objPendidikan = (Pendidikan) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Pendidikan.class);
				
				url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pendidikan_anggota() + "/" + objPendidikan.getAnggotaid();
				respAll = ExtensionServices.getResource(url, null, "get", oUser.getToken());
				if (respAll.getCode() == 200) {
					List<Pendidikan> pendidikans = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<List<Pendidikan>>() {
							});
					dataForm.setDaftarpendidikan(pendidikans);
					gridPendidikan.setModel(new ListModelList<>(dataForm.getDaftarpendidikan()));
				}
				
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
				doResetPendidikan();
			} else {
				Messagebox.show("Terjadi kegagalan proses simpan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("objPelatihan")
	public void doAddPelatihan(Pelatihan obj) {
		if (obj != null) {
			isInsertPelatihan = false;
			objPelatihan = obj;
			gbPelatihan.setVisible(true);
			btAddPelatihan.setLabel("Cancel");
			btAddPelatihan.setIconSclass("z-icon-reply");
			btSavePelatihan.setLabel("Perbarui");
			
			cbRumpundiklat.setValue(obj.getRumpundiklat());
			cbJenisdiklat.setValue(obj.getJenisdiklat());
			cbAkreditasi.setValue(obj.getAkreditasi());
		} else if (btAddPelatihan.getLabel().equals("Tambah Pelatihan")) {
			doResetPelatihan();
			objPelatihan = new Pelatihan();
			gbPelatihan.setVisible(true);
			btAddPelatihan.setLabel("Cancel");
			btAddPelatihan.setIconSclass("z-icon-reply");
			btSavePelatihan.setLabel("Submit");
		} else {
			objPelatihan = null;
			gbPelatihan.setVisible(false);
			btAddPelatihan.setLabel("Tambah Pelatihan");
			btAddPelatihan.setIconSclass("z-icon-plus-square");
		}
	}

	@Command
	@NotifyChange("*")
	public void doSavePelatihan() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pelatihan();

			objPelatihan.setAnggotaid(dataForm.getPribadi().getAnggotaid());
			if (rumpundiklat != null) {
				objPelatihan.setRumpundiklatid(rumpundiklat.getId());
				objPelatihan.setRumpundiklat(rumpundiklat.getRumpundiklat());
			}
			if (jenisdiklat != null) {
				objPelatihan.setJenisdiklatid(jenisdiklat.getId());
				objPelatihan.setJenisdiklat(jenisdiklat.getJenisdiklat());	
			}
			if (akreditasi != null) {
				objPelatihan.setAkreditasi(akreditasi.getAkreditasi());
				objPelatihan.setAkreditasiid(akreditasi.getId());
			}
			
			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(objPelatihan), isInsertPelatihan ? "post" : "put", oUser.getToken());

			if (isInsertPelatihan && respAll.getCode() == 201) {
				objPelatihan = (Pelatihan) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Pelatihan.class);

				if (dataForm.getDaftarpelatihan() == null) {
					List<Pelatihan> objList = new ArrayList<>();
					dataForm.setDaftarpelatihan(objList);
				}
				dataForm.getDaftarpelatihan().add(objPelatihan);
				gridPelatihan.setModel(new ListModelList<>(dataForm.getDaftarpelatihan()));

				Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 1500);
				doResetPelatihan();
			} else if (!isInsertPelatihan && respAll.getCode() == 200) {
				objPelatihan = (Pelatihan) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Pelatihan.class);
				url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_pelatihan_anggota() + "/" + objPelatihan.getAnggotaid();
				respAll = ExtensionServices.getResource(url, null, "get", oUser.getToken());
				if (respAll.getCode() == 200) {
					List<Pelatihan> pelatihans = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<List<Pelatihan>>() {
							});
					dataForm.setDaftarpelatihan(pelatihans);
					gridPelatihan.setModel(new ListModelList<>(dataForm.getDaftarpelatihan()));
				}
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
				doResetPelatihan();
			} else {
				Messagebox.show("Terjadi kegagalan proses simpan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("objSertifikasi")
	public void doAddSertifikasi(Sertifikasi obj) {
		if (obj != null) {
			isInsertSertifikasi = false;
			objSertifikasi = obj;
			gbSertifikasi.setVisible(true);
			btAddSertifikasi.setLabel("Cancel");
			btAddSertifikasi.setIconSclass("z-icon-reply");
			btSaveSertifikasi.setLabel("Perbarui");
			
			cbProfesi.setValue(obj.getProfesi());
		} else if (btAddSertifikasi.getLabel().equals("Tambah Sertifikasi")) {
			doResetSertifikasi();
			objSertifikasi = new Sertifikasi();
			gbSertifikasi.setVisible(true);
			btAddSertifikasi.setLabel("Cancel");
			btAddSertifikasi.setIconSclass("z-icon-reply");
			btSaveSertifikasi.setLabel("Submit");
		} else {
			objSertifikasi = null;
			gbSertifikasi.setVisible(false);
			btAddSertifikasi.setLabel("Tambah Sertifikasi");
			btAddSertifikasi.setIconSclass("z-icon-plus-square");
		}
	}

	@Command
	@NotifyChange("*")
	public void doSaveSertifikasi() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_sertifikasi();

			objSertifikasi.setAnggotaid(dataForm.getPribadi().getAnggotaid());
			objSertifikasi.setProfesiid(profesi.getId());
			objSertifikasi.setProfesi(profesi.getProfesi());

			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(objSertifikasi), isInsertSertifikasi ? "post" : "put", oUser.getToken());

			if (isInsertSertifikasi && respAll.getCode() == 201) {
				objSertifikasi = (Sertifikasi) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Sertifikasi.class);

				if (dataForm.getDaftarsertifikasi() == null) {
					List<Sertifikasi> objList = new ArrayList<>();
					dataForm.setDaftarsertifikasi(objList);
				}
				dataForm.getDaftarsertifikasi().add(objSertifikasi);
				gridSertifikasi.setModel(new ListModelList<>(dataForm.getDaftarsertifikasi()));

				Clients.showNotification("Proses simpan data berhasil", "info", null, "middle_center", 1500);
				doResetSertifikasi();
			} else if (!isInsertSertifikasi && respAll.getCode() == 200) {
				objSertifikasi = (Sertifikasi) mapper.readValue(mapper.writeValueAsString(respAll.getData()),
						Sertifikasi.class);
				url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_sertifikasi_anggota() + "/" + objSertifikasi.getAnggotaid();
				respAll = ExtensionServices.getResource(url, null, "get", oUser.getToken());
				if (respAll.getCode() == 200) {
					List<Sertifikasi> sertifikasis = mapper.readValue(mapper.writeValueAsString(respAll.getData()),
							new TypeReference<List<Sertifikasi>>() {
							});
					dataForm.setDaftarsertifikasi(sertifikasis);
					gridSertifikasi.setModel(new ListModelList<>(dataForm.getDaftarsertifikasi()));
				}
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
				doResetSertifikasi();
			} else {
				Messagebox.show("Terjadi kegagalan proses simpan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	public void doAddKontak() {
		if (btAddKontak.getLabel().equals("Tambah Kontak")) {
			gbKontak.setVisible(true);
			btAddKontak.setLabel("Cancel");
			btAddKontak.setIconSclass("z-icon-reply");
		} else {
			gbKontak.setVisible(false);
			btAddKontak.setLabel("Tambah Kontak");
			btAddKontak.setIconSclass("z-icon-plus-square");
		}
	}

	@Command
	@NotifyChange("*")
	public void doSavePribadi() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_anggota_pribadi();

			dataForm.getPribadi().setNationid(nation.getId());
			dataForm.getPribadi().setProvcode(provinsi.getProvcode());
			dataForm.getPribadi().setProvname(provinsi.getProvname());
			dataForm.getPribadi().setKabcode(kabupaten.getKabcode());
			dataForm.getPribadi().setKabname(kabupaten.getKabname());

			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(dataForm.getPribadi()),
					"put", oUser.getToken());

			if (respAll.getCode() == 200) {
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
				if (media != null) {
					doDocUploader(dataForm.getPribadi().getAnggotaid());
				}
			} else {
				Messagebox.show("Terjadi kegagalan proses pembaruan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("*")
	public void doSaveKeanggotaan() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base()
					+ ConfigUtil.getConfig().getEndpoint_anggota_keanggotaan();

			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(dataForm.getPribadi()),
					"put", oUser.getToken());

			if (respAll.getCode() == 200) {
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
			} else {
				Messagebox.show("Terjadi kegagalan proses pembaruan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("*")
	public void doSaveKontak() {
		try {
			String url = ConfigUtil.getConfig().getUrl_base() + ConfigUtil.getConfig().getEndpoint_anggota_kontak();

			mapper.setTimeZone(TimeZone.getDefault());
			mapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
			GenericResp respAll = ExtensionServices.getResource(url, mapper.writeValueAsString(dataForm.getPribadi()),
					"put", oUser.getToken());

			if (respAll.getCode() == 200) {
				Clients.showNotification("Proses pembaruan data berhasil", "info", null, "middle_center", 1500);
			} else {
				Messagebox.show("Terjadi kegagalan proses pembaruan data", WebApps.getCurrent().getAppName(),
						Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), WebApps.getCurrent().getAppName(), Messagebox.OK, Messagebox.ERROR);
		}
	}

	public Validator getValidator() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String nama = (String) ctx.getProperties("pribadi.nama")[0].getValue();
				String noktp = (String) ctx.getProperties("pribadi.noktp")[0].getValue();
				String tempatlahir = (String) ctx.getProperties("pribadi.tempatlahir")[0].getValue();
				String gender = (String) ctx.getProperties("pribadi.gender")[0].getValue();
				String warganegara = (String) ctx.getProperties("pribadi.warganegara")[0].getValue();

				String email = (String) ctx.getProperties("pribadi.email")[0].getValue();
				String alamat = (String) ctx.getProperties("pribadi.alamat")[0].getValue();
				String hp = (String) ctx.getProperties("pribadi.hp")[0].getValue();
				String statusanggota = (String) ctx.getProperties("pribadi.statusanggota")[0].getValue();
				String userid = (String) ctx.getProperties("pribadi.userid")[0].getValue();
				String password = (String) ctx.getProperties("pribadi.password")[0].getValue();

				Date tglmulai = (Date) ctx.getProperties("pekerjaan.tglmulai")[0].getValue();
				String nip = (String) ctx.getProperties("pekerjaan.nip")[0].getValue();

				String namadarurat = (String) ctx.getProperties("pribadi.namadarurat")[0].getValue();
				String hubungan = (String) ctx.getProperties("pribadi.hubungan")[0].getValue();
				String nohpdarurat = (String) ctx.getProperties("pribadi.nohpdarurat")[0].getValue();

				if (nama == null || "".equals(nama.trim()))
					this.addInvalidMessage(ctx, "nama", Labels.getLabel("common.validator.empty"));
				if (noktp == null || "".equals(noktp.trim()))
					this.addInvalidMessage(ctx, "noktp", Labels.getLabel("common.validator.empty"));
				if (tempatlahir == null || "".equals(tempatlahir.trim()))
					this.addInvalidMessage(ctx, "tempatlahir", Labels.getLabel("common.validator.empty"));
				if (gender == null || "".equals(gender.trim()))
					this.addInvalidMessage(ctx, "gender", Labels.getLabel("common.validator.empty"));
				if (warganegara == null || "".equals(warganegara.trim()))
					this.addInvalidMessage(ctx, "warganegara", Labels.getLabel("common.validator.empty"));
				if (nation == null)
					this.addInvalidMessage(ctx, "nation", Labels.getLabel("common.validator.empty"));
				if (provinsi == null)
					this.addInvalidMessage(ctx, "provinsi", Labels.getLabel("common.validator.empty"));
				if (kabupaten == null)
					this.addInvalidMessage(ctx, "kabupaten", Labels.getLabel("common.validator.empty"));

				if (email == null || "".equals(email.trim()))
					this.addInvalidMessage(ctx, "email", Labels.getLabel("common.validator.empty"));
				else if (!StringUtils.emailValidator(email)) {
					this.addInvalidMessage(ctx, "email", "Format e-Mail tidak sesuai");
				}
				if (alamat == null || "".equals(alamat.trim()))
					this.addInvalidMessage(ctx, "alamat", Labels.getLabel("common.validator.empty"));
				if (hp == null || "".equals(hp.trim()))
					this.addInvalidMessage(ctx, "hp", Labels.getLabel("common.validator.empty"));

				if (statusanggota == null || "".equals(statusanggota.trim()))
					this.addInvalidMessage(ctx, "statusanggota", Labels.getLabel("common.validator.empty"));
				if (userid == null || "".equals(userid.trim()))
					this.addInvalidMessage(ctx, "userid", Labels.getLabel("common.validator.empty"));
				if (password == null || "".equals(password.trim()))
					this.addInvalidMessage(ctx, "password", Labels.getLabel("common.validator.empty"));

				if (provpekerjaan == null)
					this.addInvalidMessage(ctx, "provpekerjaan", Labels.getLabel("common.validator.empty"));
				if (kabpekerjaan == null)
					this.addInvalidMessage(ctx, "kabpekerjaan", Labels.getLabel("common.validator.empty"));
				if (rumpun == null)
					this.addInvalidMessage(ctx, "rumpun", Labels.getLabel("common.validator.empty"));
				if (kepegawaian == null)
					this.addInvalidMessage(ctx, "kepegawaian", Labels.getLabel("common.validator.empty"));
				if (kepegawaiansdmk == null)
					this.addInvalidMessage(ctx, "kepegawaiansdmk", Labels.getLabel("common.validator.empty"));
				if (tglmulai == null)
					this.addInvalidMessage(ctx, "tglmulai", Labels.getLabel("common.validator.empty"));
				if (nip == null || "".equals(nip.trim()))
					this.addInvalidMessage(ctx, "nip", Labels.getLabel("common.validator.empty"));

				if (namadarurat == null || "".equals(namadarurat.trim()))
					this.addInvalidMessage(ctx, "namadarurat", Labels.getLabel("common.validator.empty"));
				if (hubungan == null || "".equals(hubungan.trim()))
					this.addInvalidMessage(ctx, "hubungan", Labels.getLabel("common.validator.empty"));
				if (nohpdarurat == null || "".equals(nohpdarurat.trim()))
					this.addInvalidMessage(ctx, "nohpdarurat", Labels.getLabel("common.validator.empty"));

				if (cbDobDay.getValue().trim().length() > 0 && cbDobMonth.getValue().trim().length() > 0
						&& cbDobYear.getValue().trim().length() > 0) {
					String strDob = cbDobYear.getValue().toString() + "/" + (cbDobMonth.getSelectedIndex() + 1) + "/"
							+ cbDobDay.getValue().toString();
					try {
						dob = dateFormatter.parse(strDob);
					} catch (ParseException e) {
						this.addInvalidMessage(ctx, "tgllahir", "Data tanggal lahir tidak sesuai");
						e.printStackTrace();
					}
				}

			}
		};
	}

	public Validator getValidatorPribadi() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String nama = (String) ctx.getProperties("pribadi.nama")[0].getValue();
				String noktp = (String) ctx.getProperties("pribadi.noktp")[0].getValue();
				String tempatlahir = (String) ctx.getProperties("pribadi.tempatlahir")[0].getValue();
				String gender = (String) ctx.getProperties("pribadi.gender")[0].getValue();
				String warganegara = (String) ctx.getProperties("pribadi.warganegara")[0].getValue();
				String alamat = (String) ctx.getProperties("pribadi.alamat")[0].getValue();

				if (nama == null || "".equals(nama.trim()))
					this.addInvalidMessage(ctx, "nama", Labels.getLabel("common.validator.empty"));
				if (noktp == null || "".equals(noktp.trim()))
					this.addInvalidMessage(ctx, "noktp", Labels.getLabel("common.validator.empty"));
				if (tempatlahir == null || "".equals(tempatlahir.trim()))
					this.addInvalidMessage(ctx, "tempatlahir", Labels.getLabel("common.validator.empty"));
				if (gender == null || "".equals(gender.trim()))
					this.addInvalidMessage(ctx, "gender", Labels.getLabel("common.validator.empty"));
				if (warganegara == null || "".equals(warganegara.trim()))
					this.addInvalidMessage(ctx, "warganegara", Labels.getLabel("common.validator.empty"));
				if (nation == null)
					this.addInvalidMessage(ctx, "nation", Labels.getLabel("common.validator.empty"));
				if (provinsi == null)
					this.addInvalidMessage(ctx, "provinsi", Labels.getLabel("common.validator.empty"));
				if (kabupaten == null)
					this.addInvalidMessage(ctx, "kabupaten", Labels.getLabel("common.validator.empty"));
				if (alamat == null || "".equals(alamat.trim()))
					this.addInvalidMessage(ctx, "alamat", Labels.getLabel("common.validator.empty"));
				if (cbDobDay.getValue().trim().length() > 0 && cbDobMonth.getValue().trim().length() > 0
						&& cbDobYear.getValue().trim().length() > 0) {
					String strDob = cbDobYear.getValue().toString() + "/" + (cbDobMonth.getSelectedIndex() + 1) + "/"
							+ cbDobDay.getValue().toString();
					try {
						dob = dateFormatter.parse(strDob);
					} catch (ParseException e) {
						this.addInvalidMessage(ctx, "tgllahir", "Data tanggal lahir tidak sesuai");
						e.printStackTrace();
					}
				}
			}
		};
	}

	public Validator getValidatorKeanggotaan() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String statusanggota = (String) ctx.getProperties("pribadi.statusanggota")[0].getValue();

				if (statusanggota == null || "".equals(statusanggota.trim()))
					this.addInvalidMessage(ctx, "statusanggota", Labels.getLabel("common.validator.empty"));
				if (branch == null)
					this.addInvalidMessage(ctx, "branch", Labels.getLabel("common.validator.empty"));
			}
		};
	}

	public Validator getValidatorPekerjaan() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				Date tglmulai = (Date) ctx.getProperties("tglmulai")[0].getValue();
				String nip = (String) ctx.getProperties("nip")[0].getValue();

				if (cbProvPekerjaan.getValue() == null || cbProvPekerjaan.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "provpekerjaan", Labels.getLabel("common.validator.empty"));
				if (cbKabPekerjaan.getValue() == null || cbKabPekerjaan.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "kabpekerjaan", Labels.getLabel("common.validator.empty"));
				if (cbRumpun.getValue() == null || cbRumpun.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "rumpun", Labels.getLabel("common.validator.empty"));
				if (cbKepegawaian.getValue() == null || cbKepegawaian.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "kepegawaian", Labels.getLabel("common.validator.empty"));
				if (cbKepegawaiansdmk.getValue() == null || cbKepegawaiansdmk.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "kepegawaiansdmk", Labels.getLabel("common.validator.empty"));
				if (tglmulai == null)
					this.addInvalidMessage(ctx, "tglmulai", Labels.getLabel("common.validator.empty"));
				if (nip == null || "".equals(nip.trim()))
					this.addInvalidMessage(ctx, "nip", Labels.getLabel("common.validator.empty"));
			}
		};
	}

	public Validator getValidatorPendidikan() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String tahunlulus = (String) ctx.getProperties("tahunlulus")[0].getValue();
				
				if (tahunlulus == null || "".equals(tahunlulus.trim()))
					this.addInvalidMessage(ctx, "tahunlulus", Labels.getLabel("common.validator.empty"));
				
				if (cbUniversity.getValue() == null || cbUniversity.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "university", Labels.getLabel("common.validator.empty"));
				if (cbJenjang.getValue() == null || cbJenjang.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "jenjang", Labels.getLabel("common.validator.empty"));
				if (cbProdi.getValue() == null || cbProdi.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "prodi", Labels.getLabel("common.validator.empty"));
			}
		};
	}

	public Validator getValidatorPelatihan() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String namadiklat = (String) ctx.getProperties("namadiklat")[0].getValue();

				if (namadiklat == null || "".equals(namadiklat.trim()))
					this.addInvalidMessage(ctx, "namadiklat", Labels.getLabel("common.validator.empty"));

				if (cbRumpundiklat.getValue() == null || cbRumpundiklat.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "rumpundiklat", Labels.getLabel("common.validator.empty"));
				if (cbJenisdiklat.getValue() == null || cbJenisdiklat.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "jenisdiklat", Labels.getLabel("common.validator.empty"));
				

			}
		};
	}

	public Validator getValidatorSertifikasi() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String nostr = (String) ctx.getProperties("nostr")[0].getValue();
				
				if (nostr == null || "".equals(nostr.trim()))
					this.addInvalidMessage(ctx, "nostr", Labels.getLabel("common.validator.empty"));
				if (cbProfesi.getValue() == null || cbProfesi.getValue().trim().length() == 0)
					this.addInvalidMessage(ctx, "profesi", Labels.getLabel("common.validator.empty"));

			}
		};
	}

	public Validator getValidatorKontak() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String email = (String) ctx.getProperties("pribadi.email")[0].getValue();
				String hp = (String) ctx.getProperties("pribadi.hp")[0].getValue();
				String namadarurat = (String) ctx.getProperties("pribadi.namadarurat")[0].getValue();
				String hubungan = (String) ctx.getProperties("pribadi.hubungan")[0].getValue();
				String nohpdarurat = (String) ctx.getProperties("pribadi.nohpdarurat")[0].getValue();

				if (email == null || "".equals(email.trim()))
					this.addInvalidMessage(ctx, "email", Labels.getLabel("common.validator.empty"));
				else if (!StringUtils.emailValidator(email)) {
					this.addInvalidMessage(ctx, "email", "Format e-Mail tidak sesuai");
				}
				if (hp == null || "".equals(hp.trim()))
					this.addInvalidMessage(ctx, "hp", Labels.getLabel("common.validator.empty"));
				if (namadarurat == null || "".equals(namadarurat.trim()))
					this.addInvalidMessage(ctx, "namadarurat", Labels.getLabel("common.validator.empty"));
				if (hubungan == null || "".equals(hubungan.trim()))
					this.addInvalidMessage(ctx, "hubungan", Labels.getLabel("common.validator.empty"));
				if (nohpdarurat == null || "".equals(nohpdarurat.trim()))
					this.addInvalidMessage(ctx, "nohpdarurat", Labels.getLabel("common.validator.empty"));
			}
		};
	}

	public RegAnggota getObjForm() {
		return objForm;
	}

	public void setObjForm(RegAnggota objForm) {
		this.objForm = objForm;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public ListModelList<Region> getRegionModel() {
		return regionModel;
	}

	public void setRegionModel(ListModelList<Region> regionModel) {
		this.regionModel = regionModel;
	}

	public ListModelList<Branch> getBranchModel() {
		return branchModel;
	}

	public void setBranchModel(ListModelList<Branch> branchModel) {
		this.branchModel = branchModel;
	}

	public ListModelList<University> getUniversityModel() {
		return universityModel;
	}

	public void setUniversityModel(ListModelList<University> universityModel) {
		this.universityModel = universityModel;
	}

	public ListModelList<Provinsi> getProvinsiModel() {
		return provinsiModel;
	}

	public void setProvinsiModel(ListModelList<Provinsi> provinsiModel) {
		this.provinsiModel = provinsiModel;
	}

	public ListModelList<Kabupaten> getKabupatenModel() {
		return kabupatenModel;
	}

	public void setKabupatenModel(ListModelList<Kabupaten> kabupatenModel) {
		this.kabupatenModel = kabupatenModel;
	}

	public Provinsi getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(Provinsi provinsi) {
		this.provinsi = provinsi;
	}

	public Kabupaten getKabupaten() {
		return kabupaten;
	}

	public void setKabupaten(Kabupaten kabupaten) {
		this.kabupaten = kabupaten;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Jenjang getJenjang() {
		return jenjang;
	}

	public void setJenjang(Jenjang jenjang) {
		this.jenjang = jenjang;
	}

	public Prodi getProdi() {
		return prodi;
	}

	public void setProdi(Prodi prodi) {
		this.prodi = prodi;
	}

	public ListModelList<Jenjang> getJenjangModel() {
		return jenjangModel;
	}

	public void setJenjangModel(ListModelList<Jenjang> jenjangModel) {
		this.jenjangModel = jenjangModel;
	}

	public ListModelList<Prodi> getProdiModel() {
		return prodiModel;
	}

	public void setProdiModel(ListModelList<Prodi> prodiModel) {
		this.prodiModel = prodiModel;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public ListModelList<Nation> getNationModel() {
		return nationModel;
	}

	public void setNationModel(ListModelList<Nation> nationModel) {
		this.nationModel = nationModel;
	}

	public Rumpun getRumpun() {
		return rumpun;
	}

	public void setRumpun(Rumpun rumpun) {
		this.rumpun = rumpun;
	}

	public ListModelList<Rumpun> getRumpunModel() {
		return rumpunModel;
	}

	public void setRumpunModel(ListModelList<Rumpun> rumpunModel) {
		this.rumpunModel = rumpunModel;
	}

	public Kepegawaian getKepegawaian() {
		return kepegawaian;
	}

	public void setKepegawaian(Kepegawaian kepegawaian) {
		this.kepegawaian = kepegawaian;
	}

	public Kepegawaiansdmk getKepegawaiansdmk() {
		return kepegawaiansdmk;
	}

	public void setKepegawaiansdmk(Kepegawaiansdmk kepegawaiansdmk) {
		this.kepegawaiansdmk = kepegawaiansdmk;
	}

	public ListModelList<Kepegawaian> getKepegawaianModel() {
		return kepegawaianModel;
	}

	public void setKepegawaianModel(ListModelList<Kepegawaian> kepegawaianModel) {
		this.kepegawaianModel = kepegawaianModel;
	}

	public ListModelList<Kepegawaiansdmk> getKepegawaiansdmkModel() {
		return kepegawaiansdmkModel;
	}

	public void setKepegawaiansdmkModel(ListModelList<Kepegawaiansdmk> kepegawaiansdmkModel) {
		this.kepegawaiansdmkModel = kepegawaiansdmkModel;
	}

	public Profesi getProfesi() {
		return profesi;
	}

	public void setProfesi(Profesi profesi) {
		this.profesi = profesi;
	}

	public ListModelList<Profesi> getProfesiModel() {
		return profesiModel;
	}

	public void setProfesiModel(ListModelList<Profesi> profesiModel) {
		this.profesiModel = profesiModel;
	}

	public DataAnggota getDataForm() {
		return dataForm;
	}

	public void setDataForm(DataAnggota dataForm) {
		this.dataForm = dataForm;
	}

	public Rumpundiklat getRumpundiklat() {
		return rumpundiklat;
	}

	public void setRumpundiklat(Rumpundiklat rumpundiklat) {
		this.rumpundiklat = rumpundiklat;
	}

	public Jenisdiklat getJenisdiklat() {
		return jenisdiklat;
	}

	public void setJenisdiklat(Jenisdiklat jenisdiklat) {
		this.jenisdiklat = jenisdiklat;
	}

	public ListModelList<Rumpundiklat> getRumpundiklatModel() {
		return rumpundiklatModel;
	}

	public void setRumpundiklatModel(ListModelList<Rumpundiklat> rumpundiklatModel) {
		this.rumpundiklatModel = rumpundiklatModel;
	}

	public ListModelList<Jenisdiklat> getJenisdiklatModel() {
		return jenisdiklatModel;
	}

	public void setJenisdiklatModel(ListModelList<Jenisdiklat> jenisdiklatModel) {
		this.jenisdiklatModel = jenisdiklatModel;
	}

	public Akreditasi getAkreditasi() {
		return akreditasi;
	}

	public void setAkreditasi(Akreditasi akreditasi) {
		this.akreditasi = akreditasi;
	}

	public ListModelList<Akreditasi> getAkreditasiModel() {
		return akreditasiModel;
	}

	public void setAkreditasiModel(ListModelList<Akreditasi> akreditasiModel) {
		this.akreditasiModel = akreditasiModel;
	}

	public Provinsi getProvpekerjaan() {
		return provpekerjaan;
	}

	public void setProvpekerjaan(Provinsi provpekerjaan) {
		this.provpekerjaan = provpekerjaan;
	}

	public Kabupaten getKabpekerjaan() {
		return kabpekerjaan;
	}

	public void setKabpekerjaan(Kabupaten kabpekerjaan) {
		this.kabpekerjaan = kabpekerjaan;
	}

	public ListModelList<Kabupaten> getKabPekerjaanModel() {
		return kabPekerjaanModel;
	}

	public void setKabPekerjaanModel(ListModelList<Kabupaten> kabPekerjaanModel) {
		this.kabPekerjaanModel = kabPekerjaanModel;
	}

	public ListModelList<Provinsi> getProvPekerjaanModel() {
		return provPekerjaanModel;
	}

	public void setProvPekerjaanModel(ListModelList<Provinsi> provPekerjaanModel) {
		this.provPekerjaanModel = provPekerjaanModel;
	}

	public Pekerjaan getObjPekerjaan() {
		return objPekerjaan;
	}

	public void setObjPekerjaan(Pekerjaan objPekerjaan) {
		this.objPekerjaan = objPekerjaan;
	}

	public Pendidikan getObjPendidikan() {
		return objPendidikan;
	}

	public void setObjPendidikan(Pendidikan objPendidikan) {
		this.objPendidikan = objPendidikan;
	}

	public Pelatihan getObjPelatihan() {
		return objPelatihan;
	}

	public void setObjPelatihan(Pelatihan objPelatihan) {
		this.objPelatihan = objPelatihan;
	}

	public Sertifikasi getObjSertifikasi() {
		return objSertifikasi;
	}

	public void setObjSertifikasi(Sertifikasi objSertifikasi) {
		this.objSertifikasi = objSertifikasi;
	}

}
