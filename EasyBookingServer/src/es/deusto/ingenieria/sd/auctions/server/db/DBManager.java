package es.deusto.ingenieria.sd.auctions.server.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.easyB.server.jdo.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.jdo.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.jdo.Pago;
import es.deusto.ingenieria.sd.easyB.server.jdo.Paypal;
import es.deusto.ingenieria.sd.easyB.server.jdo.Reserva;
import es.deusto.ingenieria.sd.easyB.server.jdo.SistemaAutorizacion;
import es.deusto.ingenieria.sd.easyB.server.jdo.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.jdo.TarjetaCredito;
import es.deusto.ingenieria.sd.easyB.server.jdo.Usuario;
import es.deusto.ingenieria.sd.easyB.server.jdo.Vuelo;

public class DBManager {	
	private static DBManager instance = null;
	private PersistenceManagerFactory pmf = null;
	
	private DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");		
	}
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
			instance.initializeData();
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
	
	public void store(Aeropuerto aeropuerto) {
		DBManager.getInstance().storeObjectInDB(aeropuerto);	
	}

	public void store(Aerolinea aerolinea) {
		DBManager.getInstance().storeObjectInDB(aerolinea);	
	}

	public void store(Vuelo vuelo) {
		DBManager.getInstance().storeObjectInDB(vuelo);
	}
	
	public void store(Reserva reserva) {
		DBManager.getInstance().storeObjectInDB(reserva);
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

	public List<Aeropuerto> getAeropuerto() {
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

	public Aerolinea getAerolinea(String aerolineaName) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Aerolinea aerolinea = null; 

		try {
			System.out.println("  * Querying an Aerolinea by name: " + aerolineaName);
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Aerolinea.class.getName() + " WHERE name == " + aerolineaName);
			query.setUnique(true);
			aerolinea = (Aerolinea) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an Aerolinea: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return aerolinea;
	}

	public ArrayList<Aerolinea> getAerolineas(String aerolineaName) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		ArrayList<Aerolinea> articles = new ArrayList<>();

		try {
			System.out.println("  * Querying aerolineas of a Aeropuerto: " + aerolineaName);
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + Aerolinea.class.getName() + " WHERE name == '" + aerolineaName + "'");
			query.setUnique(true);
			Aerolinea aerolinea = (Aerolinea) query.execute();
			
			tx.commit();
			
			if (aerolinea != null) {				
				//Falta por poner
			}			
		} catch (Exception ex) {
			System.out.println("  $ Error querying Aerolinea of a Aeropuerto: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return articles;
	}
	
	public Reserva getReserva(String aeropuertoName) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		Reserva reserva = null; 

		try {
			System.out.println("  * Querying a Reserva by name: " + aeropuertoName);
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + Reserva.class.getName() + " WHERE name == '" + aeropuertoName + "'");
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
	
	private void initializeData() {
		System.out.println(" * Initializing data base");
		//Create Sample data
		Usuario user0 = new Usuario();
		user0.setEmail(email);
		user0.setNombre(nombre);
		//Email y nombre es lo que se añade al resgitrarse
						
		Usuario user1 = new Usuario();
		user1.setEmail(email);
		user1.setNombre(nombre);	
		
		Usuario user2 = new Usuario();
		user2.setEmail(email);
		user2.setNombre(nombre);
						
		//AEROPUERTO 1
	    Aeropuerto al1 = new Aeropuerto();
	    al1.setCod_aeropuerto(01);
	    al1.setNombre("Bilbao");
	    
	    //AEROPUERTO 2
	    Aeropuerto ad1 = new Aeropuerto();
	    ad1.setCod_aeropuerto(02);
	    ad1.setNombre("Valencia");
	    
	    //AEROPUERTO 3
	    Aeropuerto al2 = new Aeropuerto();
	    al2.setCod_aeropuerto(03);
	    al2.setNombre("Barcelona");
	    
	    //AEROPUERTO 4
	    Aeropuerto ad2 = new Aeropuerto();
	    ad2.setCod_aeropuerto(04);
	    ad2.setNombre("Madrid");
	    
	    //RESERVA 1
		Reserva r1 = new Reserva();
	    r1.setImporte(23.21);
	    String nombre1 = "Ainara";
	    String nombre2 = "Leire";		    
	    r1.getNombre_pasajeros().add(nombre1);
	    r1.getNombre_pasajeros().add(nombre2);
	    r1.setFecha(new Date());
	    //Falta agregar usuario
	    
	    //RESERVA 2
	    Reserva r2 = new Reserva();
	    r2.setImporte(25);
	    String nombre3 = "Olatz";
	    String nombre4 = "Ane";		    
	    r2.getNombre_pasajeros().add(nombre3);
	    r2.getNombre_pasajeros().add(nombre4);
	    r2.setFecha(new Date());

	    //VUELO 1
	    Vuelo v1 = new Vuelo(); 
	    v1.setCod_vuelo(01);
	    v1.setDestino(ad1);
	    v1.setOrigen(al1);
	    v1.setPrecio(21.23);
	    //Falta agregar la lista de reservas
	    
	    //VUELO 2
	    Vuelo v2 = new Vuelo(); 
	    v2.setCod_vuelo(02);
	    v2.setDestino(al2);
	    v2.setOrigen(ad2);
	    v2.setPrecio(24);
	   
	    Pago p1 = new Pago();
	    p1.setFecha(new Date());
	    
	    r1.setVuelo(v1);
	    r1.setNumero_asientos(2);
	    r1.setPago(p1);
	    
	    r2.setVuelo(v2);
	    r2.setNumero_asientos(2);
	    r2.setPago(p1);
	    
	    TarjetaCredito tj = new TarjetaCredito();
	    tj.setTitular("Ainara");
	    tj.setFecha_caducidad(new Date());
	    tj.setNum_tarjeta(12341234124312324L);
	    
	    TarjetaCredito tj1 = new TarjetaCredito();
	    tj1.setTitular("Leire");
	    tj1.setFecha_caducidad(new Date());
	    tj1.setNum_tarjeta(12341234124311900L);
		
		try {
			//Store users in DB
			DBManager.getInstance().store(user0);
			DBManager.getInstance().store(user1);
			DBManager.getInstance().store(user2);
		} catch (Exception ex) {
			System.out.println(" $ Error initializing data: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}