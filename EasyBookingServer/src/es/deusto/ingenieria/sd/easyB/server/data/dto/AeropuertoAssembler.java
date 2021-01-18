package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;

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
	
	public List<AeropuertoDTO> entityToDTO(List<Aeropuerto> aeropuertos) {
		List<AeropuertoDTO> dtos = new ArrayList<>();
		for (Aeropuerto aeropuerto : aeropuertos) {
			dtos.add(this.entityToDTO(aeropuerto));
		}
		return dtos;		
	}
	public Aeropuerto getAeropuerto(AeropuertoDTO aeropuertoDTO) {
		Aeropuerto a1 = new Aeropuerto();
		a1.setCod_aeropuerto(aeropuertoDTO.getCod_aeropuerto());
		a1.setNombre(aeropuertoDTO.getNombre());
		return a1;
	}
}
