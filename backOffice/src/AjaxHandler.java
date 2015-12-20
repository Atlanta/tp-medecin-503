import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Created by Julien on 18/12/2015.
 */
public class AjaxHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) {

        String reponse = "";
        String requete = "";
        Hashtable<String, String> postArgs = new Hashtable<String, String>();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(),"utf-8"));
        } catch(UnsupportedEncodingException e) {
            System.err.println("Erreur lors de la récupération du flux " + e);
            System.exit(-1);
        }

        try {
            requete = bufferedReader.readLine();
            postArgs = extraireArguments(requete);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture d'une ligne " + e);
            System.exit(-1);
        }

        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", "*");

        // Chargement des médecins
        if (postArgs.get("request").equals("chargerMedecins")) {
            headers.set("Content-Type", "text/html");
            try {
                IListeMedecins listeMedecins = (IListeMedecins) Naming.lookup("rmi://localhost/ListeMedecins");
                for(int i = 0; i < listeMedecins.getListeMedecins().size(); i++) {
                    IMedecinDistant medecinDistant = (IMedecinDistant) Naming.lookup("rmi://localhost/" + listeMedecins.getListeMedecins().get(i));
                    reponse += ("<option>" + medecinDistant.getPrenom() + " " + medecinDistant.getNom() + "</option>");
                }
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else if (postArgs.get("request").equals("clientName")) {
            headers.set("Content-Type", "text/html");
            try {
                IClientDistant clientDistant = (IClientDistant) Naming.lookup("rmi://localhost/Client" + postArgs.get("idClient"));
                reponse += (clientDistant.getPrenom() + " " + clientDistant.getNom());
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        try {
            httpExchange.sendResponseHeaders(200, reponse.getBytes().length);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de l'en-tête : " + e);
        }

        try {
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(reponse.getBytes());
            outputStream.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi du corps : " + e);
        }
    }

    public static Hashtable<String, String> extraireArguments(String URL) {
        Hashtable<String, String> argsList = new Hashtable<String, String>();
        StringTokenizer stringTokenizer = new StringTokenizer(URL, "&");
        String[] argument;

        while (stringTokenizer.hasMoreTokens()) {
            argument = stringTokenizer.nextToken().split("=");
            argsList.put(argument[0], argument[1]);
        }

        return argsList;
    }



}
