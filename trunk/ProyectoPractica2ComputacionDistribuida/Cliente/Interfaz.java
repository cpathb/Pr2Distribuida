/**
 *
 * @author icarli
 */

import javax.swing.JTextArea;
import java.io.Serializable;

public class Interfaz implements Serializable{
    public JTextArea cajita;
    
    public void rellenarAlerta(String mensaje){
        cajita.append(mensaje);
    }
    
}
