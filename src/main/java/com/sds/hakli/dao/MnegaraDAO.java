package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mnegara;
import com.sds.utils.db.StoreHibernateUtil;

public class MnegaraDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mnegara> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mnegara> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mnegara  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mnegara.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mnegara "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mnegara> listAll() throws Exception {		
    	List<Mnegara> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mnegara order by negara").list();
		session.close();
        return oList;
    }	
	
	@SuppressWarnings("unchecked")
	public List<Mnegara> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mnegara> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mnegara where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mnegara findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mnegara oForm = (Mnegara) session.createQuery("from Mnegara where mnegarapk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mnegara order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mnegara findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mnegara oForm = (Mnegara) session.createQuery("from Mnegara where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mnegara oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mnegara oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
