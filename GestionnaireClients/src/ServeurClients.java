import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

/**
 * Serveur clients
 * Gère les données relatives aux patients
 * @author Julien Hubert
 * @version 18/12/2015
 */
public class ServeurClients {

    public static void main(String[] args) {

        chargerClients("clients.json");

        System.out.println("Serveur démarré.");
    }

    /**
     * Charge les clients dans le RMI et la liste des clients depuis le fichier JSON
     * @param file Le fichier JSON qui contient les clients
     */
    public static void chargerClients(String file) {
        JSONArray jsonObjectArray = JSONTools.chargerJSON(file);
        ListeClients listeClients;

        try {
            listeClients = new ListeClients();
            for (int i = 0; i < jsonObjectArray.length(); i++) {
                JSONObject currentObject = (JSONObject) jsonObjectArray.get(i);

                listeClients.ajouter(new ClientDistant(currentObject.getInt("id"), currentObject.getString("prenom"), currentObject.getString("nom"), currentObject.getString("login"), currentObject.getString("password"), currentObject.getInt("numeroTelephone")));
            }
            Naming.rebind("ListeClients", listeClients);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}