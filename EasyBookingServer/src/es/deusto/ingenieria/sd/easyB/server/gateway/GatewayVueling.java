package es.deusto.ingenieria.sd.easyB.server.gateway;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import es.deusto.ingenieria.sd.easyB.server.data.Vuelo;

public class GatewayVueling implements IGatewayAerolinea{
	//comunicación mediante socket
	private String remoteServerIP;
	private int remoteServerPort;
	

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

	@Override
	public Vuelo buscarVuelos() {
		Vuelo vuelos = null;
		//Abrimos socket
		try (Socket tcpSocket = new Socket(this.remoteServerIP, this.remoteServerPort);
				DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {
				
				
			
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
							
						
					
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return vuelo;
	}
}
