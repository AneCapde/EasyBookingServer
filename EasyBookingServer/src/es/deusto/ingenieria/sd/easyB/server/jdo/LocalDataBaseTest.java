package es.deusto.ingenieria.sd.easyB.server.jdo;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;

public class LocalDataBaseTest {
	
	private static void initializeData() {
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
	
		//AEROPUERTO 1
	    Aeropuerto al1 = new Aeropuerto();
	    al1.setCod_aeropuerto("BIO");
	    al1.setNombre("Bilbao");
	
	    //AEROPUERTO 2
	    Aeropuerto ad1 = new Aeropuerto();
	    ad1.setCod_aeropuerto("VA");
	    ad1.setNombre("Valencia");
	
	    //AEROPUERTO 3
	    Aeropuerto al2 = new Aeropuerto();
	    al2.setCod_aeropuerto("BCN");
	    al2.setNombre("Barcelona");
	    //AEROPUERTO 4
	    Aeropuerto ad2 = new Aeropuerto();
	    ad2.setCod_aeropuerto("MAD");
	    ad2.setNombre("Madrid");
	    
	    DBManager.getInstance().storeObjectInDB(al1);
		DBManager.getInstance().storeObjectInDB(ad1);
		DBManager.getInstance().storeObjectInDB(al2);
		DBManager.getInstance().storeObjectInDB(ad2);

	    //RESERVA 1
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
	
	public static void main(String[] args) {
		initializeData();
	}
}
