package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mkepegawaian;
import com.sds.utils.db.StoreHibernateUtil;

public class MkepegawaianDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mkepegawaian> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mkepegawaian> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mkepegawaian  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mkepegawaian.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mkepegawaian "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mkepegawaian> listAll() throws Exception {		
    	List<Mkepegawaian> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mkepegawaian order by kepegawaian").list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Mkepegawaian> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mkepegawaian> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mkepegawaian where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mkepegawaian findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mkepegawaian oForm = (Mkepegawaian) session.createQuery("from Mkepegawaian where mkepegawaianpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mkepegawaian order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mkepegawaian findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mkepegawaian oForm = (Mkepegawaian) session.createQuery("from Mkepegawaian where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mkepegawaian oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mkepegawaian oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
