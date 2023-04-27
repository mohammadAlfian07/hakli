package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mp2kbkegiatan;
import com.sds.utils.db.StoreHibernateUtil;

public class Mp2kbkegiatanDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mp2kbkegiatan> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mp2kbkegiatan> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mp2kbkegiatan  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mp2kbkegiatan.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mp2kbkegiatan "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mp2kbkegiatan> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mp2kbkegiatan> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mp2kbkegiatan where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Mp2kbkegiatan> listNativeByFilter(String filter, String orderby) throws Exception {		
    	List<Mp2kbkegiatan> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createSQLQuery("select Mp2kbkegiatan.* from Mp2kbkegiatan join Mp2kbranah on mp2kbranahfk = mp2kbranahpk "
				+ "where " + filter + " order by " + orderby).addEntity(Mp2kbkegiatan.class).list();
		session.close();
        return oList;
    }	
	
	public Mp2kbkegiatan findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mp2kbkegiatan oForm = (Mp2kbkegiatan) session.createQuery("from Mp2kbkegiatan where mp2kbkegiatanpk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mp2kbkegiatan order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mp2kbkegiatan findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mp2kbkegiatan oForm = (Mp2kbkegiatan) session.createQuery("from Mp2kbkegiatan where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mp2kbkegiatan oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mp2kbkegiatan oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
