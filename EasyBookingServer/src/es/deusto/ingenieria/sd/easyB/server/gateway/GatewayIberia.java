package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public class GatewayIberia implements IGatewayAerolinea{

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
	//comunicación mediante RMI

//	@Override
//	public Vuelo recuperarInfo(int cod_vuelo) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
