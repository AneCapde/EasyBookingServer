package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.jdo.Vuelo;

public interface IVueloDAO {
	
	public void storeVuelo(Vuelo vuelo);
	public Vuelo getVuelo(int cod_vuelo);
	public void updateVuelo(Vuelo vuelo);
	public ArrayList<Vuelo> getVuelos();
	public void deleteAllVuelos();

}
