package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.easyB.server.services.LoginService;
import google.server.remote.IGoogle;

public class GatewayGoogle implements IGatewayAutorizacion{

	private String ip = "127.0.0.1";
	private int port = 1099;
	private String serviceName = "Google";
	private IGoogle service;
	private static IGatewayAutorizacion instance;
	
	public GatewayGoogle() {
		try {
			String name = "//" + ip + ":" + port + "/" + serviceName;
			this.service = (IGoogle) Naming.lookup(name);
     	} catch (Exception ex) {
    		System.err.println("#ERROR looking up for the remote service: " + ex);
    	}
	}
	
	public static IGatewayAutorizacion getInstance() {
		if (instance == null) {
			instance = new GatewayGoogle();
		}
		return instance;
	}
	
	@Override
	public boolean login(String email, String password) {
		//this.inicializarServicio();
		try {
			if (this.service.login(email, password)) {
				System.out.println("Se ha realizado el login correctamente");
				return true;
			}else {
				System.out.println("No se ha podido realizar el login correctamente");
				return false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean registrarUsuario(String email, String password) {
		//this.inicializarServicio();
		try {
			if (this.service.registrarUsuario(email, password)) {
				System.out.println("Se ha realizado el registro correctamente");
				return true;
			}else {
				System.out.println("No se ha podido realizar el registro correctamente");
				return false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
}
