/**
 * Created by Atlanta on 16/12/15.
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;

public class ServeurMedecins {

    private static String fichierMedecins;
    private static String fichierRendezVous;

    public static void main(String[] args) {
        fichierMedecins = "medecins.json";
        fichierRendezVous = "rdv.json";
        // Chargement de medecins.json dans le RMI

        chargerMedecins(fichierMedecins);
        //chargerRendezVous(fichierRendezVous);

        ListeMedecins listeMedecins = null;
        try {
            listeMedecins = new ListeMedecins();
            Naming.rebind("ListeMedecins", listeMedecins);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // region Méthodes liées aux Médecins

    public static void ajouterMedecin(MedecinDistant medecin) {
        try {
            Naming.rebind(medecin.getPrenom() + medecin.getNom(), medecin);
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
        } catch (MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
        }
    }

    public static void chargerMedecins(String file) {
        JSONArray jsonObjectArray = JSONTools.chargerJSON(file);

        for (int i = 0; i < jsonObjectArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonObjectArray.get(i);
            try {
                ajouterMedecin(new MedecinDistant(jsonObject.getInt("id"), jsonObject.getString("prenom"), jsonObject.getString("nom"), jsonObject.getString("login"), jsonObject.getString("password"), jsonObject.getString("fonction")));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean medecinExiste(MedecinDistant medecin) {
        JSONArray tableauMedecins = chargerJSON(fichierMedecins);
        boolean medecinExiste = false;

        for (int i = 0; i < tableauMedecins.length(); i++) {
            JSONObject currentObject = (JSONObject) tableauMedecins.get(i);
            try {
                MedecinDistant currentClient = new MedecinDistant(currentObject.getInt("id"), currentObject.getString("prenom"), currentObject.getString("nom"), currentObject.getString("login"), currentObject.getString("password"), currentObject.getString("fonction"));
                if (currentClient.equals(medecin))
                    medecinExiste = true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return medecinExiste;
    }

    public static MedecinDistant rechercherMedecin(int id) {
        JSONArray tableauMedecins = chargerJSON(fichierMedecins);
        MedecinDistant medecinDistant = null;

        for (int i = 0; i < tableauMedecins.length(); i++) {
            JSONObject currentObject = (JSONObject) tableauMedecins.get(i);
            try {
                MedecinDistant currentMedecin = new MedecinDistant(currentObject.getInt("id"), currentObject.getString("prenom"), currentObject.getString("nom"), currentObject.getString("login"), currentObject.getString("password"), currentObject.getString("fonction"));
                if (currentMedecin.getId() == id)
                    medecinDistant = currentMedecin;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return medecinDistant;
    }

    // endregion

    // region Méthodes liées aux Rendez-Vous

    public static void ajouterRendezVous(RendezVousDistant rdv) {
        try {
            Naming.rebind("RendezVous" + rdv.getId(), rdv);
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
        } catch (MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
        }
    }

    public static void chargerRendezVous(String file) {
        JSONArray jsonObjectArray = chargerJSON(file);

        for (int i = 0; i < jsonObjectArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonObjectArray.get(i);
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.set(jsonObject.getInt("annee"), jsonObject.getInt("mois") - 1, jsonObject.getInt("jour"), jsonObject.getInt("heure"), jsonObject.getInt("minutes"));
                ajouterRendezVous(new RendezVousDistant(jsonObject.getInt("id"), jsonObject.getInt("idMedecin"), jsonObject.getInt("idClient"), calendar));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void enregistrerRendezVous(RendezVousDistant rdv) {
        try {
            File file = new File(fichierRendezVous);
            String fileContent = "";
            String line = "";

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("}]"))
                    line = "}, " + rdv.toJSONString() + "]";
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

            ajouterRendezVous(rdv);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement dans le fichier : " + e);
        }
    }

    public static ArrayList<Integer> rechercherRendezVous(ClientDistant client) {
        ArrayList<Integer> listeRendezVous = new ArrayList<Integer>();
        JSONArray tableauRendezVous = chargerJSON(fichierRendezVous);

        for (int i = 0; i < tableauRendezVous.length(); i++) {
            JSONObject currentObject = (JSONObject) tableauRendezVous.get(i);
            try {
                if (currentObject.getInt("idClient") == client.getId())
                    listeRendezVous.add(currentObject.getInt("id"));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return listeRendezVous;
    }

    // endregion

    // region Gestion JSON

    public static JSONArray chargerJSON(String file) {
        FileReader f = null;
        try {
            f = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.err.println("Fichier non trouvé : " + file);
        }
        BufferedReader br = new BufferedReader(f);

        int currentCharacter;
        String JSONString = "";

        try {
            while ((currentCharacter = br.read()) != -1)
                JSONString += (char)currentCharacter;
        } catch (IOException e) {
            System.err.println("Erreur de lecture.");
        }

        JSONArray jsonObjectArray = new JSONArray(JSONString);

        return jsonObjectArray;
    }

    // endregion
}