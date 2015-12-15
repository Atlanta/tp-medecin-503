package serveurs.medecins;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Raphael on 13/12/2015.
 */
public class GestionnaireMedecinDistant implements IGestionnaireMedecinDistant{
    public void ajouterMedecin(int id, String nom, String prenom, String login, String mdp, String specialisation, List<Medecin> lesMedecins) throws RemoteException {
        Medecin m = new Medecin(id, nom, prenom, login, mdp, specialisation);

        lesMedecins.add(m);

    }


    public boolean supprimerMedecin(List<Medecin> lesMedecins, Medecin m)
    {
        boolean ok = lesMedecins.remove(m);
        return  ok;
    }

    public boolean verifLogin(List<Medecin> lesMedecins, Medecin m)
    {
        boolean ok = lesMedecins.contains(m);
        return ok;
    }


}
