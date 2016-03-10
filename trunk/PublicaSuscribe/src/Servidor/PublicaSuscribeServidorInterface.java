package Servidor;

/**
 *
 * @author Carlos
 */

import java.rmi.*;

public interface PublicaSuscribeServidorInterface extends java.rmi.Remote{
    public void RegistroAlerta(Alerta alerta) throws java.rmi.RemoteException;
    public void DesRegistroAlerta(Alerta alerta) throws java.rmi.RemoteException;
    
} 

