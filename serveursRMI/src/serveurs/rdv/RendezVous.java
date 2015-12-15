package serveurs.rdv;

import org.json.JSONObject;
import serveurs.medecins.Medecin;
import serveurs.patients.Patient;

import java.util.Date;

/**
 * Created by Raphael on 13/12/2015.
 */
public class RendezVous {
    private int id;
    private Medecin medecin;
    private Patient patient;
    private Date date;

    public RendezVous(int id, Patient patient, Medecin medecin, Date date) {
        this.id = id;
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
    }


    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JSONObject toJSON()
    {
        JSONObject o = new JSONObject(this);
        return o;
    }
}
