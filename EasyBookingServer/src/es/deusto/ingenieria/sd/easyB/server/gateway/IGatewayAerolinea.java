package es.deusto.ingenieria.sd.easyB.server.gateway;

import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public interface IGatewayAerolinea {
	public Vuelo buscarVuelos();
	public Vuelo reservarVuelo(int cod_vuelo);
}
