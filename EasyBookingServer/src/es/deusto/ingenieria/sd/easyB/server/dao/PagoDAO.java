package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.Pago;

public class PagoDAO implements IPagoDAO{
	private PersistenceManagerFactory pmf;
	private static PagoDAO instance = null;
	public static PagoDAO getInstance() {
		if (instance == null) {
			instance = new PagoDAO();
		}		
		
		return instance;
	}

	public PagoDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public void storePago(Pago pago) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Pago: " + pago);
			pm.makePersistent(pago);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Pago: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public Pago getPago(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePago(Pago pago) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(pago);
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
	public ArrayList<Pago> getPagos() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Pago> pagos = new ArrayList<Pago>();

		try {
			System.out.println("   * Retrieving an Extent for Pagos.");

			tx.begin();
			Extent<Pago> extent = pm.getExtent(Pago.class, true);
			
			for (Pago pago : extent) {
				pagos.add(pago);
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
		return pagos;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Pago> getPagos(String condition) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Pago> pagos = new ArrayList<Pago>();

		try {
			System.out.println("   * Executing a Query for Usuarios given a condition: " + condition);

			tx.begin();
			Extent<Pago> extent = pm.getExtent(Pago.class, true);
			Query<Pago> query = pm.newQuery(extent, condition);

			for (Pago pago : (ArrayList<Pago>) query.execute()) {
				pagos.add(pago);
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

		return pagos;
	}
	@Override
	public void deleteAllPagos() {
		System.out.println("- Cleaning the Pagos from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			// Deleting All Pago.
			Query<Pago> query2 = pm.newQuery(Pago.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' pago deleted from the DB.");
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
