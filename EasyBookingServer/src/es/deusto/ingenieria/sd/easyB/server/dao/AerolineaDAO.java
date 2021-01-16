package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;

public class AerolineaDAO implements IAerolineaDAO{
	private static AerolineaDAO instance = null;
	public static AerolineaDAO getInstance() {
		if (instance == null) {
			instance = new AerolineaDAO();
		}		
		
		return instance;
	}
	private PersistenceManagerFactory pmf;

	public AerolineaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	@Override
	public void storeAerolinea(Aerolinea aerolinea) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Aerolinea: " + aerolinea);
			pm.makePersistent(aerolinea);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Aerolinea: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public Aerolinea getAerolinea(int cod_aerolinea) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		Aerolinea aerolinea = null;
		try {
			System.out.println("   * Querying a Aerolinea: " + cod_aerolinea);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Aerolinea.class.getName() + " WHERE cod_aerolinea == " +cod_aerolinea );
			query.setUnique(true);
			aerolinea = (Aerolinea) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return aerolinea;
	}

	@Override
	public void updateAerolinea(Aerolinea aerolinea) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(aerolinea);
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
	public ArrayList<Aerolinea> getAerolineas() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Aerolinea> aeropuertos = new ArrayList<Aerolinea>();

		try {
			System.out.println("   * Retrieving an Extent for Aerolineas.");

			tx.begin();
			Extent<Aerolinea> extent = pm.getExtent(Aerolinea.class, true);
			
			for (Aerolinea aerolinea : extent) {
				aeropuertos.add(aerolinea);
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
	public ArrayList<Aerolinea> getAerolineas(String condition) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

		try {
			System.out.println("   * Executing a Query for Aerolineas given a condition: " + condition);

			tx.begin();
			Extent<Aerolinea> extent = pm.getExtent(Aerolinea.class, true);
			Query<Aerolinea> query = pm.newQuery(extent, condition);

			for (Aerolinea aerolinea : (ArrayList<Aerolinea>) query.execute()) {
				aerolineas.add(aerolinea);
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
		return aerolineas;
	}
	@Override
	public void deleteAllAerolineas() {
		System.out.println("- Cleaning the Aerolineas from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			// Deleting All Aeropuertos.
			Query<Aerolinea> query2 = pm.newQuery(Aerolinea.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' aerolineas deleted from the DB.");
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
