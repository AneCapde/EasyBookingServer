package es.deusto.ingenieria.sd.easyB.server.services;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;

public class LoginService {
	private static LoginService instance;
	
	private LoginService() { }
	
	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}

	public Usuario login(String email, String contraseña) {
		Usuario user = DBManager.getInstance().getUser(email);
		
		if (user != null && user.chekPassword(contraseña)) {
			return user;
		} else {
			return null;
		}
	}
}
//tengo que comunicarme con el gateway de autentificacion, no comprobarlo solo