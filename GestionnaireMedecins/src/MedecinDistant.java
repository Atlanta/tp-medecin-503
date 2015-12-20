/**
 * Created by Atlanta on 16/12/15.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public class MedecinDistant extends PersonneDistante implements IMedecinDistant, Remote {

    // region Attributs

    private String fonction;

    // endregion

    //region Constructeurs

    public MedecinDistant() throws RemoteException {
        super();
        this.fonction = "Aucune";
    }

    public MedecinDistant(int id, String prenom, String nom, String login, String password, String fonction) throws RemoteException {
        super(id, prenom, nom, login, password);
        this.fonction = fonction;
    }

    //endregion

    //region Getters/Setters

    public String getFonction() throws RemoteException {
        return fonction;
    }

    public void setFonction(String fonction) throws RemoteException {
        this.fonction = fonction;
    }

    //endregion

}
