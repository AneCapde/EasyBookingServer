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

	public Usuario registrarUsuario(String email, String contraseña,SistemaPago tipoPago, Aeropuerto aeroPref) {
		return this.Autorizacion.registrarUsuario(email, contraseña, tipoPago, aeroPref);
//		Usuario user = DBManager.getInstance().getUser(email);
//		
//		if (user != null && user.chekPassword(contraseña)) {
//			return user;
//		} else {
//			return null;
//		}
	}
	
	public Usuario login(String email, String contraseña) {
		return this.Autorizacion.login(email, contraseña);
	}
}