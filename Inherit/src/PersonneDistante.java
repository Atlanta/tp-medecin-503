/**
 * Created by Atlanta on 16/12/15.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PersonneDistante extends UnicastRemoteObject implements IPersonneDistante, Remote {

    //region Attributs

    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;

    //endregion

    //region Constructeurs

    public PersonneDistante() throws RemoteException {
        id = 0;
        nom = "Doe";
        prenom = "John";
        login = "johndoe";
        password = "password";
    }

    public PersonneDistante(int id, String prenom, String nom, String login, String password) throws RemoteException {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
    }

    //endregion

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