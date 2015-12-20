import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Julien on 19/12/2015.
 */
public interface IListeClients extends Remote {

    public ArrayList<String> getListeClients() throws RemoteException;
    public void setListeClients(ArrayList<String> listeMedecins) throws RemoteException;

}
