package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.jdo.SistemaPago;

public interface ISistemaPagoDAO {
	
	public void storeSistemaPago(SistemaPago sistemaPago);
	public SistemaPago getSistemaPago(Date fecha); //creo que este hay que quitarle
	public void updatePago(SistemaPago sistemaPago);
	public ArrayList<SistemaPago> getSistemaPagos();
	public void deleteAllSistemaPagos();

}
