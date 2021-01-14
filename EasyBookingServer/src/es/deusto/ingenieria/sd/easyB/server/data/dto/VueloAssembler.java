package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.jdo.Vuelo;

public class VueloAssembler {
	
	private static VueloAssembler instance;
	
	private VueloAssembler() { }
	
	public static VueloAssembler getInstance() {
		if (instance == null) {
			instance = new VueloAssembler();
		}
		return instance;
	}
	
	public VueloDTO entityToDTO(Vuelo vuelo) {
		VueloDTO dto = new VueloDTO();
		
		dto.setCod_vuelo(vuelo.getCod_vuelo());
		dto.setLlegada(vuelo.getLlegada());
		dto.setSalida(vuelo.getSalida());
		dto.setPrecio(vuelo.getPrecio());
				
		return dto;
	}
	
	public ArrayList<VueloDTO> entityToDTO(ArrayList<Vuelo> vuelos) {
		ArrayList<VueloDTO> dtos = new ArrayList<>();
		for (Vuelo vuelo : vuelos) {
			dtos.add(this.entityToDTO(vuelo));
		}
		return dtos;		
	}
	
}
