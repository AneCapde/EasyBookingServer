package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import iberia.server.remote.IIberia;
import paypal.server.remote.IPayPal;

public class GatewayIberia implements IGatewayAerolinea{
	
	private String ip = "127.0.0.1";
	private int port = 2001;
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
	public ArrayList<VueloDTO> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva reservarVuelo(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros)throws RemoteException  {
		Reserva reserva = null;
		if (this.service.reservarVuelo(vuelo.getCod_vuelo())) {
			reserva.setFecha(fecha);
			reserva.setImporte(importe);
			reserva.setNombre_pasajeros(nombre_pasajeros);
			reserva.setNumero_asientos(num_pasajeros);
			reserva.setVuelo(vuelo);
			System.out.println("Se ha realizado la reserva correctamente");
		}else {
			System.out.println("No se ha podido realizar la reserva");
		}
		return reserva;
	}

}
