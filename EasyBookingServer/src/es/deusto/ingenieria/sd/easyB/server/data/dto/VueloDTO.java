package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.io.Serializable;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;

public class VueloDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cod_vuelo;
	private Date llegada; 
	private Date salida;
	private double precio;
	private AeropuertoDTO aero_origen;
	private AeropuertoDTO aero_destino;
	
	public AeropuertoDTO getAero_origen() {
		return aero_origen;
	}
	public void setAero_origen(AeropuertoDTO aero_origen) {
		this.aero_origen = aero_origen;
	}
	public AeropuertoDTO getAero_destino() {
		return aero_destino;
	}
	public void setAero_destino(AeropuertoDTO aero_destino) {
		this.aero_destino = aero_destino;
	}
	public String getCod_vuelo() {
		return cod_vuelo;
	}
	public void setCod_vuelo(String codVuelo) {
		this.cod_vuelo = codVuelo;
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
