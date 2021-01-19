package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import es.deusto.ingenieria.sd.easyB.server.dao.DBManager;
import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public class GatewayVueling implements IGatewayAerolinea{
	//comunicación mediante socket
	private String remoteServerIP = "127.0.0.1";
	private int remoteServerPort = 3000;
	private ArrayList<Vuelo> vuelos;
	private Vuelo vuelo;
	
	
	public int getRemoteServerPort() {
		return remoteServerPort;
	}
	public void setRemoteServerPort(int remoteServerPort) {
		this.remoteServerPort = remoteServerPort;
	}
	public String getRemoteServerIP() {
		return remoteServerIP;
	}
	public void setRemoteServerIP(String remoteServerIP) {
		this.remoteServerIP = remoteServerIP;
	}
	
	public ArrayList<Vuelo> getVuelos() {
		return vuelos;
	}
	public void setVuelos(ArrayList<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	@Override
	public ArrayList<Vuelo> buscarVuelos(Aeropuerto origen, Aeropuerto destino, Date fecha, int num_pasajeros) {
		ArrayList<Vuelo> vuelos = new ArrayList<>();
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
				DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
				
				String request = origen.getCod_aeropuerto() + "#" + destino.getCod_aeropuerto() + "#" + fecha + "#" + num_pasajeros;
				System.out.println("    -> Vuelos request:" + request);
				out.writeUTF(request);
				
				String response = in.readUTF();
				vuelos = StringtoVuelos(response);
				System.out.println("    <- Vuelos response:" + response);
							
		} catch (Exception e) {
			System.out.println("# Error: " + e.getMessage());
		}
		return vuelos;
	}
	
	 
	@Override
	public Reserva reservarVuelo(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) {
		Reserva reserva = new Reserva();
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
			DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
							
			String request = vuelo.getCod_vuelo() + "#" + importe + "#" + num_pasajeros+ "#"+ fecha + "#" + nombre_pasajeros;
			System.out.println("    -> Vuelo request:" + request);
			out.writeUTF(request);
			
			boolean response = in.readBoolean();
			System.out.println("    <- Vuelos response:" + response);
			
			if(response) {
				reserva.setFecha(fecha);
				reserva.setImporte(importe);
				reserva.setNombre_pasajeros(nombre_pasajeros);
				reserva.setNumero_asientos(num_pasajeros);
				reserva.setVuelo(vuelo);			
			}
					
		} catch (Exception e) {
			System.out.println("# Error: " + e.getMessage());
		}
		
		return reserva;
	}
	
	public ArrayList<Vuelo> StringtoVuelos(String datosVuelos){
		ArrayList<Vuelo> vuelos = new ArrayList<>();

	    StringTokenizer tokenizerVuelo = new StringTokenizer(datosVuelos, "$");

	     while (tokenizerVuelo.hasMoreElements()) {

	         StringTokenizer tokenizer = new StringTokenizer(datosVuelos, "#");
	         String cod_vuelo = tokenizer.nextToken();
	         String origen = tokenizer.nextToken();
	         String destino = tokenizer.nextToken();
	         String llegada = tokenizer.nextToken();
	         String salida = tokenizer.nextToken();
	         String precio = tokenizer.nextToken();
	         
	         Vuelo vuelo = new Vuelo();
	         vuelo.setCod_vuelo(cod_vuelo);
	         for( Aeropuerto ae : DBManager.getInstance().getAeropuertos()) {
	             if (ae.getCod_aeropuerto().equals(destino)) {
	                 vuelo.setDestino(ae);
	             }
	             if (ae.getCod_aeropuerto().equals(origen)) {
	                 vuelo.setOrigen(ae);
	             }
	         }
	         
	         vuelo.setPrecio(Double.parseDouble(precio));
	         Aerolinea aerolinea = new Aerolinea();
	         aerolinea.setCod_aero("VLG");
	         aerolinea.setNombre("Vueling");
	         vuelo.setAerolinea(aerolinea);
	         try {
				vuelo.setSalida(new SimpleDateFormat("dd/MM/yyyy").parse(salida));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	         try {
				vuelo.setLlegada(new SimpleDateFormat("dd/MM/yyyy").parse(llegada));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	         vuelos.add(vuelo);       
	}
		return vuelos;
	}
	
}

