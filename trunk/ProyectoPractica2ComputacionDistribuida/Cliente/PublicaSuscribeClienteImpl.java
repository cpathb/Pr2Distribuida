/**
 *
 * @author Carlos
 */

import java.rmi.*;
import java.rmi.server.*;

public class PublicaSuscribeClienteImpl extends UnicastRemoteObject implements PublicaSuscribeClienteInterface{
    public Interfaz interfaz;
    PublicaSuscribeClienteImpl() throws RemoteException{
        super();
    }
    
    PublicaSuscribeClienteImpl(Interfaz interfaz) throws RemoteException{
        super();
        this.interfaz=interfaz;
    }
    
    public synchronized String notificar(String mensaje){
        /* Mensaje para notificar de las alertas al cliente */
        String mensajeEnvio = "Atención: " + mensaje;
        System.out.println(mensajeEnvio);
        this.interfaz.rellenarAlerta(mensajeEnvio+"\n");
        return mensajeEnvio;
    }
}