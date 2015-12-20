import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Julien on 19/12/2015.
 */

public class ListeMedecins extends UnicastRemoteObject implements IListeMedecins, Remote {
    private ArrayList<String> listeMedecins;

    public ListeMedecins() throws RemoteException {
        this.listeMedecins = new ArrayList<String>();
        JSONArray tableauMedecins = JSONTools.chargerJSON("medecins.json");
        for (int i = 0; i < tableauMedecins.length(); i++) {
            JSONObject currentObject = (JSONObject) tableauMedecins.get(i);
            listeMedecins.add(currentObject.getString("prenom") + currentObject.getString("nom"));
        }
    }

    public ArrayList<String> getListeMedecins() throws RemoteException {
        return listeMedecins;
    }

    public void setListeMedecins(ArrayList<String> listeMedecins) throws RemoteException {
        this.listeMedecins = listeMedecins;
    }
}
