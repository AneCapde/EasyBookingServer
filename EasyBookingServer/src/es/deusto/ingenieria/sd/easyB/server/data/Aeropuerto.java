package es.deusto.ingenieria.sd.easyB.server.data;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Aeropuerto {

	private String cod_aeropuerto;
	private String nombre;
	private Set<Aeropuerto> aeropuerto;
	
	
	public String getCod_aeropuerto() {
		return cod_aeropuerto;
	}
	public void setCod_aeropuerto(String cod_aeropuerto) {
		this.cod_aeropuerto = cod_aeropuerto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void add(Aeropuerto aeropuerto) {
		if (aeropuerto != null && !this.aeropuerto.contains(aeropuerto)) {
			this.aeropuerto.add(aeropuerto);
		}
		
	}
	
	

}
