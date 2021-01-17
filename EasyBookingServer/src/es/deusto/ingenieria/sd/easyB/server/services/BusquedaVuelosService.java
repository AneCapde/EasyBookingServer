package es.deusto.ingenieria.sd.easyB.server.services;

import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.dao.AerolineaDAO;
import es.deusto.ingenieria.sd.easyB.server.dao.AeropuertoDAO;
import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.dao.VueloDAO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayAerolinea;

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
	
	public List<VueloDTO> getVuelos(String aeropuertoName) {
		return VueloAssembler.getInstance().entityToDTO(DBManager.getInstance().getVuelos(aeropuertoName));
	}
	
	public List<AerolineaDTO> getAerolineas(String tipoAerolinea) {
		return AerolineaAssembler.getInstance().entityToDTO(FactGatewayAerolinea.);
	}

	//Cambiar esto para hacerlo con el DBManager, y la aerolinea cojer el enum del factoryAerolinea
}
