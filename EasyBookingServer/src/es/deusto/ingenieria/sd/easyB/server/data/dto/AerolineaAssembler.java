package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;

public class AerolineaAssembler {

	private static AerolineaAssembler instance;
	
	private AerolineaAssembler() { }
	
	public static AerolineaAssembler getInstance() {
		if (instance == null) {
			instance = new AerolineaAssembler();
		}
		return instance;
	}
	
	public AerolineaDTO entityToDTO(Aerolinea aerolinea) {
		AerolineaDTO dto = new AerolineaDTO();
		
		dto.setCod_aero(aerolinea.getCod_aero());
		dto.setNombre(aerolinea.getNombre());
				
		return dto;
	}
	
	public ArrayList<AerolineaDTO> entityToDTO(ArrayList<Aerolinea> aerolineas) {
		ArrayList<AerolineaDTO> dtos = new ArrayList<>();
		for (Aerolinea aerolinea : aerolineas) {
			dtos.add(this.entityToDTO(aerolinea));
		}
		return dtos;		
	}

}
