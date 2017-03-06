/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import db.GestionnaireDB.DAOFactory;
import java.util.ArrayList;
import java.util.Calendar;
import nf.Adresse.DateT;
import nf.GestionDexploitation.Personnel;

/**
 *
 * @author Deniz
 */
public class FicheDeSoins {
    private final ArrayList<Acte> listeActes;
    private final ArrayList<Prescription> prescription;
    private final ArrayList<Resultat> resultat;
    private DateT dateDeCreation = new DateT("");
    private final Personnel createur;
    private String numeroSejour;
    private final int idFicheDeSoins;
    
    public FicheDeSoins(ArrayList<Acte> listeActes, ArrayList<Prescription> prescription, ArrayList<Resultat> resultat, Personnel createur) {
        this.listeActes = listeActes;
        this.prescription = prescription;
        this.resultat = resultat;
        this.createur = createur;
        this.dateDeCreation.setC(Calendar.getInstance());
        this.idFicheDeSoins = DAOFactory.getFicheDeSoinsDAO().getMaxId() +1;
        DAOFactory.getFicheDeSoinsDAO().create(this);
    }
    
    //Consutructeur appeller pas la DB
    public FicheDeSoins(int idFicheDeSoins, ArrayList<Acte> listeActes, ArrayList<Prescription> prescription, ArrayList<Resultat> resultat, Personnel createur,DateT dateCreation, String numeroSejour) {
        this.listeActes = listeActes;
        this.prescription = prescription;
        this.resultat = resultat;
        this.createur = createur;
        this.dateDeCreation = dateCreation;
        this.numeroSejour = numeroSejour;
        this.idFicheDeSoins = idFicheDeSoins;
    }

    public ArrayList<Acte> getListeActes() {
        return listeActes;
    }

    public ArrayList<Prescription> getPrescription() {
        return prescription;
    }

    public ArrayList<Resultat> getResultat() {
        return resultat;
    }

    public DateT getDateDeCreation() {
        return dateDeCreation;
    }

    public Personnel getCreateur() {
        return createur;
    }

    public double calculerCoutFiche(){
        double res = 0.0;
        for(Acte a : listeActes){
            res += a.calculerCout();
        }
        return res;
    }

    /**
     * @return the numeroSejour
     */
    public String getNumeroSejour() {
        return numeroSejour;
    }

    /**
     * @return the idFicheDeSoins
     */
    public int getIdFicheDeSoins() {
        return idFicheDeSoins;
    }

   
}
