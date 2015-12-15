package serveurs.patients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Raphael on 15/12/2015.
 */
public class GestionnaireIArrayList extends UnicastRemoteObject implements IArrayList{

    private ArrayList<Patient>lesPatients;

    protected GestionnaireIArrayList() throws RemoteException {
        super();
    }

    public ArrayList<Patient> getLesPatients() throws RemoteException {
        return lesPatients;
    }


    public void setLesPatients(ArrayList<Patient> lesPatients) throws RemoteException {
        this.lesPatients = lesPatients;
    }


    public int verifLogin(String login, String mdp) throws RemoteException {

        int idPatient = -1;
        for (Patient p: lesPatients) {
            if(login.equals(p.getLogin()) && mdp.equals(p.getMdp()) ){
                 idPatient = p.getId();
            }
        }
        return idPatient;
    }


}
