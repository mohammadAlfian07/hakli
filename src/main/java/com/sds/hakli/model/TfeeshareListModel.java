package com.sds.hakli.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import com.sds.hakli.dao.TfeeshareDAO;
import com.sds.hakli.domain.Tfeeshare;

public class TfeeshareListModel extends AbstractPagingListModel<Tfeeshare> implements Sortable<Tfeeshare> {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int _size = -1;
	List<Tfeeshare> oList;  

	public TfeeshareListModel(int startPageNumber, int pageSize, String filter, String orderby) {
		super(startPageNumber, pageSize, filter, orderby);
	}
	
	@Override
	protected List<Tfeeshare> getPageData(int itemStartNumber, int pageSize, String filter, String orderby) {		
		TfeeshareDAO oDao = new TfeeshareDAO();		
		try {
			oList = oDao.listPaging(itemStartNumber, pageSize, filter, orderby);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oList;
	}

	@Override
	public int getTotalSize(String filter) {
		TfeeshareDAO oDao = new TfeeshareDAO();	
		try {
			_size = oDao.pageCount(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _size;
	}

	@Override
	public void sort(Comparator<Tfeeshare> cmpr, boolean ascending) {		
		Collections.sort(oList, cmpr);
        fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);	
		
	}

	@Override
	public String getSortDirection(Comparator<Tfeeshare> cmpr) {
		return null;
	}
}