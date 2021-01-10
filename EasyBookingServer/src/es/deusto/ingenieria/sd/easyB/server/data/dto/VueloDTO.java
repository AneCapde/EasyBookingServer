package es.deusto.ingenieria.sd.easyB.server.data.dto;

import java.io.Serializable;
import java.util.Date;

public class VueloDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int cod_vuelo;
	private Date llegada;
	private Date salida;
	private double precio;
	
}
