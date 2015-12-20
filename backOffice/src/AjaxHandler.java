import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * AjaxHandler
 * Gère toutes les requêtes ajax avec le back-office, excepté le login
 * @author Julien Hubert
 * @version 18/12/2015
 */
public class AjaxHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) {

        String reponse = "";
        String requete;
        Hashtable<String, String> postArgs = new Hashtable<String, String>();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "utf-8"));
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
        headers.set("Content-Type", "text/html; charset=UTF-8");
        headers.set("Access-Control-Allow-Origin", "*");

        // Récupération des listes
        // Pas très pratique car appel de toutes les listes à chaque chargement de la page
        //  Mais ici on travaille en local
        IListeMedecins listeMedecins = null;
        IListeRendezVous listeRendezVous = null;
        IListeClients listeClients = null;
        try {
            listeMedecins = (IListeMedecins) Naming.lookup("rmi://localhost/ListeMedecins");
            listeRendezVous = (IListeRendezVous) Naming.lookup("rmi://localhost/ListeRendezVous");
            listeClients = (IListeClients) Naming.lookup("rmi://localhost/ListeClients");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        /**
        Affichage des médecins
        Retourne le menu de sélection des médecins
         */
        if (postArgs.get("request").equals("chargerMedecins")) {
            try {
                for(int i = 0; i < listeMedecins.getListeMedecins().size(); i++) {
                    IMedecinDistant medecinDistant = (IMedecinDistant) Naming.lookup("rmi://localhost/" + listeMedecins.getListeMedecins().get(i));
                    reponse += ("<option>" + medecinDistant.getPrenom() + " " + medecinDistant.getNom() + " - " + medecinDistant.getFonction() + "</option>");
                }
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /*
        Récupération du nom du client
        Retourne le nom du client
         */
        else if (postArgs.get("request").equals("clientName")) {
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

        /*
        Récupération des rendez-vous d'un client
        Renvoie la liste des rendez-vous avec balises HTML
         */
        else if (postArgs.get("request").equals("clientRdv")) {
            String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
            reponse += "<ul>";
            try {
                IClientDistant clientDistant = (IClientDistant) Naming.lookup("rmi://localhost/Client" + postArgs.get("idClient"));
                for(int i = 0; i < listeRendezVous.getListeRendezVous().size(); i++) {
                    IRendezVousDistant rendezVousDistant = (IRendezVousDistant) Naming.lookup("rmi://localhost/" + listeRendezVous.getListeRendezVous().get(i));
                    if (rendezVousDistant.getIdClient() == clientDistant.getId()) {
                        IMedecinDistant medecinDistant = (IMedecinDistant) Naming.lookup("rmi://localhost/" + listeMedecins.getListeMedecins().get(rendezVousDistant.getIdMedecin()));
                        reponse += ("<li>" + rendezVousDistant.getDate().get(Calendar.DAY_OF_MONTH) + " " + months[rendezVousDistant.getDate().get(Calendar.MONTH)] + " " + rendezVousDistant.getDate().get(Calendar.YEAR) + " à " + rendezVousDistant.getDate().get(Calendar.HOUR_OF_DAY) + "h" + (rendezVousDistant.getDate().get(Calendar.MINUTE) == 0 ? "00" : rendezVousDistant.getDate().get(Calendar.MINUTE))  + " avec Dr " + medecinDistant.getPrenom() + " " + medecinDistant.getNom() + "</li>");
                    }
                }
                reponse += "</ul>";
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /*
        Ajout de rendez-vous
        Ajoute un rendez-vous dans la liste et l'enregistre dans le fichier JSON
         */
        else if (postArgs.get("request").equals("addRdv")) {
            try {
                IClientDistant clientDistant = (IClientDistant) Naming.lookup("rmi://localhost/Client" + postArgs.get("idClient"));
                IMedecinDistant medecinDistant = (IMedecinDistant) Naming.lookup("rmi://localhost/Medecin" + postArgs.get("idMedecin"));
                Calendar date = Calendar.getInstance();
                int annee = Integer.parseInt(postArgs.get("annee"));
                int mois = Integer.parseInt(postArgs.get("mois"));
                int jour = Integer.parseInt(postArgs.get("jour"));
                int heure = Integer.parseInt(postArgs.get("heure"));
                int minutes = Integer.parseInt(postArgs.get("minutes"));
                date.set(annee, mois, jour, heure, minutes);
                boolean dejaPris = false;

                for (int i = 0; i < listeRendezVous.getListeRendezVous().size(); i++) {
                    IRendezVousDistant rendezVousDistant = (IRendezVousDistant) Naming.lookup("rmi://localhost/" + listeRendezVous.getListeRendezVous().get(i));
                    if(rendezVousDistant.getIdMedecin() == medecinDistant.getId() && rendezVousDistant.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) && rendezVousDistant.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH) && rendezVousDistant.getDate().get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH) && rendezVousDistant.getDate().get(Calendar.HOUR_OF_DAY) == date.get(Calendar.HOUR_OF_DAY) && rendezVousDistant.getDate().get(Calendar.MINUTE) == date.get(Calendar.MINUTE))
                        dejaPris = true;
                }
                // Si le créneau n'est pas pris on enregistre le rendez-vous
                if(!dejaPris)
                    listeRendezVous.enregistrer(listeRendezVous.getListeRendezVous().size(), medecinDistant.getId(), clientDistant.getId(), date);
                else
                    reponse += "false";
                // Sinon on renvoie "faux"
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /*
        Enregistrement d'utilisateur
        Ajoute un utilisateur dans la liste et l'enregistre dans le fichier JSON
         */
        else if (postArgs.get("request").equals("register")) {
            try {
                boolean dejaPris = false;
                int numberExisting = 0;

                // Le login est de forme nomprenom, sans accents ni caractères spéciaux
                String login = postArgs.get("login").toLowerCase();
                login = login.replaceAll("[^a-z]", "");

                for (int i = 0; i < listeClients.getListeClients().size(); i++) {
                    IClientDistant clientDistant = (IClientDistant) Naming.lookup("rmi://localhost/" + listeClients.getListeClients().get(i));
                    if(postArgs.get("firstName").equals(clientDistant.getPrenom()) && postArgs.get("lastName").equals(clientDistant.getNom())) {
                        dejaPris = true;
                        numberExisting++;
                    }
                }
                if(!dejaPris) {
                    reponse += login;
                }
                else {
                    login += numberExisting;
                    reponse += login;
                }
                listeClients.enregistrer(listeClients.getListeClients().size(), postArgs.get("firstName"), postArgs.get("lastName"), login, postArgs.get("password"), Integer.parseInt(postArgs.get("phoneNumber")));
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                reponse += "false+RequestError";
            } catch (RemoteException e) {
                e.printStackTrace();
                reponse += "false+InternalError";
            }
        }

        /*
        Envoi des en-têtes HTTP
         */
        try {
            httpExchange.sendResponseHeaders(200, reponse.getBytes().length);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de l'en-tête : " + e);
        }

        /*
        Envoi des données
         */
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
