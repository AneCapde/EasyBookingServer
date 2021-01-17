package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public interface IGatewayAerolinea {
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros);
	public Vuelo reservarVuelo(int cod_vuelo);
	//public Vuelo recuperarInfo(int cod_vuelo);
}
