/**
 *
 * @author Carlos
 */

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;

public class PublicaSuscribeServidor {
    public static PublicaSuscribeServidorImpl ObjetoExportado;
    public static String portNum="5009", registryURL;
    

    public static void main(String args[]) throws InterruptedException{
        try{ 
            int RMIPortNum = Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            ObjetoExportado = new PublicaSuscribeServidorImpl();
            registryURL = "rmi://localhost:" + portNum + "/publicasuscribe";
            Naming.rebind(registryURL, ObjetoExportado);
            System.out.println("Servidor Listo");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run(){
                    try{
                        int i=0;
                        while(i<ObjetoExportado.alertas.size()){
                            ObjetoExportado.alertas.get(i).getCliente().notificar("Tu alerta ha sido eliminada porque el servidor ha cerrado");
                            i++;
                        }
                        i=0;
                        while(i<ObjetoExportado.alertas.size()){
                            ObjetoExportado.alertas.remove(i).getCliente();
                            i++;
                        }
                        try{
                            Naming.unbind(registryURL);
                            System.out.println("Servidor cerrado\n");
                            //stem.exit(0);
                        }
                        catch(MalformedURLException mue ){
                        }
                        catch(NotBoundException ex){
                        }           
                    }
                    catch(RemoteException re){
                    }
                }
            });
            
            while(true){
                long time_start = System.currentTimeMillis();
                ObjetoExportado.PeticionDatos();
                ObjetoExportado.comprobarAlertas();
                long time_end= System.currentTimeMillis();
                if(time_end-time_start<60000){
                    Thread.sleep(60000-(time_end-time_start));
                }
            }
        }
        catch (Exception re) {
            System.out.println("Problema con el servidor: " + re);
        } 
    }

    
    private static void startRegistry(int RMIPortNum) throws RemoteException{
        /* Método para crear el registro RMI*/
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();  

        }
        catch (RemoteException e) { 
            /* No existe el registro en ese puerto */
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
        }
    }

}
