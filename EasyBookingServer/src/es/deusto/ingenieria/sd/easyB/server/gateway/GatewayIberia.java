package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import iberia.server.remote.IIberia;
import iberia.server.remote.VueloServ;
import paypal.server.remote.IPayPal;

public class GatewayIberia implements IGatewayAerolinea{
	
	private String ip = "127.0.0.1";
	private int port = 1099;
	private String serviceName = "Iberia";
	private IIberia service;
	
	public void inicializarServicio()  {
		try {
    		String name = "//" + ip + ":" + port + "/" + serviceName;
    		this.service = (IIberia) Naming.lookup(name);
     	} catch (Exception ex) {
    		System.err.println("#ERROR looking up for the remote service: " + ex);
    	}
	}

	@Override
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) throws RemoteException{
		ArrayList<Vuelo> vuelos = new ArrayList<>();
		inicializarServicio();
		try {
			for (VueloServ vueloserv : this.service.buscarVuelos(origen.getNombre(), destino.getNombre(), fecha ,num_pasajeros)) {
				Vuelo v1 = new Vuelo();
				v1.setCod_vuelo(vueloserv.getCod_vuelo());
				for( Aeropuerto ae : DBManager.getInstance().getAeropuertos()) {
					if (ae.getCod_aeropuerto().equals(vueloserv.getDestino())) {
						v1.setDestino(ae);
					}
					if (ae.getCod_aeropuerto().equals(vueloserv.getOrigen())) {
						v1.setOrigen(ae);
					}
				}
				v1.setPrecio(vueloserv.getPrecio());
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setCod_aero("IBE");
				aerolinea.setNombre("Iberia");
				v1.setAerolinea(aerolinea);
				v1.setSalida(vueloserv.getSalida());
				v1.setLlegada(vueloserv.getLlegada());
				vuelos.add(v1);	
			}	
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return vuelos;
	}

	@Override
	public Reserva reservarVuelo(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros)throws RemoteException  {
		inicializarServicio();
		if (this.service.reservarVuelo(vuelo.getCod_vuelo(), num_pasajeros)) {
			Reserva reserva = new Reserva();
			reserva.setFecha(fecha);
			reserva.setImporte(importe);
			reserva.setNombre_pasajeros(nombre_pasajeros);
			reserva.setNumero_asientos(num_pasajeros);
			reserva.setVuelo(vuelo);
			System.out.println("Se ha realizado la reserva correctamente");
		}else {
			System.out.println("No se ha podido realizar la reserva");
		}
		return null;
	}

}
