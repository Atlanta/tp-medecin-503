import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * LoginHandler
 * Gère le login en Ajax avec le back-office
 * @author Julien Hubert
 * @version 17/12/2015
 */
class LoginHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) {
        String reponse = "";
        String requete = "";
        boolean isLoginCorrect = false;
        boolean isPasswordCorrect = false;
        Hashtable<String, String> arguments = new Hashtable<String, String>();

        // Utilisation d'un flux pour lire les données du message Http
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(),"utf-8"));
        } catch(UnsupportedEncodingException e) {
            System.err.println("Erreur lors de la récupération du flux " + e);
        }

        // Récupération des données en POST
        try {
            if (bufferedReader != null) {
                requete = bufferedReader.readLine();
            }
            arguments = extraireArguments(requete);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture d'une ligne " + e);
        }

        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", "*");

        try {
                IListeClients listeClients = (IListeClients) Naming.lookup("rmi://localhost/ListeClients");
                for(int i = 0; i < listeClients.getListeClients().size(); i++) {
                    IClientDistant clientDistant = (IClientDistant) Naming.lookup("rmi://localhost/" + listeClients.getListeClients().get(i));

                    if(clientDistant.getLogin().equals(arguments.get("login"))) {
                        isLoginCorrect = true;
                        if (arguments.get("password").equals(clientDistant.getPassword())) {
                            isPasswordCorrect = true;
                            reponse += ("idClient=" + clientDistant.getId());
                        }
                    }
                }
                if(isLoginCorrect && !isPasswordCorrect) {
                    reponse += "false+Password";
                }
                else if (!isLoginCorrect){
                    reponse += "false+User";
                }
        } catch (NotBoundException e) {
            System.err.println("Utilisateur inexistant : " + e);
            reponse += "false+User";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            reponse += "false+RequestError";
        } catch (RemoteException e) {
            e.printStackTrace();
            reponse += "false+InternalError";
        }

        // Envoi de l'en-tête Http
        try {
            httpExchange.sendResponseHeaders(200, reponse.getBytes().length);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de l'en-tête : " + e);
        }

        // Envoi du corps (données HTML)
        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(reponse.getBytes());
            outputStream.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi du corps : " + e);
        }
    }

    /**
     * Fonction qui récupère les arguments POST et les rentre dans un tableau
     * @param URL L'URL qui contient les données POST
     * @return Tableau associatif des paramètres
     */
    public static Hashtable<String, String> extraireArguments(String URL) {
        Hashtable<String, String> argumentsList = new Hashtable<String, String>();
        StringTokenizer stringTokenizer = new StringTokenizer(URL, "&");
        String[] argument;

        while (stringTokenizer.hasMoreTokens()) {
            argument = stringTokenizer.nextToken().split("=");
            argumentsList.put(argument[0], argument[1]);
        }

        return argumentsList;
    }

}