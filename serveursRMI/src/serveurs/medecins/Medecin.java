package serveurs.medecins;

import org.json.JSONObject;
import serveurs.PersonneDistante;

import java.rmi.RemoteException;

/**
 * Created by Raphael on 13/12/2015.
 */
public class Medecin extends PersonneDistante {

    private String specialisation;


    public Medecin(int id, String nom, String prenom, String login, String mdp, String specialisation) throws RemoteException {
        super(id, nom, prenom, login, mdp);
        this.specialisation = specialisation;
    }


    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public JSONObject toJSON()
    {
        JSONObject o = new JSONObject(this);
        return o;
    }


}
