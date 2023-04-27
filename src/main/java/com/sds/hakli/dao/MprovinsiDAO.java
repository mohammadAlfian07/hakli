package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mprovinsi;
import com.sds.utils.db.StoreHibernateUtil;

public class MprovinsiDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mprovinsi> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mprovinsi> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mprovinsi  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mprovinsi.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mprovinsi "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mprovinsi> listAll() throws Exception {		
    	List<Mprovinsi> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mprovinsi order by provname").list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Mprovinsi> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mprovinsi> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mprovinsi where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mprovinsi findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mprovinsi oForm = (Mprovinsi) session.createQuery("from Mprovinsi where mprovinsipk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mprovinsi order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mprovinsi findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mprovinsi oForm = (Mprovinsi) session.createQuery("from Mprovinsi where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mprovinsi oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mprovinsi oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
