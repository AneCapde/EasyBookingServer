package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.jdo.Reserva;

public class ReservaDAO implements IReservaDAO{

	private PersistenceManagerFactory pmf;

	public ReservaDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	@Override
	public void storeReserva(Reserva reserva) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("   * Storing Reserva: " + reserva);
			pm.makePersistent(reserva);
			pm.flush();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing Reserva: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	@Override
	public Reserva getReserva(String nombre) { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateReserva(Reserva reserva) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(reserva);
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
	public ArrayList<Reserva> getReservas() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();

		try {
			System.out.println("   * Retrieving an Extent for Reservas.");

			tx.begin();
			Extent<Reserva> extent = pm.getExtent(Reserva.class, true);
			
			for (Reserva reserva : extent) {
				reservas.add(reserva);
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
		return reservas;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Reserva> getReservas(String condition) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();

		try {
			System.out.println("   * Executing a Query for Reservas given a condition: " + condition);

			tx.begin();
			Extent<Reserva> extent = pm.getExtent(Reserva.class, true);
			Query<Reserva> query = pm.newQuery(extent, condition);

			for (Reserva reserva : (ArrayList<Reserva>) query.execute()) {
				reservas.add(reserva);
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

		return reservas;
	}
	@Override
	public void deleteAllReservas() {
		System.out.println("- Cleaning the Reservas from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			// Deleting All Reservas.
			Query<Reserva> query2 = pm.newQuery(Reserva.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' reservas deleted from the DB.");
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
