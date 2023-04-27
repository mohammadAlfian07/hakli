package com.sds.hakli.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import com.sds.hakli.dao.TinvoiceDAO;
import com.sds.hakli.domain.Tinvoice;

public class TinvoiceListModel extends AbstractPagingListModel<Tinvoice> implements Sortable<Tinvoice> {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int _size = -1;
	List<Tinvoice> oList;  

	public TinvoiceListModel(int startPageNumber, int pageSize, String filter, String orderby) {
		super(startPageNumber, pageSize, filter, orderby);
	}
	
	@Override
	protected List<Tinvoice> getPageData(int itemStartNumber, int pageSize, String filter, String orderby) {		
		TinvoiceDAO oDao = new TinvoiceDAO();		
		try {
			oList = oDao.listPaging(itemStartNumber, pageSize, filter, orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oList;
	}

	@Override
	public int getTotalSize(String filter) {
		TinvoiceDAO oDao = new TinvoiceDAO();	
		try {
			_size = oDao.pageCount(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _size;
	}

	@Override
	public void sort(Comparator<Tinvoice> cmpr, boolean ascending) {		
		Collections.sort(oList, cmpr);
        fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);	
		
	}

	@Override
	public String getSortDirection(Comparator<Tinvoice> cmpr) {
		return null;
	}
}