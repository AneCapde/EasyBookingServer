package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public interface IEasyBookingRemoteFacade extends Remote {
	
	public boolean login(String aEmail, String aPassword) throws RemoteException;
	public boolean registrarUsuario(String email, String password, String tipoPago, String aeroPref) throws RemoteException;
	//public ArrayList<AerolineaDTO> getAerolineas() throws RemoteException;
	public ArrayList<AeropuertoDTO> getAeropuertos() throws RemoteException;
	public ArrayList<VueloDTO> buscarVuelos(AeropuertoDTO origenDTO, AeropuertoDTO destinoDTO, Date fecha, int num_pasajeros) throws RemoteException;
	public boolean reservaVuelos(VueloDTO vueloDTO, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) throws RemoteException;
	public boolean realizarPago(String email, String password, double cantidad) throws RemoteException;
}
