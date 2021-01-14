package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;

public class SistemaPagoDAO implements ISistemaPagoDAO{

	private PersistenceManagerFactory pmf;

	public SistemaPagoDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	@Override
	public void storeSistemaPago(SistemaPago sistemaPago) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Sistema de Pago: " + sistemaPago);
			pm.makePersistent(sistemaPago);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Sistema de Pago: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public SistemaPago getSistemaPago(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePago(SistemaPago sistemaPago) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(sistemaPago);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}


	@Override
	public ArrayList<SistemaPago> getSistemaPagos() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<SistemaPago> sistemas = new ArrayList<SistemaPago>();

		try {
			System.out.println("   * Retrieving an Extent for Sistema de Pago.");

			tx.begin();
			Extent<SistemaPago> extent = pm.getExtent(SistemaPago.class, true);
			
			for (SistemaPago sistema : extent) {
				sistemas.add(sistema);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return sistemas;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<SistemaPago> getSistemaPagos(String condition) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<SistemaPago> sistemas = new ArrayList<SistemaPago>();

		try {
			System.out.println("   * Executing a Query for SistemaPago given a condition: " + condition);

			tx.begin();
			Extent<SistemaPago> extent = pm.getExtent(SistemaPago.class, true);
			Query<SistemaPago> query = pm.newQuery(extent, condition);

			for (SistemaPago sistema : (ArrayList<SistemaPago>) query.execute()) {
				sistemas.add(sistema);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return sistemas;
	}
	@Override
	public void deleteAllSistemaPagos() {
		System.out.println("- Cleaning the Sistemas de Pago from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			// Deleting All Sistemas de Pago.
			Query<SistemaPago> query2 = pm.newQuery(SistemaPago.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' Sitemas de Pago deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}

}
