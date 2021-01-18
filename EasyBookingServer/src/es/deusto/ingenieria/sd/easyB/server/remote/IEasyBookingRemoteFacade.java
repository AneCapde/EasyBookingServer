package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AerolineaDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.AeropuertoDTO;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public interface IEasyBookingRemoteFacade extends Remote {
	
	public boolean login(String aEmail, String aPassword) throws RemoteException;
	public boolean registrarUsuario(String email, String password, String tipoPago, String aeroPref) throws RemoteException;
	//public ArrayList<AerolineaDTO> getAerolineas() throws RemoteException;
	public ArrayList<AeropuertoDTO> getAeropuertos() throws RemoteException;
	
	//Hay que cambiar este método
	public ArrayList<VueloDTO> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) throws RemoteException;
	//Hay que cambiar este método
	public boolean reservaVuelos(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) throws RemoteException;
	
	public boolean realizarPago(String email, String password, double cantidad) throws RemoteException;
}
