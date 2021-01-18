package es.deusto.ingenieria.sd.easyB.server.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.multi.MultiPopupMenuUI;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea.TipoAerolineas;

public class ReservaVuelosService {
	
	private static ReservaVuelosService instance;

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
	
	public boolean reservaVuelos(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) {
		TipoAerolineas aerolinea = TipoAerolineas.valueOf(vuelo.getAerolinea().getNombre()); 
		FactGatewayAerolinea.getInstance().createGateway(aerolinea).reservarVuelo(vuelo, importe, num_pasajeros, fecha, nombre_pasajeros);
		return true;
	}
	
	
	
}
