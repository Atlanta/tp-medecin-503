import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * ListeClient
 * Contient la liste des clients enregistrés dans le RMI
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class ListeClients extends UnicastRemoteObject implements IListeClients, Remote {
    private ArrayList<String> listeClients;

    public ListeClients() throws RemoteException {
        this.listeClients = new ArrayList<String>();
    }

    public ArrayList<String> getListeClients() throws RemoteException {
        return listeClients;
    }

    public void setListeClients(ArrayList<String> listeMedecins) throws RemoteException {
        this.listeClients = listeMedecins;
    }

    /**
     * Ajoute le client dans le RMI
     */
    public void ajouter(ClientDistant clientDistant) throws RemoteException {
        try {
            Naming.rebind("Client" + clientDistant.getId(), clientDistant);
            listeClients.add("Client" + clientDistant.getId());
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
        } catch (MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
        }
    }

    /**
     * Enregistre le client dans le fichier JSON puis invoque ajouter()
     */
    public void enregistrer(int id, String prenom, String nom, String login, String password, int numeroTelephone) throws RemoteException {
        try {
            ClientDistant clientDistant = new ClientDistant(id, prenom, nom, login, password, numeroTelephone);
            File file = new File("clients.json");
            String fileContent = "";
            String line;

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("}]"))
                    line = "}, " + clientDistant.toJSONString() + "]";
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

            this.ajouter(clientDistant);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement dans le fichier : " + e);
        }
    }
}
