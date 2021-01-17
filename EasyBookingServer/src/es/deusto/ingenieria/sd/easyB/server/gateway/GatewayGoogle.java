package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;


import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import google.server.remote.IGoogle;

public class GatewayGoogle implements IGatewayAutorizacion{

	private String ip = "127.0.0.1";
	private int port = 2000;
	private String serviceName = "Google";
	private IGoogle service;
	
	public GatewayGoogle() {
		try {
			String name = "//" + ip + ":" + port + "/" + serviceName;
			this.service = (IGoogle) Naming.lookup(name);
     	} catch (Exception ex) {
    		System.err.println("#ERROR looking up for the remote service: " + ex);
    	}
	}
	
	//DEVOLVER USUARIO?
	@Override
	public Usuario login(String email, String password) {
		Usuario usuario = null;
		try {
			if (this.service.login(email, password)) {
				usuario = new Usuario();
				System.out.println("Se ha realizado el login correctamente");
			}else {
				System.out.println("No se ha podido realizar el login correctamente");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	@Override
	public Usuario registrarUsuario(String email, String password) {
		Usuario usuario = null;
		try {
			if (this.service.registrarUsuario(email, password)) {
				usuario = new Usuario();
				System.out.println("Se ha realizado el registro correctamente");
			}else {
				System.out.println("No se ha podido realizar el registro correctamente");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return usuario;
	
	}


}
