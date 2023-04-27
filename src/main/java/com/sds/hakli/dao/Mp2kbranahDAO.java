package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mp2kbranah;
import com.sds.utils.db.StoreHibernateUtil;

public class Mp2kbranahDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mp2kbranah> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mp2kbranah> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mp2kbranah  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mp2kbranah.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mp2kbranah "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mp2kbranah> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mp2kbranah> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mp2kbranah where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mp2kbranah findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mp2kbranah oForm = (Mp2kbranah) session.createQuery("from Mp2kbranah where mp2kbranahpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mp2kbranah order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mp2kbranah findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mp2kbranah oForm = (Mp2kbranah) session.createQuery("from Mp2kbranah where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mp2kbranah oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mp2kbranah oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
