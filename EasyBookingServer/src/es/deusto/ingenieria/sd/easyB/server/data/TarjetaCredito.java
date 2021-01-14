package es.deusto.ingenieria.sd.easyB.server.data;

import java.util.Date;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class TarjetaCredito  extends SistemaPago{
	
	private String titular;
	private long num_tarjeta;
	private Date fecha_caducidad;
	
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public long getNum_tarjeta() {
		return num_tarjeta;
	}
	public void setNum_tarjeta(long num_tarjeta) {
		this.num_tarjeta = num_tarjeta;
	}
	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}
	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

}
