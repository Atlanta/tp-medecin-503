/**
 * Created by Julien on 17/12/2015.
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

public class ServeurClients {

    public static void main(String[] args) {

        String jsonFilePath = "clients.json";
        // Chargement de medecins.json dans le RMI
        chargerClients(jsonFilePath);

        ListeClients listeClients = null;
        try {
            listeClients = new ListeClients();
            Naming.rebind("ListeClients", listeClients);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void ajouterClient(ClientDistant client) {
        try {
            Naming.rebind("Client" + client.getId(), client);
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
        } catch (MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
        }
    }

    /**
     * Renvoie si un client existe ou non dans le fichier JSON
     * @param jsonFile Le fichier JSON à parser
     * @param client Le client à rechercher
     * @return Vrai si le client existe, faux sinon
     */
    public static boolean clientExiste(String jsonFile, ClientDistant client) {
        JSONArray tableauClients = chargerJSON(jsonFile);
        boolean clientExiste = false;

        for (int i = 0; i < tableauClients.length(); i++) {
            JSONObject currentObject = (JSONObject) tableauClients.get(i);
            try {
                ClientDistant currentClient = new ClientDistant(currentObject.getInt("id"), currentObject.getString("prenom"), currentObject.getString("nom"), currentObject.getString("login"), currentObject.getString("password"), currentObject.getInt("numeroTelephone"));
                if (currentClient.equals(client))
                    clientExiste = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return clientExiste;
    }

    //region Chargement

    public static JSONArray chargerJSON(String jsonFile) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            System.err.println("Fichier non trouvé : " + jsonFile);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int currentCharacter;
        String jsonString = "";

        try {
            while ((currentCharacter = bufferedReader.read()) != -1)
                jsonString += (char)currentCharacter;
        } catch (IOException e) {
            System.err.println("Erreur de lecture.");
        }

        JSONArray jsonObjectArray = new JSONArray(jsonString);

        return jsonObjectArray;
    }

    public static void chargerClients(String jsonFile) {
        JSONArray jsonObjectArray = chargerJSON(jsonFile);

        for (int i = 0; i < jsonObjectArray.length(); i++) {
            JSONObject currentObject = (JSONObject) jsonObjectArray.get(i);
            try {
                ajouterClient(new ClientDistant(currentObject.getInt("id"), currentObject.getString("prenom"), currentObject.getString("nom"), currentObject.getString("login"), currentObject.getString("password"), currentObject.getInt("numeroTelephone")));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    //endregion

    public static void enregistrerClient(String jsonFile, ClientDistant client) {
        try {
            File file = new File(jsonFile);
            String fileContent = "";
            String line = "";

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("}]"))
                    line = "}, " + client.toJSONString() + "]";
                else
                    line += "\n";
                fileContent += line;
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(fileContent);

            bufferedWriter.close();
            bufferedReader.close();

            fileWriter.close();
            fileReader.close();

            ajouterClient(client);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement dans le fichier : " + e);
        }
    }

}