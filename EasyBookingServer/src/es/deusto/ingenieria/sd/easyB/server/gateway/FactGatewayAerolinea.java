package es.deusto.ingenieria.sd.easyB.server.gateway;

public class FactGatewayAerolinea {
	
	//esto creo que se puede borrar
	//private static final FactGatewayAerolinea INSTANCE = new FactGatewayAerolinea();
	public enum TipoAerolineas {Vueling, Iberia}
	
	private FactGatewayAerolinea() {
		
	}
	
	public IGatewayAerolinea createGateway(TipoAerolineas tipoAerolinea) {
		
		if (TipoAerolineas.Vueling.equals(tipoAerolinea)) {
			
		}else {
			
		}
		return null;
	}
}
