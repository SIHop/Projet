/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import java.util.ArrayList;
import java.util.Calendar;
import nf.GestionDexploitation.Personnel;

/**
 *
 * @author Deniz
 */
public class FicheDeSoins {
    private final ArrayList<Acte> listeActes;
    private final ArrayList<Prescription> prescription;
    private final ArrayList<Resultat> resultat;
    private final Calendar dateDeCreation = Calendar.getInstance();
    private final Personnel createur;
    
    public FicheDeSoins(ArrayList<Acte> listeActes, ArrayList<Prescription> prescription, ArrayList<Resultat> resultat, Personnel createur) {
        this.listeActes = listeActes;
        this.prescription = prescription;
        this.resultat = resultat;
        this.createur = createur;
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

    public Calendar getDateDeCreation() {
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

   
}
