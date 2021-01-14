package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.jdo.Aeropuerto;

public class AeropuertoAssembler {

	private static AeropuertoAssembler instance;
	
	private AeropuertoAssembler() { }
	
	public static AeropuertoAssembler getInstance() {
		if (instance == null) {
			instance = new AeropuertoAssembler();
		}
		return instance;
	}
	
	public AeropuertoDTO entityToDTO(Aeropuerto aeropuerto) {
		AeropuertoDTO dto = new AeropuertoDTO();
		
		dto.setCod_aeropuerto(aeropuerto.getCod_aeropuerto());
		dto.setNombre(aeropuerto.getNombre());
				
		return dto;
	}
	
	public ArrayList<AeropuertoDTO> entityToDTO(ArrayList<Aeropuerto> aeropuertos) {
		ArrayList<AeropuertoDTO> dtos = new ArrayList<>();
		for (Aeropuerto aeropuerto : aeropuertos) {
			dtos.add(this.entityToDTO(aeropuerto));
		}
		return dtos;		
	}
}
