package serveurs.patients;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Raphael on 13/12/2015.
 */
public class GestionnairePatientDistant{

    public void ajouterPatient(int id, String nom, String prenom, String login, String mdp, String numTel, List<Patient> lesPatients) throws RemoteException {
        Patient p = new Patient(id, nom, prenom, login, mdp, numTel);
        lesPatients.add(p);
    }

//    public String getNom(){
//        return super.getNom();
//    }
//
//    public String getPrenom(){
//        return super.getPrenom();
//    }

    public boolean supprimerPatient(List<Patient> lesPatients, Patient p)
    {
        boolean ok = lesPatients.remove(p);
        return ok;
    }

    public boolean verifLogin(List<Patient> lesPatients, Patient p)
    {
        boolean ok = lesPatients.contains(p);
        return ok;
    }
}
