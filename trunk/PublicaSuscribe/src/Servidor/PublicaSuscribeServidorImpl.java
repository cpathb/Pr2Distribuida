package Servidor;

/**
 *
 * @author Carlos
 */

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import javax.swing.DefaultListModel;

public class PublicaSuscribeServidorImpl extends UnicastRemoteObject implements PublicaSuscribeServidorInterface{
    private LinkedList<Alerta> alertas;
    private HashMap<String, Float> tabla;
    
    PublicaSuscribeServidorImpl() throws RemoteException{
        super();
        alertas =new LinkedList();
        tabla = new HashMap();
    }
    
    @Override
    public synchronized void RegistroAlerta(Alerta alerta) throws RemoteException {
        if (!(alertas.contains(alerta))) { // Comprobamos si ya existe la alerta en la lista
            if(comprobarAlerta(alerta)){ // Comprobamos si la alerta es válida
                alertas.add(alerta);
                System.out.println("Registrada una nueva alerta");
                alerta.getCliente().notificar("Añadida tu alerta de "+alerta.getTipo()+" para la empresa "+alerta.getNombreEmpresa().trim()+" con el valor objetivo: "+alerta.getValorObjetivo()); //Notificamos el exito de la creación al cliente
            }
            else{
                alerta.getCliente().notificar("La alerta que quieres realizar no es válida");
            }
        }
        else{
            alerta.getCliente().notificar("La alerta que quieres realizar ya existe");
        }
    }

    @Override
    public synchronized void DesRegistroAlerta(Alerta alerta) throws RemoteException {
        /* Función para eliminar una alerta concreta */
        if (alertas.remove(alerta)) { // Si podemos eliminar la alerta esta existía, si no, no existía
            System.out.println("Alerta eliminada");
        }
        else {
            System.out.println("La alerta no existía");
        }
    }
    
    public synchronized void DesRegistroAlertas(Alerta alerta) throws RemoteException {
        /* Función para borrar todas las alertas de un cliente (Usado para cuando se vaya a cerrar un cliente */
        int i=0;
        while(i<alertas.size()){ // Mientras no hayamos recorrido todas las alertas o la lista de alertas no este vacia
            if (alertas.get(i).getCliente().equals(alerta.getCliente())) { // Si coinciden los clientes se borra la alerta
                alertas.remove(i);
            }
            else {
                i++;
            }
        }
        alerta.getCliente().notificar("Eliminadas todas tus alertas");
    }
    
    public synchronized void PeticionDatos() throws IOException, InterruptedException{
        Document doc = Jsoup.connect("http://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000").get();
        Elements elementos = doc.getElementById("ctl00_Contenido_tblAcciones").select("tr");
        int i=1;

        while(i<elementos.size()){
            String nombre;
            float valor;
            nombre = elementos.get(i).select("td").get(0).text().toLowerCase();
            valor = Float.parseFloat(elementos.get(i).select("td").get(1).text().replace(",", "."));
            if(tabla.containsKey(nombre)){
                tabla.replace(nombre, valor);
            }
            else{
                tabla.put(nombre, valor);
            }
            i++;
        }
    }
    
    public synchronized void comprobarAlertas() throws RemoteException{
        /* Función para comprobar las alertas y notificar y eliminar las alertas que se cumplen */
        int i=0;
        while(i<alertas.size()){
            if(alertas.get(i).getTipo().toLowerCase().trim().compareTo("compra")==0){ // Si la alerta es de tipo compra
                if(tabla.get(alertas.get(i).getNombreEmpresa().trim())<=alertas.get(i).getValorObjetivo()){ // Cumple la condición de compra si el valor actual es menor o igual al objetivo
                    alertas.get(i).getCliente().notificar("Tu alerta de "+alertas.get(i).getTipo()+" para la empresa "+alertas.get(i).getNombreEmpresa().trim()+" con el valor objetivo: "+alertas.get(i).getValorObjetivo()); // Notificamos al cliente que se cumplio su alerta
                    alertas.remove(i); // Eliminamos la alerta
                }
                else{ // No se cumple la condición, pasamos a la siguiente alerta
                    i++;
                }    
            }
            else{ // Si la alerta es de tipo venta 
                if(tabla.get(alertas.get(i).getNombreEmpresa().trim())>=alertas.get(i).getValorObjetivo()){ // Cumple la condición de venta si el valor actual es mayor o igual al objetivo
                    alertas.get(i).getCliente().notificar("Tu alerta de "+alertas.get(i).getTipo()+" para la empresa "+alertas.get(i).getNombreEmpresa().trim()+" con el valor objetivo: "+alertas.get(i).getValorObjetivo()); // Notificamos al cliente que se cumplio su alerta
                    alertas.remove(i); // Eliminamos la alerta
                }
                else{ // No se cumple la condición, pasamos a la siguiente alerta
                    i++;
                }
            }
        }
    }
    
    public boolean comprobarAlerta(Alerta alerta){
        /* Comprueba la validez de una alerta que se intenta insertar */
        if(tabla.containsKey(alerta.getNombreEmpresa().trim())){
            if(alerta.getTipo().toLowerCase().trim().compareTo("compra")==0 || alerta.getTipo().toLowerCase().trim().compareTo("venta")==0){
                if(alerta.getValorObjetivo()>0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    public synchronized DefaultListModel<String> EnviarEmpresas(PublicaSuscribeClienteInterface cliente){
        /* Mensaje para notificar de las alertas al cliente */
        DefaultListModel<String> modelo = new DefaultListModel();
        tabla.keySet().forEach((s) -> {
            System.out.println(s);
            modelo.addElement(s);
        });
        return modelo;
   }
}
