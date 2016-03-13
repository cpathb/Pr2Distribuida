/**
 *
 * @author Carlos
 */

import java.rmi.*;

public interface PublicaSuscribeClienteInterface extends java.rmi.Remote{
    public String notificar(String mensaje) throws java.rmi.RemoteException;
}

