package es.deusto.ingenieria.sd.easyB.server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Reserva {
	
	private ArrayList<String> nombre_pasajeros = new ArrayList<>();
	private int numero_asientos;
	private double importe;
	private Date fecha;
	private Vuelo vuelo; 
	@Persistent(defaultFetchGroup = "true")
	private Pago pago;
	private Usuario usuario;
	private Set<Reserva> reserva;
	
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public ArrayList<String> getNombre_pasajeros() {
		return nombre_pasajeros;
	}
	public void setNombre_pasajeros(ArrayList<String> nombre_pasajeros) {
		this.nombre_pasajeros = nombre_pasajeros;
	}
	public int getNumero_asientos() {
		return numero_asientos;
	}
	public void setNumero_asientos(int numero_asientos) {
		this.numero_asientos = numero_asientos;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void add(Reserva reserva) {
		if (reserva != null && !this.reserva.contains(reserva)) {
			this.reserva.add(reserva);
		}
		
	}
	
	
}
