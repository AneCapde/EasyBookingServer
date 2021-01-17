package es.deusto.ingenieria.sd.easyB.server.services;

import java.util.List;
import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea;

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
	
	//Este metodo no tenia claro lo que tiene que obtener
	@SuppressWarnings("unchecked")
	public List<AerolineaDTO> getAerolineas(Aerolinea aerolineas) {
		//return AerolineaAssembler.getInstance().entityToDTO(FactGatewayAerolinea.getInstance().createGateway(TipoAerolineas.Vueling));
		return (List<AerolineaDTO>) AerolineaAssembler.getInstance().entityToDTO(aerolineas);
	}
}
