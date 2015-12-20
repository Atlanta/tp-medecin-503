/**
 * Créé par Julien le 19/12/2015.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IListeClients extends Remote {

    public ArrayList<String> getListeClients() throws RemoteException;
    public void setListeClients(ArrayList<String> listeMedecins) throws RemoteException;
    public void ajouter(ClientDistant clientDistant) throws RemoteException;
    public void enregistrer(int id, String nom, String prenom, String login, String password, int numeroTelephone) throws RemoteException;
}
