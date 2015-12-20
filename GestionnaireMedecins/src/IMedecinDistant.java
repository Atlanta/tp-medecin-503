/**
 * Créé par Julien le 16/12/15.
 */

import java.rmi.RemoteException;

public interface IMedecinDistant extends IPersonneDistante {

    public String getFonction() throws RemoteException;

    public void setFonction(String fonction) throws RemoteException;
}
