package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public class VueloDAO implements IVueloDAO {

	private PersistenceManagerFactory pmf;
	private static VueloDAO instance = null;
	public static VueloDAO getInstance() {
		if (instance == null) {
			instance = new VueloDAO();
		}		
		
		return instance;
	}
	public VueloDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	@Override
	public void storeVuelo(Vuelo vuelo) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Vuelo: " + vuelo);
			pm.makePersistent(vuelo);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Vuelo: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public Vuelo getVuelo(int cod_vuelo) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		Transaction tx = pm.currentTransaction();
		Vuelo vuelo = null;
		try {
			System.out.println("   * Querying a Vuelo: " + cod_vuelo);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Vuelo.class.getName() + " WHERE cod_vuelo == " + cod_vuelo );
			query.setUnique(true);
			vuelo = (Vuelo) query.execute();
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return vuelo;
	}

	@Override
	public void updateVuelo(Vuelo vuelo) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(vuelo);
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
	public ArrayList<Vuelo> getVuelos() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		try {
			System.out.println("   * Retrieving an Extent for Vuelos.");

			tx.begin();
			Extent<Vuelo> extent = pm.getExtent(Vuelo.class, true);
			
			for (Vuelo vuelo : extent) {
				vuelos.add(vuelo);
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
		return vuelos;
	}

	@Override
	public void deleteAllVuelos() {
		System.out.println("- Cleaning the Vuelos from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			// Getting ready for removing objects - Remove Relationships between Vuelo and Reserva
			Extent<Vuelo> extentB = pm.getExtent(Vuelo.class, true);
			
			for (Vuelo v : extentB) {
				v.removeReservas();
			}
			// Updating the database so changes are considered before commit
			pm.flush();

			// Deleting All Vuelos - Vuelos and Reservas will be deleted due to 'delete on cascade'
			Query<Vuelo> query2 = pm.newQuery(Vuelo.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' vuelos deleted from the DB.");
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
