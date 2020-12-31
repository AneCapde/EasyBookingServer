package es.deusto.ingenieria.sd.easyB.server.jdo;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Vuelo {
	
	private int cod_vuelo;
	@NotPersistent
	private Date llegada; 
	@NotPersistent
	private Date salida;
	@NotPersistent
	private double precio;
	@NotPersistent
	private Aeropuerto origen;
	@NotPersistent
	private Aeropuerto destino;
	@NotPersistent
	private Aerolinea aerolinea;
	@Persistent(defaultFetchGroup = "true", mappedBy = "vuelo", dependentElement = "true")
	@Join
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	
	
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
	public Aeropuerto getOrigen() {
		return origen;
	}
	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}
	public Aeropuerto getDestino() {
		return destino;
	}
	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	public void removeReservas() {
		for (Reserva r : this.reservas) {
			this.reservas.remove(r);
		}
	}
	
}
