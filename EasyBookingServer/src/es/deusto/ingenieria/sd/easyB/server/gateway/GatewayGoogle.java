package es.deusto.ingenieria.sd.easyB.server.gateway;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;

public class GatewayGoogle implements IGatewayAutorizacion{

	private static GatewayGoogle instance = null;

	// Lazy Initialization
	public static IGatewayAutorizacion getInstance() {
		if (instance == null) {
			try {
				instance = new GatewayGoogle();
			} catch (Exception ex) {
				System.err.println("# Error creating GatewayGoogle: " + ex);
			}
		}	
		return instance;
	}

	@Override
	public Usuario login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario registrarUsuario(String email, String password, SistemaPago tipoPago, Aeropuerto aeroPref) {
		// TODO Auto-generated method stub
		return null;
	}



}
