package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Muniversitas;
import com.sds.utils.db.StoreHibernateUtil;

public class MuniversitasDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Muniversitas> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Muniversitas> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from muniversitas  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Muniversitas.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Muniversitas "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Muniversitas> listAll() throws Exception {		
    	List<Muniversitas> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Muniversitas order by universitas").list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Muniversitas> listByFilter(String filter, String orderby) throws Exception {		
    	List<Muniversitas> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Muniversitas where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Muniversitas findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Muniversitas oForm = (Muniversitas) session.createQuery("from Muniversitas where muniversitaspk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Muniversitas order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Muniversitas findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Muniversitas oForm = (Muniversitas) session.createQuery("from Muniversitas where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Muniversitas oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Muniversitas oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
