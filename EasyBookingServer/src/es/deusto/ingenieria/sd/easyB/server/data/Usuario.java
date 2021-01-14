package es.deusto.ingenieria.sd.easyB.server.data;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable(detachable = "true")
public class Usuario {
	
	private String nombre;
	private String email;
	@Persistent(defaultFetchGroup = "true")
	private Aeropuerto aeropuesto;
	@Persistent(defaultFetchGroup = "true", mappedBy = "usuario", dependentElement = "true")
	@Join
	private ArrayList<Reserva> reservas = new ArrayList<>(); 
	private SistemaAutorizacion sistemaA;
	@Persistent(defaultFetchGroup = "true")
	private SistemaPago sistemaPago;
	
		
	public SistemaAutorizacion getSistemaA() {
		return sistemaA;
	}
	public void setSistemaA(SistemaAutorizacion sistemaA) {
		this.sistemaA = sistemaA;
	}
	public SistemaPago getSistemaPago() {
		return sistemaPago;
	}
	public void setSistemaPago(SistemaPago sistemaPago) {
		this.sistemaPago = sistemaPago;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Aeropuerto getAeropuesto() {
		return aeropuesto;
	}
	public void setAeropuesto(Aeropuerto aeropuesto) {
		this.aeropuesto = aeropuesto;
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
