package es.deusto.ingenieria.sd.easyB.server.gateway;

public class FactGatewayAerolinea {
	
	public enum TipoAerolineas {Vueling, Iberia}
	
	private static FactGatewayAerolinea instance;
	
	private FactGatewayAerolinea() {}
	
	public static FactGatewayAerolinea getInstance() {
		if (instance == null) {
			instance = new FactGatewayAerolinea();
		}

		return instance;
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
