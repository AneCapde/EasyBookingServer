package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public interface IGatewayAerolinea {
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros);
	public Reserva reservarVuelo(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros)throws RemoteException;
};
	

