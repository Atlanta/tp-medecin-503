import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * Created by Julien on 19/12/2015.
 */
public interface IRendezVousDistant extends Remote {

    public int getId() throws RemoteException;
    public void setId(int id) throws RemoteException;
    public int getIdMedecin() throws RemoteException;
    public void setIdMedecin(int idMedecin) throws RemoteException;
    public int getIdClient() throws RemoteException;
    public void setIdClient(int idClient) throws RemoteException;
    public Calendar getDate() throws RemoteException;
    public void setDate(Calendar date) throws RemoteException;
    public String toJSONString() throws RemoteException;

}
