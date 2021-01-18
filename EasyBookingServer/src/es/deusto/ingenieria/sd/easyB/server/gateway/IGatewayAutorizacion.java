package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.RemoteException;

public interface IGatewayAutorizacion {	
	public boolean registrarUsuario(String email, String password) throws RemoteException;
	public boolean login(String email, String password) throws RemoteException;
}
