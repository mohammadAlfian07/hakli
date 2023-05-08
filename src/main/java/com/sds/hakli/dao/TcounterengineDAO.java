package com.sds.hakli.dao;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sds.utils.db.StoreHibernateUtil;

public class TcounterengineDAO {

	public String getLastCounter(String counterName, int counter) throws Exception {
		Integer lastCounter = 0;
		String strCounter = "";
		String finalCounter = "";
		char[] fillUploadid = new char[counter];
		Session session = StoreHibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query q = session
					.createQuery("select lastcounter from Tcounterengine where countername = '" + counterName + "'");
			lastCounter = (Integer) q.uniqueResult();
			if (lastCounter != null) {
				lastCounter++;
				session.createSQLQuery("update Tcounterengine set lastcounter = lastcounter + 1 where countername = '"
						+ counterName + "'").executeUpdate();
			} else {
				lastCounter = 1;
				session.createSQLQuery("insert into Tcounterengine values ('" + counterName + "', " + lastCounter + ")")
						.executeUpdate();
			}
			transaction.commit();
			session.close();
			Arrays.fill(fillUploadid, '0');
			strCounter = new String(fillUploadid) + lastCounter;
			finalCounter = counterName
					+ strCounter.substring(strCounter.length()-counter, strCounter.length());
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return finalCounter;
	}
	
	public String getVaCounter(String counterName) throws Exception {
		Integer lastCounter = 0;
		String strCounter = "";
		String finalCounter = "";
		char[] fillUploadid = new char[5];
		Session session = StoreHibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query q = session
					.createQuery("select lastcounter from Tcounterengine where countername = 'VA" + counterName + "'");
			lastCounter = (Integer) q.uniqueResult();
			if (lastCounter != null) {
				lastCounter++;
				session.createSQLQuery("update Tcounterengine set lastcounter = lastcounter + 1 where countername = 'VA"
						+ counterName + "'").executeUpdate();
			} else {
				lastCounter = 1;
				session.createSQLQuery("insert into Tcounterengine values ('VA" + counterName + "', " + lastCounter + ")")
						.executeUpdate();
			}
			transaction.commit();
			session.close();
			Arrays.fill(fillUploadid, '0');
			strCounter = new String(fillUploadid) + lastCounter;
			finalCounter = counterName
					+ strCounter.substring(strCounter.length()-5, strCounter.length());
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return finalCounter;
	}
	
	public String getInvoiceCounter() throws Exception {
		Integer lastCounter = 0;
		String counterName = "";
		String strCounter = "";
		String finalCounter = "";
		char[] fillUploadid = new char[5];
		Session session = StoreHibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			counterName = "INV/" + new SimpleDateFormat("yyMM").format(new Date()) + "/";
			Query q = session
					.createQuery("select lastcounter from Tcounterengine where countername = '" + counterName + "'");
			lastCounter = (Integer) q.uniqueResult();
			if (lastCounter != null) {
				lastCounter++;
				session.createSQLQuery("update Tcounterengine set lastcounter = lastcounter + 1 where countername = '"
						+ counterName + "'").executeUpdate();
			} else {
				lastCounter = 1;
				session.createSQLQuery("insert into Tcounterengine values ('" + counterName + "', " + lastCounter + ")")
						.executeUpdate();
			}
			transaction.commit();
			session.close();
			Arrays.fill(fillUploadid, '0');
			strCounter = new String(fillUploadid) + lastCounter;
			finalCounter = counterName
					+ strCounter.substring(strCounter.length()-5, strCounter.length());
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return finalCounter;
	}
	
	public String getP2kbCounter(String counterName) throws Exception {
		Integer lastCounter = 0;
		String strCounter = "";
		String finalCounter = "";
		char[] fillUploadid = new char[5];
		Session session = StoreHibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			counterName = counterName + new SimpleDateFormat("MMyy").format(new Date());
			Query q = session
					.createQuery("select lastcounter from Tcounterengine where countername = '" + counterName + "'");
			lastCounter = (Integer) q.uniqueResult();
			if (lastCounter != null) {
				lastCounter++;
				session.createSQLQuery("update Tcounterengine set lastcounter = lastcounter + 1 where countername = '"
						+ counterName + "'").executeUpdate();
			} else {
				lastCounter = 1;
				session.createSQLQuery("insert into Tcounterengine values ('" + counterName + "', " + lastCounter + ")")
						.executeUpdate();
			}
			transaction.commit();
			session.close();
			Arrays.fill(fillUploadid, '0');
			strCounter = new String(fillUploadid) + lastCounter;
			finalCounter = counterName
					+ strCounter.substring(strCounter.length()-5, strCounter.length());
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return finalCounter;
	}
}
