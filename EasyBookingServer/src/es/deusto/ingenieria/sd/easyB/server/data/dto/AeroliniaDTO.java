package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.io.Serializable;

public class AeroliniaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int cod_aero;
	private String nombre;
	
	public int getCod_aero() {
		return cod_aero;
	}
	public void setCod_aero(int cod_aero) {
		this.cod_aero = cod_aero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	
	

}
