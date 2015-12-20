import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ClientDistant
 * Correspond à un médecin, implemente l'interface RMI
 * @see PersonneDistante
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class MedecinDistant extends PersonneDistante implements IMedecinDistant, Remote {
    private String fonction;

    public MedecinDistant(int id, String prenom, String nom, String login, String password, String fonction) throws RemoteException {
        super(id, prenom, nom, login, password);
        this.fonction = fonction;
    }

    public String getFonction() throws RemoteException {
        return fonction;
    }

    public void setFonction(String fonction) throws RemoteException {
        this.fonction = fonction;
    }
}
