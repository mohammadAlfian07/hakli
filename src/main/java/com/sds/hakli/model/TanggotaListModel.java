package com.sds.hakli.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import com.sds.hakli.dao.TanggotaDAO;
import com.sds.hakli.domain.Tanggota;

public class TanggotaListModel extends AbstractPagingListModel<Tanggota> implements Sortable<Tanggota> {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int _size = -1;
	List<Tanggota> oList;  

	public TanggotaListModel(int startPageNumber, int pageSize, String filter, String orderby) {
		super(startPageNumber, pageSize, filter, orderby);
	}
	
	@Override
	protected List<Tanggota> getPageData(int itemStartNumber, int pageSize, String filter, String orderby) {		
		TanggotaDAO oDao = new TanggotaDAO();		
		try {
			oList = oDao.listPaging(itemStartNumber, pageSize, filter, orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oList;
	}

	@Override
	public int getTotalSize(String filter) {
		TanggotaDAO oDao = new TanggotaDAO();	
		try {
			_size = oDao.pageCount(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _size;
	}

	@Override
	public void sort(Comparator<Tanggota> cmpr, boolean ascending) {		
		Collections.sort(oList, cmpr);
        fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);	
		
	}

	@Override
	public String getSortDirection(Comparator<Tanggota> cmpr) {
		return null;
	}
}