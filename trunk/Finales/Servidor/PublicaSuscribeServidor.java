/**
 *
 * @author Carlos
 */

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class PublicaSuscribeServidor {
    public static void main(String args[]){
        String portNum="5009", registryURL;
        try{     
            int RMIPortNum = Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            PublicaSuscribeServidorImpl ObjetoExportado = new PublicaSuscribeServidorImpl();
            registryURL = "rmi://localhost:" + portNum + "/publicasuscribe";
            Naming.rebind(registryURL, ObjetoExportado);
            System.out.println("Servidor Listo");
            
            while(true){
                long time_start = System.currentTimeMillis();
                ObjetoExportado.PeticionDatos();
                ObjetoExportado.comprobarAlertas();
                long time_end= System.currentTimeMillis();
                if(time_end-time_start<60000){
                    System.out.println("+1 vuelta");
                    Thread.sleep(60000-(time_end-time_start));
                }
            }
        }// end try
        catch (Exception re) {
            System.out.println("Problema con el servidor: ");
            re.printStackTrace();
        } // end catch
    } // end main

    //This method starts a RMI registry on the local host, if
    //it does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum) throws RemoteException{
        try {
            Registry registry = 
            LocateRegistry.getRegistry(RMIPortNum);
            registry.list( );  
            // This call will throw an exception
            // if the registry does not already exist
        }
        catch (RemoteException e) { 
            // No valid registry at that port.
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
        }
    } // end startRegistry

} // end class
