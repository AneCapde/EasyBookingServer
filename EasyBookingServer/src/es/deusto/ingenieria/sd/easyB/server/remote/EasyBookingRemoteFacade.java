package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloAssembler;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.services.BusquedaVuelosService;
import es.deusto.ingenieria.sd.easyB.server.services.LoginService;
import es.deusto.ingenieria.sd.easyB.server.services.ReservaVuelosService;

// <<Singletone>> Implemeta la interface IEasyBookingRemoteFacade
public class EasyBookingRemoteFacade extends UnicastRemoteObject implements IEasyBookingRemoteFacade{

	private static final long serialVersionUID = 1L;
	private static EasyBookingRemoteFacade instance;
	public Usuario state;
	
	private EasyBookingRemoteFacade() throws RemoteException {
		super();		
	}
	
	// Lazy Initialization
	public static IEasyBookingRemoteFacade getInstance() {
		if (instance == null) {
			try {
				instance = new EasyBookingRemoteFacade();
			} catch (Exception ex) {
				System.err.println("# Error creating EasyBookingRemoteFacade: " + ex);
			}
		}	
		return instance;
	}

	public boolean registrarUsuario(String email, String password, String tipoPago, String aeroPref) throws RemoteException{
		System.out.println(" * RemoteFaçade registro: " + email + " / " + password + " / " + tipoPago + " / " + aeroPref);
		this.state = LoginService.getInstance().registrarUsuario(email, password, tipoPago, aeroPref);
		
		return state != null;
	}
	
	public boolean login(String email, String password) throws RemoteException{
		System.out.println(" * RemoteFaçade login: " + email + " / " + password);
		this.state = LoginService.getInstance().login(email, password);
		
		return state != null;
	}
	
	public List<VueloDTO> getVuelos(String aeropuertoName) throws RemoteException{
		System.out.println(" * RemoteFaçade getVuelos");
		return BusquedaVuelosService.getInstance().getVuelos(aeropuertoName);
	}

	public List<AerolineaDTO> getAerolineas(Aerolinea aerolinea) throws RemoteException{
		System.out.println(" * RemoteFaçade getAerolienas: ");
		return BusquedaVuelosService.getInstance().getAerolineas(aerolinea);
	}
	
	public List<AeropuertoDTO> getAeropuertos() throws RemoteException{
		System.out.println(" * RemoteFaçade getAeropuertos: ");
		return BusquedaVuelosService.getInstance().getAeropuertos();
	}

	@Override
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) throws RemoteException {
		System.out.println(" * RemoteFaçade buscarVuelos: ");
		//VueloAssembler.getInstance().entityToDTO(BusquedaVuelosService.getInstance().buscarVuelos(origen, destino, fecha, num_pasajeros));
		return null;
	}

	@Override
	public boolean reservaVuelos(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) throws RemoteException {
		System.out.println(" * RemoteFaçade reservaVuelos: ");
		ReservaVuelosService.getInstance().reservaVuelos(vuelo, importe, num_pasajeros, fecha, nombre_pasajeros);
		return true;
	}


}
