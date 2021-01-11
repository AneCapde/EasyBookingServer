package es.deusto.ingenieria.sd.easyB.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// <<Singletone>> Implemeta la interface IEasyBookingRemoteFacade
public class EasyBookingRemoteFacade extends UnicastRemoteObject implements IEasyBookingRemoteFacade{

	private static final long serialVersionUID = 1L;
	private static EasyBookingRemoteFacade instance;
	
	private EasyBookingRemoteFacade() throws RemoteException {
		super();		
	}
	
	// Lazy Initialization
	public static IEasyBookingRemoteFacade getInstance() {
		if (instance == null) {
			try {
				instance = new EasyBookingRemoteFacade();
			} catch (Exception ex) {
				System.err.println("# Error creating EasyBookingRemoteFacade: " + ex);
			}
		}	
		return instance;
	}

}
