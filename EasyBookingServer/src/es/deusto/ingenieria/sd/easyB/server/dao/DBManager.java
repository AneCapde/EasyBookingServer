package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Pago;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public class DBManager {
	private static DBManager instance = null;
	private PersistenceManagerFactory pmf = null;
	
	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");		
	}
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
			//instance.initializeData();
		}		
		
		return instance;
	}
	
	public void deleteData() {
		List<Usuario> users = DBManager.getInstance().getUsers();
		
		for (Usuario user : users) {
			DBManager.getInstance().deleteObjectFromDB(user);
		}
	}
	
	public void deleteObjectFromDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println(" * Delete an object: " + object);
			
			pm.deletePersistent(object);
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error deleting an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
	
	public void storeObjectInDB(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("  * Storing an object: " + object);
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error storing an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void store(Usuario user) {
		DBManager.getInstance().storeObjectInDB(user);	
	}
	
	public void store(Vuelo vuelo) {
		DBManager.getInstance().storeObjectInDB(vuelo);	
	}

	public void store(Aeropuerto aeropuerto) {
		DBManager.getInstance().storeObjectInDB(aeropuerto);	
	}

	public void store(Pago pago) {
		DBManager.getInstance().storeObjectInDB(pago);
	}
	
	public void store(Reserva reserva) {
		DBManager.getInstance().storeObjectInDB(reserva);
	}
	
	public void store(SistemaPago sistemaPago) {
		DBManager.getInstance().storeObjectInDB(sistemaPago);
	}
	
	public Aeropuerto getAeropuerto(String aeropuertoName) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Aeropuerto aeropuerto = null; 

		try {
			System.out.println("  * Querying a Aeropuerto by name: " + aeropuertoName);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Aeropuerto.class.getName() + " WHERE name == '" + aeropuertoName + "'");
			query.setUnique(true);
			aeropuerto = (Aeropuerto) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Aeropuerto: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return aeropuerto;
	}
	
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
	
	public List<Aeropuerto> getAeropuertos() {
		List<Aeropuerto> aeropuertos = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("  * Retrieving all the Aeropuertos");

			tx.begin();

			Extent<Aeropuerto> extent = pm.getExtent(Aeropuerto.class, true);

			for (Aeropuerto aeropuerto : extent) {
				aeropuerto.add(aeropuerto);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Aeropuertos: " + ex.getMessage());
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
	
	public List<Vuelo> getVuelo() {
		List<Vuelo> vuelos = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("  * Retrieving all the Vuelos");

			tx.begin();

			Extent<Vuelo> extent = pm.getExtent(Vuelo.class, true);

			for (Vuelo vuelo : extent) {
				vuelos.add(vuelo);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Vuelos: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return vuelos;		
	}
	
	public ArrayList<Vuelo> getVuelos(String aeropuertoName) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		try {
			System.out.println(" *Querying a Vuelo of an Aeropuerto: " + aeropuertoName);

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
	
	public Reserva getReserva(String nombrePasajero) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Reserva reserva = null; 

		try {
			System.out.println("  * Querying a Reserva : " + nombrePasajero);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Reserva.class.getName() + " WHERE name == '" + nombrePasajero + "'");
			query.setUnique(true);
			reserva = (Reserva) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println(" $ Error querying a Reserva: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return reserva;
	}
	
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
	
	public List<Reserva> getReservas() {
		List<Reserva> reservas = new ArrayList<>();		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("  * Retrieving all the Reservas");

			tx.begin();

			Extent<Reserva> extent = pm.getExtent(Reserva.class, true);

			for (Reserva reserva : extent) {
				reserva.add(reserva);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Reservas: " + ex.getMessage());
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
	
	
	public Usuario getUser(String email) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Usuario user = null; 

		try {
			System.out.println("  * Querying a User by email: " + email);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE email == '" + email + "'");
			query.setUnique(true);
			user = (Usuario) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return user;
	}
	
	public void updateUsuario(Usuario usuario) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(usuario);
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
	
	public List<Usuario> getUsers() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		List<Usuario> users = new ArrayList<>(); 

		try {
			System.out.println("  * Querying all users");
			tx.begin();

			Extent<Usuario> userExtent = pm.getExtent(Usuario.class, true);

			for (Usuario user : userExtent) {
				users.add(user);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying all users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return users;
	}
	
	public void deleteAllUsuarios() {
		System.out.println("- Cleaning the Usuarios from the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			
			// Getting ready for removing objects - Remove Relationships between Usuario and Reserva
			Extent<Usuario> extentB = pm.getExtent(Usuario.class, true);
			
			for (Usuario u : extentB) {
				u.removeReservas();
			}
			// Updating the database so changes are considered before commit
			pm.flush();

			// Deleting All Usuarios - Reservas and Usuarios will be deleted due to 'delete on cascade'
			Query<Usuario> query2 = pm.newQuery(Usuario.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' usuarios deleted from the DB.");
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

	public Pago getPago(Date fecha) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Pago pago = null; 

		try {
			System.out.println("  * Querying a Pago by fecha: " + fecha);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE fecha == '" + fecha + "'");
			query.setUnique(true);
			pago = (Pago) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a pago: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return pago;
	}


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


	public List<Pago> getPagos() {
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
	
	public SistemaPago getSistemaPago(Date fecha) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		SistemaPago sisPago = null; 

		try {
			System.out.println("  * Querying a SistemaPago by fecha: " + fecha);
			tx.begin();

			Query<?> query = pm.newQuery("SELECT FROM " + SistemaPago.class.getName() + " WHERE fecha == '" + fecha + "'");
			query.setUnique(true);
			sisPago = (SistemaPago) query.execute();

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a SistemaPago: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return sisPago;
	}

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

	public List<SistemaPago> getSistemaPagos() {
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
	
//	private void initializeData() {
//		System.out.println(" * Initializing data base");
//		//Create Sample data
//		Usuario user0 = new Usuario();
//		user0.setEmail(email);
//		user0.setNombre(nombre);
//		//Email y nombre es lo que se añade al resgitrarse
//
//		Usuario user1 = new Usuario();
//		user1.setEmail(email);
//		user1.setNombre(nombre);	
//
//		Usuario user2 = new Usuario();
//		user2.setEmail(email);
//		user2.setNombre(nombre);
//
//		//AEROPUERTO 1
//	    Aeropuerto al1 = new Aeropuerto();
//	    al1.setCod_aeropuerto(01);
//	    al1.setNombre("Bilbao");
//
//	    //AEROPUERTO 2
//	    Aeropuerto ad1 = new Aeropuerto();
//	    ad1.setCod_aeropuerto(02);
//	    ad1.setNombre("Valencia");
//
//	    //AEROPUERTO 3
//	    Aeropuerto al2 = new Aeropuerto();
//	    al2.setCod_aeropuerto(03);
//	    al2.setNombre("Barcelona");
//	    //AEROPUERTO 4
//	    Aeropuerto ad2 = new Aeropuerto();
//	    ad2.setCod_aeropuerto(04);
//	    ad2.setNombre("Madrid");
//
//	    //RESERVA 1
//		Reserva r1 = new Reserva();
//	    r1.setImporte(23.21);
//	    String nombre1 = "Ainara";
//	    String nombre2 = "Leire";		    
//	    r1.getNombre_pasajeros().add(nombre1);
//	    r1.getNombre_pasajeros().add(nombre2);
//	    r1.setFecha(new Date());
//	    //Falta agregar usuario
//
//	    //RESERVA 2
//	    Reserva r2 = new Reserva();
//	    r2.setImporte(25);
//	    String nombre3 = "Olatz";
//	    String nombre4 = "Ane";		    
//	    r2.getNombre_pasajeros().add(nombre3);
//	    r2.getNombre_pasajeros().add(nombre4);
//	    r2.setFecha(new Date());
//
//	    //VUELO 1
//	    Vuelo v1 = new Vuelo(); 
//	    v1.setCod_vuelo(01);
//	    v1.setDestino(ad1);
//	    v1.setOrigen(al1);
//	    v1.setPrecio(21.23);
//	    //Falta agregar la lista de reservas
//
//	    //VUELO 2
//	    Vuelo v2 = new Vuelo(); 
//	    v2.setCod_vuelo(02);
//	    v2.setDestino(al2);
//	    v2.setOrigen(ad2);
//	    v2.setPrecio(24);
//
//	    Pago p1 = new Pago();
//	    p1.setFecha(new Date());
//
//	    r1.setVuelo(v1);
//	    r1.setNumero_asientos(2);
//	    r1.setPago(p1);
//
//	    r2.setVuelo(v2);
//	    r2.setNumero_asientos(2);
//	    r2.setPago(p1);
//
//	    TarjetaCredito tj = new TarjetaCredito();
//	    tj.setTitular("Ainara");
//	    tj.setFecha_caducidad(new Date());
//	    tj.setNum_tarjeta(12341234124312324L);
//
//	    TarjetaCredito tj1 = new TarjetaCredito();
//	    tj1.setTitular("Leire");
//	    tj1.setFecha_caducidad(new Date());
//	    tj1.setNum_tarjeta(12341234124311900L);
//	    try {
//			//Store users in DB
//			DBManager.getInstance().store(user0);
//			DBManager.getInstance().store(user1);
//			DBManager.getInstance().store(user2);
//		} catch (Exception ex) {
//			System.out.println(" $ Error initializing data: " + ex.getMessage());
//			ex.printStackTrace();
//		}
//	}
}
