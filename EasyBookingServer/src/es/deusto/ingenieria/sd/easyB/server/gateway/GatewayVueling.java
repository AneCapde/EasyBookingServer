package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import es.deusto.ingenieria.sd.easyB.server.data.Aerolinea;
import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

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
	public Vuelo buscarVuelos() {
		Vuelo vuelos = null;
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
				DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
				
			//ESTO NO SE COMO HACERLO
				ArrayList<Vuelo> request = this.vuelos;
				System.out.println("    -> Vuelos request:" + request);
				//out.write(request);
				
				//ArrayList<Vuelo> response = in.readUTF();
				//System.out.println("    -> Vuelos response:" + response);
							
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return vuelos;
	}
	
	@Override
	public Vuelo reservarVuelo(int cod_vuelo) {
		Vuelo vuelo = null;
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
			DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
							
			//Vuelo request = this.vuelo;
					
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return vuelo;
	}
	
	@Override
	public Vuelo recuperarInfo(int cod_vuelo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
