package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public interface IEasyBookingRemoteFacade extends Remote {
	public boolean login(String aEmail, String aPassword) throws RemoteException;
	public List<VueloDTO> getVuelos(String aeropuertoName) throws RemoteException;
	public List<AerolineaDTO> getAerolineas(Aerolinea aerolinea) throws RemoteException;
	public List<AeropuertoDTO> getAeropuertos() throws RemoteException;
}
