package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Tevent;
import com.sds.utils.db.StoreHibernateUtil;

public class TeventDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Tevent> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Tevent> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from tevent  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Tevent.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Tevent "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Tevent> listByFilter(String filter, String orderby) throws Exception {		
    	List<Tevent> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Tevent where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Tevent findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Tevent oForm = (Tevent) session.createQuery("from Tevent where teventpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Tevent order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Tevent findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Tevent oForm = (Tevent) session.createQuery("from Tevent where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Tevent oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Tevent oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
