package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.io.Serializable;
import java.util.Date;

public class VueloDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int cod_vuelo;
	private Date llegada; 
	private Date salida;
	private double precio;
	
	public int getCod_vuelo() {
		return cod_vuelo;
	}
	public void setCod_vuelo(int cod_vuelo) {
		this.cod_vuelo = cod_vuelo;
	}
	public Date getLlegada() {
		return llegada;
	}
	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}
	public Date getSalida() {
		return salida;
	}
	public void setSalida(Date salida) {
		this.salida = salida;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
