package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

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
		dto.setAero_destino(AeropuertoAssembler.getInstance().entityToDTO(vuelo.getDestino()));
		dto.setAero_origen(AeropuertoAssembler.getInstance().entityToDTO(vuelo.getDestino()));
		dto.setAerolinea(AerolineaAssembler.getInstance().entityToDTO(vuelo.getAerolinea()));	
		
		return dto;
	}
	
	public List<VueloDTO> entityToDTO(List<Vuelo> vuelos) {
		List<VueloDTO> dtos = new ArrayList<>();
		for (Vuelo vuelo : vuelos) {
			dtos.add(this.entityToDTO(vuelo));
		}
		return dtos;		
	}
	public Vuelo getVuelo(VueloDTO vueloDTO) {
		Vuelo v = new Vuelo();
		
		v.setCod_vuelo(vueloDTO.getCod_vuelo());
		v.setLlegada(vueloDTO.getLlegada());
		v.setSalida(vueloDTO.getSalida());
		v.setPrecio(vueloDTO.getPrecio());
		v.setDestino(AeropuertoAssembler.getInstance().getAeropuerto(vueloDTO.getAero_destino()));
		v.setOrigen(AeropuertoAssembler.getInstance().getAeropuerto(vueloDTO.getAero_origen()));
		v.setAerolinea(AerolineaAssembler.getInstance().getAerolinea(vueloDTO.getAerolinea()));
		
		return v;
	}
}
