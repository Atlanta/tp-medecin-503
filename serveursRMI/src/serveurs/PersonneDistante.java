package serveurs;

import serveurs.rdv.RendezVous;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raphael on 13/12/2015.
 */
public abstract class PersonneDistante extends UnicastRemoteObject {

    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private List<RendezVous> lesRendezVous;

    public PersonneDistante() throws RemoteException {
        super();
    }

    public PersonneDistante(int id, String nom, String prenom, String login, String mdp) throws RemoteException {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;

        lesRendezVous = new ArrayList<RendezVous>();
    }


    public String getNom() throws RemoteException {
        return nom;
    }

    public void setNom(String nom) throws RemoteException {
        this.nom = nom;
    }

    public String getPrenom() throws RemoteException {
        return prenom;
    }

    public void setPrenom(String prenom) throws RemoteException {
        this.prenom = prenom;
    }

    public String getLogin() throws RemoteException {
        return login;
    }

    public void setLogin(String login) throws RemoteException {
        this.login = login;
    }

    public String getMdp() throws RemoteException {
        return mdp;
    }

    public void setMdp(String mdp) throws RemoteException {
        this.mdp = mdp;
    }


    public int getId()throws RemoteException {
        return id;
    }

}
