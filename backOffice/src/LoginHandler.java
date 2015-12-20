import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Classe correspondant au handler sur le contexte 'index.html'.
 * @author Cyril Rabat
 * @version 2015/06/25
 */
class LoginHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) {
        String reponse = "";
        String requete = "";
        boolean isLoginCorrect = false;

        // Utilisation d'un flux pour lire les données du message Http
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(),"utf-8"));
        } catch(UnsupportedEncodingException e) {
            System.err.println("Erreur lors de la récupération du flux " + e);
        }

        Hashtable<String, String> arguments = new Hashtable<String, String>();
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
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        try {
            if (!arguments.isEmpty()) {
                IListeClients listeClients = (IListeClients) Naming.lookup("rmi://localhost/ListeClients");
                for(int i = 0; i < listeClients.getListeClients().size(); i++) {
                    IClientDistant clientDistant = (IClientDistant) Naming.lookup("rmi://localhost/" + listeClients.getListeClients().get(i));

                    if(clientDistant.getLogin().equals(arguments.get("login"))) {
                        if (arguments.get("password").equals(clientDistant.getPassword())) {
                            headers.set("Location", "http://localhost/gestion.php");
                            reponse += ("idClient=" + clientDistant.getId());
                        }
                    }
                }
                if(reponse.equals("")) {
                    headers.set("Location", "http://localhost/");
                    reponse += "false+Password";
                }
            }
            else
                headers.set("Location", "http://localhost/");
        } catch (NotBoundException e) {
            System.err.println("Utilisateur inexistant : " + e);
            headers.set("Location", "http://localhost/");
            reponse += "false+User";
        } catch (MalformedURLException e) {
            e.printStackTrace();
            headers.set("Location", "http://localhost/");
            reponse += "false+RequestError";
        } catch (RemoteException e) {
            e.printStackTrace();
            headers.set("Location", "http://localhost/");
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