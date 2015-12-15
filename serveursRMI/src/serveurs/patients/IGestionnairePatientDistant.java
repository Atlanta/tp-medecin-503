package serveurs.patients;

import serveurs.patients.Patient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Raphael on 13/12/2015.
 */
public interface IGestionnairePatientDistant extends Remote {

    public int getId() throws RemoteException;
    public String getNom() throws RemoteException;
    public String getPrenom() throws RemoteException;
    public String getLogin() throws RemoteException;
    public String getMdp() throws RemoteException;
    public String getNumeroTel() throws RemoteException;
    public void setNom(String nom) throws  RemoteException;
    public void setPrenom(String prenom) throws RemoteException;
    public void setLogin(String login) throws RemoteException;
    public void setMdp(String mdp) throws RemoteException;
    public void setNumeroTel(String numeroTel) throws RemoteException;

    public void ajouterPatient(int id, String nom, String prenom, String login, String mdp, String numTel, List<Patient> lesPatients) throws RemoteException;
    public boolean supprimerPatient(List<Patient> lesPatients, Patient p) throws RemoteException;
    //public boolean verifLogin(List<Patient> lesPatients, Patient p) throws RemoteException;
}
