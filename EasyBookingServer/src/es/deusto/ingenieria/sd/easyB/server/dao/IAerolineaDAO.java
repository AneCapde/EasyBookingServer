package es.deusto.ingenieria.sd.easyB.server.dao;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;


public interface IAerolineaDAO {
	public void storeAerolinea(Aerolinea aerolinea);
	public Aerolinea getAerolinea(int cod_aerolinea); 
	public void updateAerolinea(Aerolinea aerolinea);
	public ArrayList<Aerolinea> getAerolineas();
	public void deleteAllAerolineas(); 
}
