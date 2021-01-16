package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public interface IEasyBookingRemoteFacade extends Remote {
	public boolean login(String aEmail, String aPassword) throws RemoteException;
	public List<VueloDTO> getVuelos() throws RemoteException;
	public List<AerolineaDTO> getAerolineas() throws RemoteException;
	public List<AeropuertoDTO> getAerolpuertos() throws RemoteException;
}
