package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mcabang;
import com.sds.utils.db.StoreHibernateUtil;

public class McabangDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mcabang> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mcabang> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mcabang  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mcabang.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mcabang "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mcabang> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mcabang> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mcabang where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mcabang findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mcabang oForm = (Mcabang) session.createQuery("from Mcabang where mcabangpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mcabang order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mcabang findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mcabang oForm = (Mcabang) session.createQuery("from Mcabang where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mcabang oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mcabang oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
