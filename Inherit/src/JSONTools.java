import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * JSONTools
 * Implemente les méthodes utiles pour traiter les fichiers JSON
 * @author Julien Hubert
 * @version 16/12/2015
 */
public class JSONTools {
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
}
