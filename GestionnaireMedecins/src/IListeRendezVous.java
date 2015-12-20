import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Créé par Julien le 19/12/2015.
 */
public interface IListeRendezVous extends Remote {

    public ArrayList<String> getListeRendezVous() throws RemoteException;
    public void setListeRendezVous(ArrayList<String> listeRendezVous) throws RemoteException;
    public void enregistrer(int id, int idMedecin, int idClient, Calendar date) throws RemoteException;
}
