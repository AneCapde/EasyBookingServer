package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Pago;

public interface IPagoDAO {
	
	public void storePago(Pago pago);
	public Pago getPago(Date fecha); //este creo que hay que eliminarlo
	public void updatePago(Pago pago);
	public ArrayList<Pago> getPagos();
	public void deleteAllPagos();

}
