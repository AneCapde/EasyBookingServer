package es.deusto.ingenieria.sd.easyB.server.jdo;

public class Main {

	public static void main(String[] args) {
		//IEasyBookingDAO usuarioDAO = new EasyBookingDAO();
		
		//createUsuarioTest(usuarioDAO);
		
		//getAllUsuariosTest(usuarioDAO);
		
		//updateUsuarioTest(usuarioDAO);
		
		//getAllUsuariosTest(usuarioDAO);
		
		//deleteAllObjects(usuarioDAO);
		
		
	}
	
	
//	protected static void createUsuarioTest(IEasyBookingDAO objectDAO) {
//		try {
//			System.out.println(" - Creating 2 new usuarios ... ");
//			
//			//RESERVA 1
//			Reserva r1 = new Reserva();
//		    r1.setImporte(23.21);
//		    String nombre1 = "Ainara";
//		    String nombre2 = "Leire";		    
//		    r1.getNombre_pasajeros().add(nombre1);
//		    r1.getNombre_pasajeros().add(nombre2);
//		    r1.setFecha(new Date());
//		    //Falta agregar usuario
//		    
//		    //RESERVA 2
//		    Reserva r2 = new Reserva();
//		    r2.setImporte(25);
//		    String nombre3 = "Olatz";
//		    String nombre4 = "Ane";		    
//		    r2.getNombre_pasajeros().add(nombre3);
//		    r2.getNombre_pasajeros().add(nombre4);
//		    r2.setFecha(new Date());
//		  //Falta agregar usuario
//		    
//		    //AEROPUERTO 1
//		    Aeropuerto al1 = new Aeropuerto();
//		    al1.setCod_aeropuerto(01);
//		    al1.setNombre("Bilbao");
//		    
//		    //AEROPUERTO 2
//		    Aeropuerto ad1 = new Aeropuerto();
//		    ad1.setCod_aeropuerto(02);
//		    ad1.setNombre("Valencia");
//		    
//		    //AEROPUERTO 3
//		    Aeropuerto al2 = new Aeropuerto();
//		    al2.setCod_aeropuerto(03);
//		    al2.setNombre("Barcelona");
//		    
//		    //AEROPUERTO 4
//		    Aeropuerto ad2 = new Aeropuerto();
//		    ad2.setCod_aeropuerto(04);
//		    ad2.setNombre("Madrid");
//		    
//		    //VUELO 1
//		    Vuelo v1 = new Vuelo(); 
//		    v1.setCod_vuelo(01);
//		    v1.setDestino(ad1);
//		    v1.setOrigen(al1);
//		    v1.setPrecio(21.23);
//		    //Falta agregar la lista de reservas
//		    
//		    //VUELO 2
//		    Vuelo v2 = new Vuelo(); 
//		    v2.setCod_vuelo(02);
//		    v2.setDestino(al2);
//		    v2.setOrigen(ad2);
//		    v2.setPrecio(24);
//		   
//		    Pago p1 = new Pago();
//		    p1.setFecha(new Date());
//		    
//		    r1.setVuelo(v1);
//		    r1.setNumero_asientos(2);
//		    r1.setPago(p1);
//		    
//		    r2.setVuelo(v2);
//		    r2.setNumero_asientos(2);
//		    r2.setPago(p1);
//		    
//		    TarjetaCredito tj = new TarjetaCredito();
//		    tj.setTitular("Ainara");
//		    tj.setFecha_caducidad(new Date());
//		    tj.setNum_tarjeta(12341234124312324L);
//		    
//		    TarjetaCredito tj1 = new TarjetaCredito();
//		    tj1.setTitular("Leire");
//		    tj1.setFecha_caducidad(new Date());
//		    tj1.setNum_tarjeta(12341234124311900L);
//		    
//		    
//		    Usuario u1 = new Usuario();
//		    u1.setEmail("ainara.astondoa@opendeusto.es");
//		    u1.setNombre("Ainara");
//		    u1.setAeropuesto(ad1);
//		    u1.setSistemaA(SistemaAutorizacion.GOOGLE);
//		    u1.getReservas().add(r1);
//		    u1.setSistemaPago(tj);
//		    
//		    Usuario u2 = new Usuario();
//		    u2.setEmail("leire.fernandez@opendeusto.es");
//		    u2.setNombre("Leire");
//		    u2.setAeropuesto(ad1);
//		    u2.setSistemaA(SistemaAutorizacion.GOOGLE);
//		    u2.getReservas().add(r2);
//		    u2.setSistemaPago(tj1);
//			
//			objectDAO.storeUsuario(u1);
//			objectDAO.storeUsuario(u2);
//			
//		} catch (Exception ex) {
//			System.out.println(" $ Error creating a new usuario: " + ex.getMessage());
//		}
//	}
//		
//	protected static void getAllUsuariosTest(IEasyBookingDAO objectDAO) {
//		try {
//			System.out.println("     - ArrayList de Usuarios ...");
//			
//			for (Usuario usuario : objectDAO.getUsuarios()) {
//				System.out.println("        # " + usuario.getEmail());
//			}
//		} catch (Exception ex) {
//			System.out.println(" $ Error getting ArrayList of usuarios " + ex.getMessage());
//		}
//	}
//	
//	protected static void updateUsuarioTest(IEasyBookingDAO objectDAO) {
//		try {
//			Usuario usuario = objectDAO.getUsuario("Ainara");
//			System.out.println(" - Detaching and updating a product: " + usuario);
//			usuario.setEmail("ainara.astondoa@gmail.com");
//			objectDAO.updateUsuario(usuario);
//			System.out.println("     - Attaching a product after changing description: " + usuario);
//			
//		} catch (Exception ex) {
//			System.out.println(" $ Error detaching-attaching: " + ex.getMessage());
//		}
//	}
//	
//	protected static void deleteAllObjects(IEasyBookingDAO objectDAO) {
//		try {
//			objectDAO.deleteAllUsuarios();
//			System.out.println("   ==== DB emptied ====    ");
//		} catch (Exception ex) {
//			System.out.println(" $ Error emptying DB: " + ex.getMessage());
//		}
//	}
	
}