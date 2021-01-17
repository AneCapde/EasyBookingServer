package es.deusto.ingenieria.sd.easyB.server.gateway;

public class FactGatewayAerolinea {
	
	public enum TipoAerolineas {Vueling, Iberia}
	
	private FactGatewayAerolinea() {
		
	}
	
	public IGatewayAerolinea createGateway(TipoAerolineas tipoAerolinea) {
		IGatewayAerolinea gatewayaerolinea = null;
		
		if (TipoAerolineas.Vueling.equals(tipoAerolinea)) {
			gatewayaerolinea = new GatewayVueling();
		}else {
			gatewayaerolinea = new GatewayIberia();
		}
		return gatewayaerolinea;
	}
}
