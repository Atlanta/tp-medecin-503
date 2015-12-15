package serveurs.patients;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

/**
 * Created by Raphael on 13/12/2015.
 */
public class ServeurRMI {

    public static void main(String[] args) throws IOException {

        //region medecins
//        PrintWriter writerMedecins = new PrintWriter("medecins.json", "UTF-8");
//
//
//        JSONArray arrayJsonMedecins = new JSONArray();
//
//
//        Medecin m1 = new Medecin("Pulwermacher-Blanchart", "Philippe", "ppb", "bob", "généraliste");
//        Medecin m2 = new Medecin("Laurie", "Hugh", "house", "tv", "mad");
//
//        JSONObject m1Json = m1.toJSON();
//        JSONObject m2Json = m2.toJSON();
//
//        arrayJsonMedecins.put(m1Json);
//        arrayJsonMedecins.put(m2Json);
//
//
//        writerMedecins.println(arrayJsonMedecins.toString());
//        writerMedecins.close();
//
//        BufferedReader readerMedecins = new BufferedReader(new FileReader("medecins.json"));
//        System.out.println(readerMedecins.readLine());
//        readerMedecins.close();
        //endregion

        //region patients
        PrintWriter writerPatients = new PrintWriter("patients.json", "UTF-8");

        GestionnaireIArrayList listePatients = new GestionnaireIArrayList();
        ArrayList<Patient> lesPatients = new ArrayList<Patient>();
        int id = lesPatients.size();
        System.out.println("Size : " + id);




        JSONArray arrayJsonPatients = new JSONArray();

        Patient p1 = new Patient(id, "Manson", "Charles", "foo", "bar", "0102030405");

        lesPatients.add(p1);
        id = lesPatients.size();
        System.out.println("Size : " + id);
        Patient p2 = new Patient(id, "Marc", "Machin", "log", "mdp", "0102030405");

        lesPatients.add(p2);
        listePatients.setLesPatients(lesPatients);

        JSONObject p1Json = p1.toJSON();
        JSONObject p2Json = p2.toJSON();



        arrayJsonPatients.put(p1Json);
        arrayJsonPatients.put(p2Json);

        writerPatients.println(arrayJsonPatients.toString());
        writerPatients.close();

        BufferedReader readerPatients = new BufferedReader(new FileReader("patients.json"));


        //System.out.println(readerPatients.readLine());

        JSONArray lesPatientsJson = new JSONArray(readerPatients.readLine());
        readerPatients.close();



        //endregion

        //region RDV
//        PrintWriter writerRDV = new PrintWriter("rendezVous.json", "UTF-8");
//
//        JSONArray arrayJsonRdv = new JSONArray();
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm");
//
//
//        try {
//            //Date date1 = formatter.parse("15/12/2015 16:00");
//            Date date1 = new Date(2015, 12, 15, 16, 00);
//            RendezVous rdv1 = new RendezVous(1, p1, m1, date1);
//
//            Date date2 = formatter.parse("03/01/2016 12:30");
//            RendezVous rdv2 = new RendezVous(1, p1, m1, date2);
//
//            arrayJsonRdv.put(rdv1.toJSON());
//            arrayJsonRdv.put(rdv2.toJSON());
//
//            writerRDV.println(arrayJsonRdv.toString());
//            writerRDV.close();
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        BufferedReader readerRdv = new BufferedReader(new FileReader("rendezVous.json"));
//        System.out.println(readerRdv.readLine());
//        readerRdv.close();



        //endregion






        try{

            try{
                LocateRegistry.createRegistry(1099);

            }catch(RemoteException e){
                System.out.println("Erreur à l'éxecution du RmiRegistry : \n "+ e);
            }

            Naming.rebind("charlie", p1);
            Naming.rebind("listePatients", listePatients);

        }catch(RemoteException e) {
            System.err.println("Erreur lors de l'enregistrement : " + e);
            System.exit(-1);
        } catch(MalformedURLException e) {
            System.err.println("URL mal formée : " + e);
            System.exit(-1);
        }
    }
}
