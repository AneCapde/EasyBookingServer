package es.deusto.ingenieria.sd.easyB.server.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.multi.MultiPopupMenuUI;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Pago;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayAerolinea;
import es.deusto.ingenieria.sd.easyB.server.gateway.IGatewayPago;
import es.deusto.ingenieria.sd.easyB.server.gateway.FactGatewayAerolinea.TipoAerolineas;
import es.deusto.ingenieria.sd.easyB.server.gateway.GatewayPaypal;

public class ReservaVuelosService {
	
	private static ReservaVuelosService instance;

	private ReservaVuelosService() { }
	
	public static ReservaVuelosService getInstance() {
		if (instance == null) {
			instance = new ReservaVuelosService();
		}

		return instance;
	}
	
	public boolean reservaVuelos(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) {
		TipoAerolineas aerolinea = TipoAerolineas.valueOf(vuelo.getAerolinea().getNombre()); 
		try {
			System.out.println(vuelo.toString());
			for (String s : nombre_pasajeros) {
				System.out.println(s);		
			}
			Reserva r = new Reserva();
			r = FactGatewayAerolinea.getInstance().createGateway(aerolinea).reservarVuelo(vuelo, importe, num_pasajeros, fecha, nombre_pasajeros);
			r.setVuelo(vuelo);
			Pago p = new Pago();
			r.setPago(p);
			//DBManager.getInstance().store(r);
			return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean realizarPago(String email, String password, double cantidad) {
		ArrayList<Reserva> reservas = new ArrayList<>();
		try {
			if (GatewayPaypal.getIntance().realizarPago(email, password, cantidad)) {
				reservas = (ArrayList<Reserva>) DBManager.getInstance().getReservas();
				for (Reserva r : reservas) {
					if (r.getImporte() == cantidad) {
						Date fecha = java.util.Calendar.getInstance().getTime();
						r.getPago().setFecha(fecha);
						DBManager.getInstance().updatePago(r.getPago());
					}
				}
				return true;
			}else {
				//delete reserva from database in case payment is not done properly
				return false;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public List<AeropuertoDTO> getAeropuertos() {
		return AeropuertoAssembler.getInstance().entityToDTO(DBManager.getInstance().getAeropuertos());
	}
	
	
}
