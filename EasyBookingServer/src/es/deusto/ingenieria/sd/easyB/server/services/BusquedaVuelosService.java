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
	private IGatewayAerolinea Aerolinea;

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
	

	//Este metodo no tenia claro lo que tiene que obtener da problemas
	@SuppressWarnings("unchecked")
	public List<AerolineaDTO> getAerolineas(Aerolinea aerolineas) {
		//return AerolineaAssembler.getInstance().entityToDTO(FactGatewayAerolinea.getInstance().createGateway(TipoAerolineas.Vueling));
		return (List<AerolineaDTO>) AerolineaAssembler.getInstance().entityToDTO(aerolineas);
	}
	
	public ArrayList<VueloDTO> buscarVuelos(Vuelo vuelo, Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) {
//		return this.Aerolinea.buscarVuelos(origen, destino, fecha, num_pasajeros);	
		TipoAerolineas aerolinea = TipoAerolineas.valueOf(vuelo.getAerolinea().getNombre()); 
		return FactGatewayAerolinea.getInstance().createGateway(aerolinea).buscarVuelos(origen, destino, fecha, num_pasajeros);
	}

}
