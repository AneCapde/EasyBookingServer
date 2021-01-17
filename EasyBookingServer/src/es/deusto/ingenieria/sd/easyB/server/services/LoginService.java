package es.deusto.ingenieria.sd.easyB.server.services;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayAutorizacion;

public class LoginService {
	private static LoginService instance;
	private IGatewayAutorizacion Autorizacion;
	
	private LoginService() { }
	
	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}

	public Usuario registrarUsuario(String email, String contraseņa, String tipoPago, String aeroPref) {
		return this.Autorizacion.registrarUsuario(email, contraseņa);
//		Usuario user = DBManager.getInstance().getUser(email);
//		
//		if (user != null && user.chekPassword(contraseņa)) {
//			return user;
//		} else {
//			return null;
//		}
	}
	
	public Usuario login(String email, String contraseņa) {
		return this.Autorizacion.login(email, contraseņa);
	}
}