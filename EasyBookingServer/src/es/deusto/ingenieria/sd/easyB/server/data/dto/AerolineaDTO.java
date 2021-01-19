package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.io.Serializable;

public class AerolineaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cod_aero;
	private String nombre;
	//private ArrayList<VueloDTO> vuelos = new ArrayList<>();
	
	public String getCod_aero() {
		return cod_aero;
	}
	public void setCod_aero(String cod_aero) {
		this.cod_aero = cod_aero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
//	public ArrayList<VueloDTO> getVuelos() {
//		return vuelos;
//	}
//	public void setVuelos(ArrayList<VueloDTO> vuelos) {
//		this.vuelos = vuelos;
//	}
	
	

}
