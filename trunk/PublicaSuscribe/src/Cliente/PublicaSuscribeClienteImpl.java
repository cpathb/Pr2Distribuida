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
        /* Añadir comprobaciones, como que la alerta sea posible de realizar mirando si en el HashMap y comprobando que existe la empresapara la que pide la alerta, comprobar tambien integridad de los datos y que no existe una petición de ese cliente para esa empresa del mismo tipo */
        String mensajeEnvio = " Alerta Recibida: " + mensaje;
        System.out.println(mensajeEnvio);
        return mensajeEnvio;
   }
}
