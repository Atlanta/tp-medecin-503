/**
 * Created by Julien on 17/12/2015.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientDistant extends PersonneDistante implements IClientDistant, Remote {
    private int numeroTelephone;

    //region Constructeurs

    public ClientDistant() throws RemoteException {
        super();
        this.numeroTelephone = 0;
    }

    public ClientDistant(int id, String prenom, String nom, String login, String password, int numeroTelephone) throws RemoteException {
        super(id, prenom, nom, login, password);
        this.numeroTelephone = numeroTelephone;
    }

    //endregion

    // region Get/Set

    public int getNumeroTelephone() throws RemoteException {
        return numeroTelephone;
    }

    public void setNumeroTelephone(int numeroTelephone) throws RemoteException {
        this.numeroTelephone = numeroTelephone;
    }

    // endregion

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        ClientDistant otherObj = (ClientDistant) obj;

        try {
            return getId() == otherObj.getId() && getNom().equals(otherObj.getNom()) && getPrenom().equals(otherObj.getPrenom()) && getLogin().equals(otherObj.getLogin()) && getPassword().equals(otherObj.getPassword()) && getNumeroTelephone() == otherObj.getNumeroTelephone();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
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
