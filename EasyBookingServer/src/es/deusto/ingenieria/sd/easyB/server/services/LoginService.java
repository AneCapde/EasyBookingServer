package es.deusto.ingenieria.sd.easyB.server.services;

import es.deusto.ingenieria.sd.easyB.server.dao.UsuarioDAO;
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

	public Usuario login(String email, String contrase�a) {
		Usuario user = UsuarioDAO.getInstance().getUsuario(email);
		
		if (user != null && user.chekPassword(contrase�a)) {
			return user;
		} else {
			return null;
		}
	}
}
