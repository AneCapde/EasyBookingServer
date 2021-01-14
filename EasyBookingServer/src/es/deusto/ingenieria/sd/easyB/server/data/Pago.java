package es.deusto.ingenieria.sd.easyB.server.data;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Pago {
	
	private Date fecha;

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
