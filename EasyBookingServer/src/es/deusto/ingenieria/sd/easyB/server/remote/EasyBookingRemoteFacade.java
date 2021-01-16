package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.easyB.server.services.BusquedaVuelosService;
import es.deusto.ingenieria.sd.easyB.server.services.LoginService;

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

	public boolean login(String email, String password) {
		System.out.println(" * RemoteFaçade login: " + email + " / " + password);
		this.state = LoginService.getInstance().login(email, password);
		
		return state != null;
	}
	
	public List<VueloDTO> getVuelos() {
		System.out.println(" * RemoteFaçade getVuelos");
		return BusquedaVuelosService.getInstance().getVuelos();
	}

	public List<AerolineaDTO> getAerolineas() {
		System.out.println(" * RemoteFaçade getAerolienas: ");
		return BusquedaVuelosService.getInstance().getAerolineas();
	}
	
	public List<AeropuertoDTO> getAerolpuertos() {
		System.out.println(" * RemoteFaçade getAeropuertos: ");
		return BusquedaVuelosService.getInstance().getAeropuertos();
	}

}
