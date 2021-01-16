package es.deusto.ingenieria.sd.easyB.server.gateway;

import es.deusto.ingenieria.sd.easyB.server.data.Usuario;

public interface IGatewayAutorizacion {
	//un metodo por cada funcionalidad que nos ofrezca el servicio externo
	public Usuario registrarUsuario(String nombre);
	public Usuario login(String email, String password);

}
