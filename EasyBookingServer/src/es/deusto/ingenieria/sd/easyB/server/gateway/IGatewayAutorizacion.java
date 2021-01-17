package es.deusto.ingenieria.sd.easyB.server.gateway;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.SistemaPago;
import es.deusto.ingenieria.sd.easyB.server.data.Usuario;

public interface IGatewayAutorizacion {
	//un metodo por cada funcionalidad que nos ofrezca el servicio externo
	//public static void getInstance() {}
	public Usuario registrarUsuario(String email, String password, SistemaPago tipoPago, Aeropuerto aeroPref);
	public Usuario login(String email, String password);

}
