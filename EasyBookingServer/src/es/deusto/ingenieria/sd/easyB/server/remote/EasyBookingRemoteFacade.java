package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
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
	
	public List<AeropuertoDTO> getAerolpuertos() throws RemoteException{
		System.out.println(" * RemoteFaçade getAeropuertos: ");
		return BusquedaVuelosService.getInstance().getAeropuertos();
	}

	@Override
	public List<VueloDTO> getVuelos() throws RemoteException {
		System.out.println(" * RemoteFaçade getVuelos");
		return ReservaVuelosService.getInstance().getVuelos();
	}

	@Override
	public List<AerolineaDTO> getAerolineas() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
