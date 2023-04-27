package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mfee;
import com.sds.utils.db.StoreHibernateUtil;

public class MfeeDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mfee> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mfee> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mfee  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mfee.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mfee "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mfee> listAll() throws Exception {		
    	List<Mfee> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mfee order by mfeepk").list();
		session.close();
        return oList;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mfee> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mfee> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mfee where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mfee findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mfee oForm = (Mfee) session.createQuery("from Mfee where mfeepk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mfee order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mfee findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mfee oForm = (Mfee) session.createQuery("from Mfee where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mfee oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mfee oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
