package Cliente;

/**
 *
 * @author Carlos
 */

import java.rmi.*;

public class PublicaSuscribeCliente {
    public static void main(String args[]) {
        try{
            int RMIPort=5009;         
            String hostName ="localhost";
            String registryURL = "rmi://localhost:" + RMIPort + "/publicasuscribe";  
            // Casteamos el objeto remoto a una intrerface
            PublicaSuscribeServidorInterface h = (PublicaSuscribeServidorInterface)Naming.lookup(registryURL);
            System.out.println("Lookup completed " );
            PublicaSuscribeClienteInterface Objetocliente = new PublicaSuscribeClienteImpl(); // Registramos una alerta
            Alerta alerta= new Alerta(Objetocliente, "compra", "ABERTIS",900);
            h.RegistroAlerta(alerta);
            System.out.println("Petición de alerta enviada");
            while(true){
            }
        } // end try 
        catch (Exception e) {
            System.out.println("Problema con el cliente: " + e);
        } // end catch
    }
}
