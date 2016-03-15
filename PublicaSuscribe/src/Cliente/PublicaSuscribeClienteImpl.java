package Cliente;

/**
 *
 * @author Carlos
 */

import java.rmi.*;
import java.rmi.server.*;

public class PublicaSuscribeClienteImpl extends UnicastRemoteObject implements PublicaSuscribeClienteInterface{
    PublicaSuscribeClienteImpl() throws RemoteException{
        super();
    }
    
    public String notificar(String mensaje){
        /* Mensaje para notificar de las alertas al cliente */
        String mensajeEnvio = " Alerta Recibida: " + mensaje;
        System.out.println(mensajeEnvio);
        return mensajeEnvio;
    }
}
