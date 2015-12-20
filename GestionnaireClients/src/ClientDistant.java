import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ClientDistant
 * Correspond à un client, implemente l'interface RMI
 * @see PersonneDistante
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class ClientDistant extends PersonneDistante implements IClientDistant, Remote {
    private int numeroTelephone;

    public ClientDistant(int id, String prenom, String nom, String login, String password, int numeroTelephone) throws RemoteException {
        super(id, prenom, nom, login, password);
        this.numeroTelephone = numeroTelephone;
    }

    public int getNumeroTelephone() throws RemoteException {
        return numeroTelephone;
    }
    public void setNumeroTelephone(int numeroTelephone) throws RemoteException {
        this.numeroTelephone = numeroTelephone;
    }

    public String toJSONString() throws RemoteException {
        return "{\n" +
                "   \"id\": " + this.getId() + ",\n" +
                "   \"nom\": \"" + this.getNom() + "\",\n" +
                "   \"prenom\": \"" + this.getPrenom() + "\",\n" +
                "   \"login\": \"" + this.getLogin() + "\",\n" +
                "   \"password\": \"" + this.getPassword() + "\",\n" +
                "   \"numeroTelephone\": " + this.getNumeroTelephone() + "\n" +
                "}";
    }
}
