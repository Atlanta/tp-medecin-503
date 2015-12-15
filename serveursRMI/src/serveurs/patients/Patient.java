package serveurs.patients;

import org.json.JSONObject;
import serveurs.PersonneDistante;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Raphael on 13/12/2015.
 */
public class Patient extends PersonneDistante implements IGestionnairePatientDistant {

    private String numeroTel;

    public Patient() throws RemoteException {
        super();
    }

    public Patient(int id, String nom, String prenom, String login, String mdp, String numeroTel) throws RemoteException {
        super(id, nom, prenom, login, mdp);
        this.numeroTel = numeroTel;
    }


    public String getNom() throws RemoteException {
        return super.getNom();
    }

    public void setNom(String nom) throws RemoteException {
        super.setNom(nom);
    }

    public String getPrenom() throws RemoteException{
        return super.getPrenom();
    }


    public void setPrenom(String prenom) throws RemoteException {
        super.setPrenom(prenom);
    }


    public String getLogin() throws  RemoteException {
        return super.getLogin();
    }

    public void setLogin(String login) throws RemoteException {
        super.setLogin(login);
    }


    public String getMdp() throws RemoteException{
        return super.getMdp();
    }

    public void setMdp(String mdp) throws RemoteException {
        super.setMdp(mdp);
    }


    public String getNumeroTel() throws RemoteException{
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) throws RemoteException{
        this.numeroTel = numeroTel;
    }

    public JSONObject toJSON() throws RemoteException
    {
        JSONObject o = new JSONObject(this);
        return o;
    }


    public boolean supprimerPatient(List<Patient> lesPatients, Patient p) throws RemoteException
    {
        boolean ok = lesPatients.remove(p);
        return ok;
    }

//    public boolean verifLogin(List<Patient> lesPatients, Patient p) throws RemoteException
//    {
//        boolean ok = lesPatients.contains(p);
//        return ok;
//    }

    public void ajouterPatient(int id, String nom, String prenom, String login, String mdp, String numTel, List<Patient> lesPatients) throws RemoteException {
        Patient p = new Patient(id, nom, prenom, login, mdp, numTel);
        //lesPatients.add(p);
    }
}
