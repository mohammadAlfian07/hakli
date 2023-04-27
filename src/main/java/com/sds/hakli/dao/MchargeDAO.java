package com.sds.hakli.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sds.hakli.domain.Mcharge;
import com.sds.utils.db.StoreHibernateUtil;

public class MchargeDAO {
	
	private Session session;

	@SuppressWarnings("unchecked")
	public List<Mcharge> listPaging(int first, int second, String filter, String orderby) throws Exception {		
    	List<Mcharge> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
    	oList = session.createSQLQuery("select * from mcharge  "
				+ "where " + filter + " order by " + orderby + " limit " + second +" offset " + first)
				.addEntity(Mcharge.class).list();		

		session.close();
        return oList;
    }	
	
	public int pageCount(String filter) throws Exception {
		int count = 0;
		if (filter == null || "".equals(filter))
			filter = "0 = 0";
		session = StoreHibernateUtil.openSession();
		count = Integer.parseInt((String) session.createSQLQuery("select count(*) from Mcharge "
				+ "where " + filter).uniqueResult().toString());
		session.close();
        return count;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mcharge> listAll() throws Exception {		
    	List<Mcharge> oList = null;
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mcharge order by charge").list();
		session.close();
        return oList;
    }
	
	@SuppressWarnings("unchecked")
	public List<Mcharge> listByFilter(String filter, String orderby) throws Exception {		
    	List<Mcharge> oList = null;
    	if (filter == null || "".equals(filter))
			filter = "0 = 0";
    	session = StoreHibernateUtil.openSession();
		oList = session.createQuery("from Mcharge where " + filter + " order by " + orderby).list();
		session.close();
        return oList;
    }	
	
	public Mcharge findByPk(Integer pk) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mcharge oForm = (Mcharge) session.createQuery("from Mcharge where mchargepk = " + pk).uniqueResult();
		session.close();
		return oForm;
	}
	
	@SuppressWarnings("rawtypes")
	public List listStr(String fieldname) throws Exception {
		List oList = new ArrayList();
       	session = StoreHibernateUtil.openSession();
       	oList = session.createQuery("select " + fieldname + " from Mcharge order by " + fieldname).list();   
        session.close();
        return oList;
	}
	
	public Mcharge findByFilter(String filter) throws Exception {
		session = StoreHibernateUtil.openSession();
		Mcharge oForm = (Mcharge) session.createQuery("from Mcharge where " + filter).uniqueResult();
		session.close();
		return oForm;
	}
		
	public void save(Session session, Mcharge oForm) throws HibernateException, Exception {
		session.saveOrUpdate(oForm);
	}
	
	public void delete(Session session, Mcharge oForm) throws HibernateException, Exception {
		session.delete(oForm);    
    }

}
