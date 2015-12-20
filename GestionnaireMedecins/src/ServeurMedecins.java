import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.util.Calendar;

/**
 * Serveur médecins
 * Gère les données relatives aux médecins et aux rendez-vous
 * @author Julien Hubert
 * @version 18/12/2015
 */
public class ServeurMedecins {

    public static void main(String[] args) {

        chargerMedecins("medecins.json");
        chargerRendezVous("rdv.json");

        System.out.println("Serveur démarré.");
    }

    /**
     * Charge les médecins dans le RMI et la liste des médecins depuis le fichier JSON
     * @param file Le fichier JSON qui contient les médecins
     */
    public static void chargerMedecins(String file) {
        JSONArray jsonObjectArray = JSONTools.chargerJSON(file);
        ListeMedecins listeMedecins;

        try {
            listeMedecins = new ListeMedecins();
            for (int i = 0; i < jsonObjectArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonObjectArray.get(i);

                MedecinDistant medecinDistant = new MedecinDistant(jsonObject.getInt("id"), jsonObject.getString("prenom"), jsonObject.getString("nom"), jsonObject.getString("login"), jsonObject.getString("password"), jsonObject.getString("fonction"));
                listeMedecins.ajouter(medecinDistant);
            }
            Naming.rebind("ListeMedecins", listeMedecins);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les rendez-vous dans le RMI et la liste des rendez-vous depuis le fichier JSON
     * @param file Le fichier JSON qui contient les rendez-vous
     */
    public static void chargerRendezVous(String file) {
        JSONArray jsonObjectArray = JSONTools.chargerJSON(file);
        ListeRendezVous listeRendezVous;

        try {
            listeRendezVous = new ListeRendezVous();
            for (int i = 0; i < jsonObjectArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonObjectArray.get(i);

                Calendar calendar = Calendar.getInstance();
                JSONObject date = jsonObject.getJSONObject("date");
                calendar.set(date.getInt("annee"), date.getInt("mois"), date.getInt("jour"), date.getInt("heure"), date.getInt("minutes"));

                listeRendezVous.ajouter(new RendezVousDistant(jsonObject.getInt("id"), jsonObject.getInt("idMedecin"), jsonObject.getInt("idClient"), calendar));
            }
            Naming.rebind("ListeRendezVous", listeRendezVous);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}