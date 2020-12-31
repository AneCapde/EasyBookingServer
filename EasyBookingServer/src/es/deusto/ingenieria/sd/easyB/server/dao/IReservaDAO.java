package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.jdo.Reserva;

public interface IReservaDAO {
	
	public void storeReserva(Reserva reserva);
	public Reserva getReserva(String nombre);//yo creo que hay eliminar este metodo
	public void updateReserva(Reserva reserva);
	public ArrayList<Reserva> getReservas();
	public void deleteAllReservas();

}
