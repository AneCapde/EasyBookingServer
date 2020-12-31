package es.deusto.ingenieria.sd.easyB.server.jdo;

import java.util.ArrayList;

public class Aerolinea {

	private int cod_aero;
	private String nombre;	
	private ArrayList<Vuelo> vuelos = new ArrayList<>();
	
	public ArrayList<Vuelo> getVuelos() {
		return vuelos;
	}
	public void setVuelos(ArrayList<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCod_aero() {
		return cod_aero;
	}
	public void setCod_aero(int cod_aero) {
		this.cod_aero = cod_aero;
	}
	
	
}
