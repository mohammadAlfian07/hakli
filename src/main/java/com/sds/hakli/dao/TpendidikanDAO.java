package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Tpendidikan;
import com.sds.utils.db.StoreHibernateUtil;

public class TpendidikanDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Tpendidikan> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Tpendidikan> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from tpendidikan  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Tpendidikan.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Tpendidikan "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Tpendidikan> listByFilter(String filter, String orderby) throws Exception {		
    	List<Tpendidikan> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Tpendidikan where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Tpendidikan findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Tpendidikan oForm = (Tpendidikan) session.createQuery("from Tpendidikan where tpendidikanpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Tpendidikan order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Tpendidikan findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Tpendidikan oForm = (Tpendidikan) session.createQuery("from Tpendidikan where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Tpendidikan oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Tpendidikan oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
