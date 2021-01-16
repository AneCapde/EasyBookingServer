package es.deusto.ingenieria.sd.easyB.server.services;

import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.dao.AerolineaDAO;
import es.deusto.ingenieria.sd.easyB.server.dao.AeropuertoDAO;
import es.deusto.ingenieria.sd.easyB.server.dao.VueloDAO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public class BusquedaVuelosService {
	
	private static BusquedaVuelosService instance;

	private BusquedaVuelosService() { }
	
	public static BusquedaVuelosService getInstance() {
		if (instance == null) {
			instance = new BusquedaVuelosService();
		}

		return instance;
	}
	
	public List<VueloDTO> getVuelos() {
		return VueloAssembler.getInstance().entityToDTO(VueloDAO.getInstance().getVuelos());
	}
	
	public List<AeropuertoDTO> getAeropuertos() {
		return AeropuertoAssembler.getInstance().entityToDTO(AeropuertoDAO.getInstance().getAeropuertos());
	}
	
	public List<AerolineaDTO> getAerolineas() {
		return AerolineaAssembler.getInstance().entityToDTO(AerolineaDAO.getInstance().getAerolineas());
	}

}
