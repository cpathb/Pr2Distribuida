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
        String mensajeEnvio = "Atención: " + mensaje;
        System.out.println(mensajeEnvio);
        return mensajeEnvio;
   }
}
