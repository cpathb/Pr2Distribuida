/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebap2;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class PruebaP2 {

    public static void main(String[] args) throws IOException, InterruptedException{
        Document doc = Jsoup.connect("http://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000").get();
        Elements elementos = doc.getElementById("ctl00_Contenido_tblAcciones").select("tr");
        HashMap<String, Float> tabla = new HashMap();
        //LinkedList<Alerta> Alertas = new LinkedList();
        LinkedList<String> Alertas = new LinkedList();
        int i=1;
        while(true){
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
                if(i%5==0 && !Alertas.contains(nombre)){
                    Alertas.add(nombre);
                }
                i++;
            }
            i=0;
            System.out.println(tabla);
            while(i<Alertas.size()){
                System.out.println(Alertas.get(i)+" -> "+tabla.get(Alertas.get(i)));
                i++;
            }
            Thread.sleep(60000);
        }
    }
    
}
