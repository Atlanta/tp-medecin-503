import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Julien on 19/12/2015.
 */
public class ListeClients extends UnicastRemoteObject implements IListeClients, Remote {
    private ArrayList<String> listeClients;

    public ListeClients() throws RemoteException {
        this.listeClients = new ArrayList<String>();
        JSONArray tableauClients = JSONTools.chargerJSON("clients.json");
        for (int i = 0; i < tableauClients.length(); i++) {
            JSONObject currentObject = (JSONObject) tableauClients.get(i);
            listeClients.add("Client" + currentObject.getInt("id"));
        }
    }

    public ArrayList<String> getListeClients() throws RemoteException {
        return listeClients;
    }

    public void setListeClients(ArrayList<String> listeMedecins) throws RemoteException {
        this.listeClients = listeMedecins;
    }
}
