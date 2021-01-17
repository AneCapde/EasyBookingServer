package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import iberia.server.remote.IIberia;
import paypal.server.remote.IPayPal;

public class GatewayIberia implements IGatewayAerolinea{
	
	private String ip = "127.0.0.1";
	private int port = 2000;
	private String serviceName = "Iberia";
	private IIberia service;
	
	public GatewayIberia() {
		try {
    		String name = "//" + ip + ":" + port + "/" + serviceName;
    		this.service = (IIberia) Naming.lookup(name);
     	} catch (Exception ex) {
    		System.err.println("#ERROR looking up for the remote service: " + ex);
    	}
	}

	@Override
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva reservarVuelo(Vuelo vuelo) {
		// TODO Auto-generated method stub
		return null;
	}

}
