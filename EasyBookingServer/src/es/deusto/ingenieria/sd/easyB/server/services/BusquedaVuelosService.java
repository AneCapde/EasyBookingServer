package es.deusto.ingenieria.sd.easyB.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea.TipoAerolineas;

public class BusquedaVuelosService {
	
	private static BusquedaVuelosService instance;

	private BusquedaVuelosService() { }
	
	public static BusquedaVuelosService getInstance() {
		if (instance == null) {
			instance = new BusquedaVuelosService();
		}

		return instance;
	}
	
	public List<AeropuertoDTO> getAeropuertos() {
		return AeropuertoAssembler.getInstance().entityToDTO(DBManager.getInstance().getAeropuertos());
	}
		
	public ArrayList<VueloDTO> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) {	
		ArrayList<VueloDTO> vuelos = new ArrayList<>();
		ArrayList<VueloDTO> vuelos_server = new ArrayList<>();
		for (TipoAerolineas tipo : TipoAerolineas.values()) {
			vuelos_server = (ArrayList<VueloDTO>) VueloAssembler.getInstance().entityToDTO(FactGatewayAerolinea.getInstance().createGateway(tipo).buscarVuelos(origen, destino, fecha, num_pasajeros));
			for (VueloDTO vD : vuelos_server) {
				vuelos.add(vD);
			}
		}
		return vuelos;
	}

}
