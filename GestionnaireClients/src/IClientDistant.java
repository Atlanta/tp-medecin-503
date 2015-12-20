/**
 * Créé par Julien le 17/12/2015.
 */

import java.rmi.RemoteException;

public interface IClientDistant extends IPersonneDistante {
    public int getNumeroTelephone() throws RemoteException;
    public void setNumeroTelephone(int numeroTelephone) throws RemoteException;
    public String toJSONString() throws RemoteException;
}
