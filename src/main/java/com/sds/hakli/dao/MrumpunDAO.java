package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mrumpun;
import com.sds.utils.db.StoreHibernateUtil;

public class MrumpunDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mrumpun> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mrumpun> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mrumpun  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mrumpun.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mrumpun "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mrumpun> listAll() throws Exception {		
    	List<Mrumpun> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mrumpun order by rumpun").list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Mrumpun> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mrumpun> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mrumpun where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mrumpun findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mrumpun oForm = (Mrumpun) session.createQuery("from Mrumpun where mrumpunpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mrumpun order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mrumpun findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mrumpun oForm = (Mrumpun) session.createQuery("from Mrumpun where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mrumpun oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mrumpun oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
