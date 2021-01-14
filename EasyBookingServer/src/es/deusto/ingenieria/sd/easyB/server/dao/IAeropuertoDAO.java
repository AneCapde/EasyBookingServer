package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;

public interface IAeropuertoDAO { 
	
	public void storeAeropuerto(Aeropuerto aeropuerto);
	public Aeropuerto getAeropuerto(int cod_aeropuerto); 
	public void updateAeropuerto(Aeropuerto aeropuerto);
	public ArrayList<Aeropuerto> getAeropuertos();
	public void deleteAllAeropuertos(); 
	
}
