package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mkabupaten;
import com.sds.utils.db.StoreHibernateUtil;

public class MkabupatenDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mkabupaten> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mkabupaten> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mkabupaten  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mkabupaten.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mkabupaten "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mkabupaten> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mkabupaten> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mkabupaten where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mkabupaten findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mkabupaten oForm = (Mkabupaten) session.createQuery("from Mkabupaten where mkabupatenpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mkabupaten order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mkabupaten findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mkabupaten oForm = (Mkabupaten) session.createQuery("from Mkabupaten where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mkabupaten oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mkabupaten oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
