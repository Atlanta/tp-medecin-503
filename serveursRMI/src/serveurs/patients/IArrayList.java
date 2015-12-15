package serveurs.patients;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Raphael on 14/12/2015.
 */
public interface IArrayList extends Remote {
    public ArrayList<Patient> getLesPatients() throws RemoteException;
    public void setLesPatients(ArrayList<Patient> lesPatients) throws RemoteException;
    public int verifLogin(String login, String mdp)throws RemoteException;


}
