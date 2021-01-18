package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.RemoteException;

public interface IGatewayPago {
	
	public boolean realizarPago(String email, String password, double cantidad) throws RemoteException;
}
