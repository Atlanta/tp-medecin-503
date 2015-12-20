import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * PersonneDistante
 * Classe abstraite, base de ClientDistant et MédecinDistant
 * @author Julien Hubert
 * @version 15/12/2015
 */
public abstract class PersonneDistante extends UnicastRemoteObject implements IPersonneDistante, Remote {
    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;

    public PersonneDistante(int id, String prenom, String nom, String login, String password) throws RemoteException {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
    }

    //region Getters/Setters

    public int getId() throws RemoteException {
        return id;
    }

    public String getNom() throws RemoteException {
        return nom;
    }

    public String getPrenom() throws RemoteException {
        return prenom;
    }

    public String getLogin() throws RemoteException {
        return login;
    }

    public String getPassword() throws RemoteException {
        return password;
    }

    public void setId(int id) throws RemoteException {
        this.id = id;
    }

    public void setNom(String nom) throws RemoteException {
        this.nom = nom;
    }

    public void setPrenom(String prenom) throws RemoteException {
        this.prenom = prenom;
    }

    public void setLogin(String login) throws RemoteException {
        this.login = login;
    }

    public void setPassword(String password) throws RemoteException {
        this.password = password;
    }

    //endregion

}