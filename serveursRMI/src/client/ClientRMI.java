package client;

import serveurs.patients.IArrayList;
import serveurs.patients.IGestionnairePatientDistant;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * Created by Raphael on 14/12/2015.
 */
public class ClientRMI {

    public static void main(String[] args) throws RemoteException {
        IGestionnairePatientDistant p = null;
        IArrayList listePatients = null;



        try {
            p =  (IGestionnairePatientDistant)Naming.lookup("rmi://localhost/charlie");
            listePatients = (IArrayList)Naming.lookup("rmi://localhost/listePatients");

//            for(Patient patient : listePatients.getLesPatients())
//            {
//                System.out.println("id patient : " + patient.getId());
//            }
            int idPatient = listePatients.verifLogin("log", "mdp");

            System.out.println("verifLogin : " + idPatient);





        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }






    }


}