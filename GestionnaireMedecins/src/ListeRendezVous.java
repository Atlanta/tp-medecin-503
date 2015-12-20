import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * ListeRendezVous
 * Contient la liste des rendez-vous enregistrés dans le RMI
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class ListeRendezVous extends UnicastRemoteObject implements IListeRendezVous, Remote {
    private ArrayList<String> listeRendezVous;

    public ListeRendezVous() throws RemoteException {
        this.listeRendezVous = new ArrayList<String>();
    }

    public ArrayList<String> getListeRendezVous() throws RemoteException {
        return listeRendezVous;
    }

    public void setListeRendezVous(ArrayList<String> listeRendezVous) throws RemoteException {
        this.listeRendezVous = listeRendezVous;
    }

    /**
     * Ajoute le rendez-vous dans le RMI
     */
    public void ajouter(RendezVousDistant rendezVousDistant) throws RemoteException {
        try {
            Naming.rebind("RendezVous" + rendezVousDistant.getId(), rendezVousDistant);
            listeRendezVous.add("RendezVous" + rendezVousDistant.getId());
        } catch (RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
        } catch (MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
        }
    }

    /**
     * Enregistre le rendez-vous dans le fichier JSON puis invoque ajouter()
     */
    public void enregistrer(int id, int idMedecin, int idClient, Calendar date) throws RemoteException {
        try {
            RendezVousDistant rendezVousDistant = new RendezVousDistant(id, idMedecin, idClient, date);
            File file = new File("rdv.json");
            String fileContent = "";
            String line;

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("}]"))
                    line = "}, " + rendezVousDistant.toJSONString() + "]";
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

            this.ajouter(rendezVousDistant);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement dans le fichier : " + e);
        }
    }
}
