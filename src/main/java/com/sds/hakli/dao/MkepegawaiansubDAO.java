package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mkepegawaiansub;
import com.sds.utils.db.StoreHibernateUtil;

public class MkepegawaiansubDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mkepegawaiansub> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mkepegawaiansub> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mkepegawaiansub  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mkepegawaiansub.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mkepegawaiansub "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mkepegawaiansub> listAll() throws Exception {		
    	List<Mkepegawaiansub> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mkepegawaiansub order by kepegawaiansub").list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Mkepegawaiansub> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mkepegawaiansub> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mkepegawaiansub where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mkepegawaiansub findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mkepegawaiansub oForm = (Mkepegawaiansub) session.createQuery("from Mkepegawaiansub where mkepegawaiansubpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mkepegawaiansub order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mkepegawaiansub findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mkepegawaiansub oForm = (Mkepegawaiansub) session.createQuery("from Mkepegawaiansub where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mkepegawaiansub oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mkepegawaiansub oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
