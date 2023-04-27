package com.sds.hakli.viewmodel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Color;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.plotOptions.PiePlotOptions;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.sds.hakli.dao.TanggotaDAO;
import com.sds.hakli.domain.BranchTop;
import com.sds.hakli.domain.Muser;

public class DashboardVm {

	private Session session = Sessions.getCurrent();
	private Muser oUser;

	private TanggotaDAO anggotaDao = new TanggotaDAO();

	private String totalanggota;
	private List<BranchTop> objList = new ArrayList<>();
	private List<BranchTop> objListTopTen = new ArrayList<>();

	@Wire
	private Grid grid;
	@Wire
	private Charts chart;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		oUser = (Muser) session.getAttribute("oUser");

		grid.setRowRenderer(new RowRenderer<BranchTop>() {

			@Override
			public void render(Row row, BranchTop data, int index) throws Exception {
				row.getChildren().add(new Label(data.getName()));
				row.getChildren().add(new Label(NumberFormat.getInstance().format(data.getTotal())));
			}
		});

		doCount();
		doBranchTop();
		doChart();
	}

	@NotifyChange("totalanggota")
	public void doCount() {
		try {
			totalanggota = NumberFormat.getInstance().format(anggotaDao.pageCount("0=0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doBranchTop() {
		try {
			objList = anggotaDao.listBranchTop();

			objListTopTen = anggotaDao.listTop10();
			grid.setModel(new ListModelList<>(objListTopTen));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doChart() {
		chart.setPlotBackgroundColor((Color) null);
		chart.setPlotBorderWidth(null);
		chart.setPlotShadow(false);

		chart.getTooltip().setPointFormat("{series.name}: <b>{point.percentage:.1f}%</b>");

		chart.getAccessibility().getPoint().setValueSuffix("%");

		PiePlotOptions plotOptions = chart.getPlotOptions().getPie();
		plotOptions.setAllowPointSelect(true);
		plotOptions.setCursor("pointer");
		plotOptions.getDataLabels().setEnabled(false);
		plotOptions.setShowInLegend(true);

		Series series = chart.getSeries();
		series.setName("Cabang");

		for (BranchTop obj : objList) {
			series.addPoint(new Point(obj.getName(), obj.getTotal()));
		}

	}

	public String getTotalanggota() {
		return totalanggota;
	}

	public void setTotalanggota(String totalanggota) {
		this.totalanggota = totalanggota;
	}

}
