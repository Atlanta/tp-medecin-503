import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * ListeMedecins
 * Contient la liste des médecins enregistrés dans le RMI
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class ListeMedecins extends UnicastRemoteObject implements IListeMedecins, Remote {
    private ArrayList<String> listeMedecins;

    public ListeMedecins() throws RemoteException {
        this.listeMedecins = new ArrayList<String>();
    }

    public ArrayList<String> getListeMedecins() throws RemoteException {
        return listeMedecins;
    }
    public void setListeMedecins(ArrayList<String> listeMedecins) throws RemoteException {
        this.listeMedecins = listeMedecins;
    }

    /**
     * Ajoute le médecin dans le RMI
     */
    public void ajouter(MedecinDistant medecinDistant) throws RemoteException {
        try {
            Naming.rebind("Medecin" + medecinDistant.getId(), medecinDistant);
            listeMedecins.add("Medecin" + medecinDistant.getId());
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
        } catch (MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
        }
    }

}
