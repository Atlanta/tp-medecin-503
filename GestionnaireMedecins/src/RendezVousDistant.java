import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

/**
 * RendezVousDistant
 * Correspond à un rendez-vous, implemente l'interface RMI
 * @author Julien Hubert
 * @version 17/12/2015
 */
public class RendezVousDistant extends UnicastRemoteObject implements IRendezVousDistant, Remote {
    private int id;
    private int idMedecin;
    private int idClient;
    private Calendar date;

    public RendezVousDistant(int id, int idMedecin, int idClient, Calendar date) throws RemoteException {
        this.id = id;
        this.idMedecin = idMedecin;
        this.idClient = idClient;
        this.date = date;
    }

    // region Get/Set

    public int getId() throws RemoteException {
        return id;
    }

    public void setId(int id) throws RemoteException {
        this.id = id;
    }

    public int getIdMedecin() throws RemoteException {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) throws RemoteException {
        this.idMedecin = idMedecin;
    }

    public int getIdClient() throws RemoteException {
        return idClient;
    }

    public void setIdClient(int idClient) throws RemoteException {
        this.idClient = idClient;
    }

    public Calendar getDate() throws RemoteException {
        return date;
    }

    public void setDate(Calendar date) throws RemoteException {
        this.date = date;
    }

    // endregion

    public String toJSONString() throws RemoteException {
        return "{\n" +
                "   \"id\": " + this.getId() + ",\n" +
                "   \"idMedecin\": " + this.getIdMedecin() + ",\n" +
                "   \"idClient\": " + this.getIdClient() + ",\n" +
                "   \"date\": {\n" +
                "       \"annee\": " + this.getDate().get(Calendar.YEAR) + ",\n" +
                "       \"mois\": " + this.getDate().get(Calendar.MONTH) + ",\n" +
                "       \"jour\": " + this.getDate().get(Calendar.DAY_OF_MONTH) + ",\n" +
                "       \"heure\": " + this.getDate().get(Calendar.HOUR_OF_DAY) + ",\n" +
                "       \"minutes\": " + this.getDate().get(Calendar.MINUTE) + "\n" +
                "   }\n" +
                "}";
    }
}
