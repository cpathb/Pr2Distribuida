/**
 *
 * @author Carlos
 */

import java.rmi.*;
import javax.swing.DefaultListModel;

public interface PublicaSuscribeServidorInterface extends java.rmi.Remote{
    public void RegistroAlerta(Alerta alerta) throws java.rmi.RemoteException;
    public void DesRegistroAlerta(Alerta alerta) throws java.rmi.RemoteException;
    public void DesRegistroAlertas(PublicaSuscribeClienteInterface Objetocliente) throws java.rmi.RemoteException ;
    public DefaultListModel<String> EnviarEmpresas(PublicaSuscribeClienteInterface cliente) throws java.rmi.RemoteException;
} 

