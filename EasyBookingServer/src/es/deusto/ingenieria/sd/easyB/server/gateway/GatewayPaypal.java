package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;
import paypal.server.remote.IPayPal;


public class GatewayPaypal implements IGatewayPago{
	
	private String ip = "127.0.0.1";
	private int port = 1099;
	private String serviceName = "PayPal";
	private IPayPal service;
	private static IGatewayPago instance;
	
	public GatewayPaypal() {
		try {
    		String name = "//" + ip + ":" + port + "/" + serviceName;
    		this.service = (IPayPal) Naming.lookup(name);
     	} catch (Exception ex) {
    		System.err.println("#ERROR looking up for the remote service: " + ex);
    	}
	}
	
	public static IGatewayPago getIntance() {
		if (instance == null) {
			instance = new GatewayPaypal();
		}
		return instance;
	}
	

	@Override
	public boolean realizarPago(String email, String password, double cantidad) throws RemoteException {
		if (this.service.pagar(email, password, cantidad)) {
			System.out.println("Se ha realizado el pago correctamente");
			return true;
		}else {
			System.out.println("No se ha podido realizar el pago");
			return false;
		}
	}

}
