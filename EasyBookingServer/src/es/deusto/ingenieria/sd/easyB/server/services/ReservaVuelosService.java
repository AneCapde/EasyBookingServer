package es.deusto.ingenieria.sd.easyB.server.services;


public class ReservaVuelosService {
	
	private static ReservaVuelosService instance;

	private ReservaVuelosService() { }
	
	public static ReservaVuelosService getInstance() {
		if (instance == null) {
			instance = new ReservaVuelosService();
		}

		return instance;
	}
	public synchronized void createReservaVuelos() {
		
	}
	
	public synchronized void deletereservaVuelos() {
		
	}
	
	
}
