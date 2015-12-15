package serveurs.medecins;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Raphael on 13/12/2015.
 */
public interface IGestionnaireMedecinDistant extends Remote {

   // public void ajouterMedecin(String nom, String prenom, String login, String mdp, String specialisation, List<Medecin> lesMedecins) throws RemoteException;
    public boolean supprimerMedecin(List<Medecin> lesMedecins, Medecin m);
    public boolean verifLogin(List<Medecin> lesMedecins, Medecin m);
}
