package Servidor;

/**
 *
 * @author Carlos
 */

import java.rmi.*;
import java.rmi.server.*;

public class PublicaSuscribeServidorImpl extends UnicastRemoteObject implements PublicaSuscribeServidorInterface{
    PublicaSuscribeServidorImpl() throws RemoteException{
        super();
    }
    
    public synchronized void RegistroAlerta(Alerta alerta) throws RemoteException {
//        if (!(clientList.contains(callbackClientObject))) {
//            clientList.addElement(callbackClientObject);
//            System.out.println("Registered new client ");
//            doCallbacks();
//        }
    }

    public synchronized void DesRegistroAlerta(Alerta alerta) throws RemoteException {
//        if (clientList.removeElement(callbackClientObject)) {
//            System.out.println("Unregistered client ");
//        }
//        else {
//        System.out.println("unregister: clientwasn't registered.");
//        }
    }
}
