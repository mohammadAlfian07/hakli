package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mjenjang;
import com.sds.utils.db.StoreHibernateUtil;

public class MjenjangDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mjenjang> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mjenjang> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mjenjang  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mjenjang.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mjenjang "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mjenjang> listAll() throws Exception {		
    	List<Mjenjang> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mjenjang order by jenjang").list();
		session.close();
        return oList;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mjenjang> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mjenjang> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mjenjang where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mjenjang findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mjenjang oForm = (Mjenjang) session.createQuery("from Mjenjang where mjenjangpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mjenjang order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mjenjang findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mjenjang oForm = (Mjenjang) session.createQuery("from Mjenjang where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mjenjang oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mjenjang oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
