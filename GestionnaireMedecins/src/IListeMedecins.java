import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Créé par Julien le 19/12/2015.
 */
public interface IListeMedecins extends Remote {

    public ArrayList<String> getListeMedecins() throws RemoteException;
    public void setListeMedecins(ArrayList<String> listeMedecins) throws RemoteException;
    public void ajouter(MedecinDistant medecinDistant) throws RemoteException;

}
