package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public interface IEasyBookingRemoteFacade extends Remote {
	public boolean login(String aEmail, String aPassword) throws RemoteException;
	public boolean registrarUsuario(String email, String password, String tipoPago, String aeroPref) throws RemoteException;
	public List<VueloDTO> getVuelos(String aeropuertoName) throws RemoteException;
	public List<AerolineaDTO> getAerolineas(Aerolinea aerolinea) throws RemoteException;
	public List<AeropuertoDTO> getAeropuertos() throws RemoteException;
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) throws RemoteException;
	public boolean reservaVuelos(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) throws RemoteException;

	
}
