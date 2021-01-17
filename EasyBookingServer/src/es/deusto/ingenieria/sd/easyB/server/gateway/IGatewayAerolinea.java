package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public interface IGatewayAerolinea {
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros);
	public Reserva reservarVuelo(Vuelo vuelo);
	//public Vuelo recuperarInfo(int cod_vuelo);
}
