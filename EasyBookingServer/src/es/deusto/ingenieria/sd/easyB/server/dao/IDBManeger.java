package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Pago;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public interface IDBManeger {
	public void deleteData();
	public void deleteObjectFromDB(Object object);
	public void storeObjectInDB(Object object);
	public void store(Usuario user);
	public void store(Vuelo vuelo);
	public void store(Aeropuerto aeropuerto);
	public void store(Pago pago);
	public void store(Reserva reserva);
	public void store(SistemaPago sistemaPago);
	public Aeropuerto getAeropuerto(String aeropuertoName);
	public void updateAeropuerto(Aeropuerto aeropuerto);
	public List<Aeropuerto> getAeropuertos();
	public ArrayList<Aeropuerto> getAeropuertos(String condition);
	public void deleteAllAeropuertos();
	public Vuelo getVuelo(int cod_vuelo);
	public void updateVuelo(Vuelo vuelo);
	public List<Vuelo> getVuelo();
	public ArrayList<Vuelo> getVuelos(String aeropuertoName);
	public void deleteAllVuelos();
	public Reserva getReserva(String nombrePasajero);
	public void updateReserva(Reserva reserva);
	public List<Reserva> getReservas();
	public ArrayList<Reserva> getReservas(String condition);
	public void deleteAllReservas();
	public Usuario getUser(String email);
	public void updateUsuario(Usuario usuario);
	public List<Usuario> getUsers();
	public void deleteAllUsuarios();
	public Pago getPago(Date fecha);
	public void updatePago(Pago pago);
	public List<Pago> getPagos();
	public ArrayList<Pago> getPagos(String condition);
	public void deleteAllPagos();
	public SistemaPago getSistemaPago(Date fecha);
	public void updatePago(SistemaPago sistemaPago);
	public List<SistemaPago> getSistemaPagos();
	public ArrayList<SistemaPago> getSistemaPagos(String condition);
	public void deleteAllSistemaPagos();
}
