package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Aeropuerto;
import es.deusto.ingenieria.sd.easyB.server.data.Reserva;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;
import es.deusto.ingenieria.sd.easyB.server.data.dto.VueloDTO;

public class GatewayVueling implements IGatewayAerolinea{
	//comunicación mediante socket
	private String remoteServerIP;
	private int remoteServerPort;
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
		ArrayList<Vuelo> vuelos = null;
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
				DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
				
				String request = origen.getNombre() + "#" + destino.getNombre() + "#" + fecha + "#" + num_pasajeros;
				System.out.println("    -> Vuelos request:" + request);
				out.writeUTF(request);
				
				String response = in.readUTF();
				System.out.println("    <- Vuelos response:" + response);
				
				//esto no se si sobra
				if(response.startsWith("OK")) {
					//vuelos = response.substring(response.indexOf('#')+1)
				}
							
		} catch (Exception e) {
			System.out.println("# Error: " + e.getMessage());
		}
		return vuelos;
	}
	
	//no se si esta bien (no se como pasarle el nombre de pasajeros)
	@Override
	public Reserva reservarVuelo(Vuelo vuelo, double importe, int num_pasajeros, Date fecha, ArrayList<String> nombre_pasajeros) {
		Reserva reserva = null;
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
			DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
							
			String request = vuelo.getCod_vuelo() + "#" + importe + "#" + num_pasajeros+ "#"+ fecha + "#" + nombre_pasajeros;
			System.out.println("    -> Vuelo request:" + request);
			out.writeUTF(request);
			
			String response = in.readUTF();
			System.out.println("    <- Vuelos response:" + response);
			
			//esto no se si sobra
			if(response.startsWith("OK")) {
				//vuelos = response.substring(response.indexOf('#')+1)
			}
					
		} catch (Exception e) {
			System.out.println("# Error: " + e.getMessage());
		}
		
		return reserva;
	}
	
	
}

