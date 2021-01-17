package es.deusto.ingenieria.sd.easyB.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayAerolinea;

public class ReservaVuelosService {
	
	private static ReservaVuelosService instance;
	private IGatewayAerolinea Aerolinea;

	private ReservaVuelosService() { }
	
	public static ReservaVuelosService getInstance() {
		if (instance == null) {
			instance = new ReservaVuelosService();
		}

		return instance;
	}
	
	public List<AeropuertoDTO> getAeropuertos() {
		return AeropuertoAssembler.getInstance().entityToDTO(DBManager.getInstance().getAeropuertos());
	}
	
	public List<VueloDTO> getVuelos(String aeropuertoName) {
		return VueloAssembler.getInstance().entityToDTO(DBManager.getInstance().getVuelos(aeropuertoName));
	}
	
	public Reserva reservaVuelos(Vuelo vuelo) {
		return this.Aerolinea.reservarVuelo(vuelo);
	}
	
	
	
}
