package es.deusto.ingenieria.sd.easyB.server.gateway;

import es.deusto.ingenieria.sd.easyB.server.data.Paypal;

public interface IGatewayPago {
	
	public Paypal realizarPago(String email, int cantidad);
}
