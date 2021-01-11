package es.deusto.ingenieria.sd.easyB.server;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.easyB.server.remote.EasyBookingRemoteFacade;
import es.deusto.ingenieria.sd.easyB.server.remote.IEasyBookingRemoteFacade;

public class EasyBookingServer {
	public static void main(String[] args) {	
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		try {
			IEasyBookingRemoteFacade remoteFacade = EasyBookingRemoteFacade.getInstance();			
			Naming.rebind(name, remoteFacade);
			System.out.println(" * EasyBooking server '" + name + "' started");
		} catch (Exception ex) {
			System.err.println(" # EasyBooking Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
