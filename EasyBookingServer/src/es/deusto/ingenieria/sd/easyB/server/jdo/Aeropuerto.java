package es.deusto.ingenieria.sd.easyB.server.jdo;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Aeropuerto {

	private int cod_aeropuerto;
	private String nombre;
	
	
	public int getCod_aeropuerto() {
		return cod_aeropuerto;
	}
	public void setCod_aeropuerto(int cod_aeropuerto) {
		this.cod_aeropuerto = cod_aeropuerto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
