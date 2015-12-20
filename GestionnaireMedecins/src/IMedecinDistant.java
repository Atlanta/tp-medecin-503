/**
 * Created by Atlanta on 16/12/15.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMedecinDistant extends IPersonneDistante {

    public String getFonction() throws RemoteException;

    public void setFonction(String fonction) throws RemoteException;
}
