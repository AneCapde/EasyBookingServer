package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
//import google.server.remote.IGoogle;

public class GatewayGoogle implements IGatewayAutorizacion{

	private static String ip;
	private static String port;
	private static String serviceName;

	@Override
	public Usuario login(String email, String password) {
		
		try {
			String name = "//" + ip + ":" + port + "/" + serviceName;
//			IGoogle stubServer = (IGoogle) java.rmi.Naming.lookup(name);
//			stubServer.login(email,password);
//			System.out.println("* Message coming from the server: '" +  "'");

		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Usuario registrarUsuario(String email, String password, SistemaPago tipoPago, Aeropuerto aeroPref) {
		try {
			String name = "//" + ip + ":" + port + "/" + serviceName;
//			IGoogle stubServer = (IGoogle) java.rmi.Naming.lookup(name);
//			stubServer.registrarUsuario(email,password,tipoPago,aeroPref);
//			System.out.println("* Message coming from the server: '" +  "'");

		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}



}
