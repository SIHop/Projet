/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import java.util.ArrayList;
import nf.DPI.DMA.Sejour;

/**
 *
 * @author quentin
 */
public class DM {

    private String lettreDeSortie;
    private ArrayList<FicheDeSoins> listeDesFichesDeSoins;
    private Sejour sejour; 
    private boolean decesPatient;

    public DM(String lettreDeSortie, ArrayList listeDesFichesDeSoins,Sejour sejour) {
        this.lettreDeSortie = lettreDeSortie;
        this.listeDesFichesDeSoins = new ArrayList<FicheDeSoins>();
        this.sejour= sejour;
        this.decesPatient = false;
    }

    public String getLettreDeSortie() {
        return this.lettreDeSortie;
    }

    public Sejour getSejour() {
        return sejour;
    }

    public void setLettreDeSortie(String lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
    }

    public ArrayList<FicheDeSoins> getListeDesFichesDeSoins() {
        return this.listeDesFichesDeSoins;
    }

    public void setListeDesFichesDeSoins(ArrayList<FicheDeSoins> listeDesFichesDeSoins) {
        this.listeDesFichesDeSoins = listeDesFichesDeSoins;
    }

    public boolean isDecesPatient() {
        return this.decesPatient;
    }

    public void setDecesPatient(boolean decesPatient) {
        this.decesPatient = decesPatient;
    }
    
}
