/**
 * Created by Atlanta on 16/12/15.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPersonneDistante extends Remote {

    public int getId() throws RemoteException;
    public String getNom() throws RemoteException;
    public String getPrenom() throws RemoteException;
    public String getLogin() throws RemoteException;
    public String getPassword() throws RemoteException;

    public void setId(int id) throws RemoteException;
    public void setNom(String nom) throws RemoteException;
    public void setPrenom(String prenom) throws RemoteException;
    public void setLogin(String login) throws RemoteException;
    public void setPassword(String password) throws RemoteException;
}