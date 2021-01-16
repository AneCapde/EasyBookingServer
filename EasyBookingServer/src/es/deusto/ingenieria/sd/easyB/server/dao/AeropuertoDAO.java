package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;

public class AeropuertoDAO implements IAeropuertoDAO{
	private static AeropuertoDAO instance = null;
	public static AeropuertoDAO getInstance() {
		if (instance == null) {
			instance = new AeropuertoDAO();
		}		
		
		return instance;
	}
	private PersistenceManagerFactory pmf;

	public AeropuertoDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	@Override
	public void storeAeropuerto(Aeropuerto aeropuerto) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Aeropuerto: " + aeropuerto);
			pm.makePersistent(aeropuerto);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Aeropuerto: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public Aeropuerto getAeropuerto(int cod_aeropuerto) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		Aeropuerto aeropuerto = null;
		try {
			System.out.println("   * Querying a Aeropuerto: " + cod_aeropuerto);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Aeropuerto.class.getName() + " WHERE cod_aeropuerto == " +cod_aeropuerto );
			query.setUnique(true);
			aeropuerto = (Aeropuerto) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return aeropuerto;
	}

	@Override
	public void updateAeropuerto(Aeropuerto aeropuerto) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(aeropuerto);
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
	public ArrayList<Aeropuerto> getAeropuertos() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

		try {
			System.out.println("   * Retrieving an Extent for Aeropuertos.");

			tx.begin();
			Extent<Aeropuerto> extent = pm.getExtent(Aeropuerto.class, true);
			
			for (Aeropuerto aeropuerto : extent) {
				aeropuertos.add(aeropuerto);
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
		return aeropuertos;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Aeropuerto> getAeropuertos(String condition) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

		try {
			System.out.println("   * Executing a Query for Aeropuertos given a condition: " + condition);

			tx.begin();
			Extent<Aeropuerto> extent = pm.getExtent(Aeropuerto.class, true);
			Query<Aeropuerto> query = pm.newQuery(extent, condition);

			for (Aeropuerto aeropuerto : (ArrayList<Aeropuerto>) query.execute()) {
				aeropuertos.add(aeropuerto);
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
		return aeropuertos;
	}
	@Override
	public void deleteAllAeropuertos() {
		System.out.println("- Cleaning the Aeropuertos from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			// Deleting All Aeropuertos.
			Query<Aeropuerto> query2 = pm.newQuery(Aeropuerto.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' aeropuertos deleted from the DB.");
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
