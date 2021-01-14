package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.io.Serializable;

public class AeropuertoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
