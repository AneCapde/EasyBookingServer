package es.deusto.ingenieria.sd.easyB.server.services;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaAutorizacion;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.GatewayGoogle;

public class LoginService {
	private static LoginService instance;
	
	private LoginService() { }
	
	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}

	public boolean registrarUsuario(String email, String password, String tipoPago, String aeroPref) {
		try {
			System.out.println(" * AppService Registrando Usuario: (+ " + email+ ")");
			if (GatewayGoogle.getInstance().registrarUsuario(email, password)) {
				Usuario u = new Usuario();
				u.setEmail(email);
				String nombre = email.substring( 0, email.indexOf("@"));
				u.setSistemaA(SistemaAutorizacion.GOOGLE);
				u.setNombre(nombre);
				//revisar
				Aeropuerto a = new Aeropuerto();
				a.setCod_aeropuerto(aeroPref);
				u.setAeropuesto(a);
				//revisar
				DBManager.getInstance().store(u);
				return true;
			}else {
				return false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean login(String email, String contraseña) {
		try {
			if (GatewayGoogle.getInstance().login(email, contraseña)) {
				return true;
			}else {
				return false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public List<AeropuertoDTO> getAeropuertos() {
		return AeropuertoAssembler.getInstance().entityToDTO(DBManager.getInstance().getAeropuertos());
	}
}