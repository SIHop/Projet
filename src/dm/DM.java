/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import java.util.ArrayList;

/**
 *
 * @author quentin
 */
public class DM {

    private String lettreDeSortie;
    private ArrayList<FicheDeSoins> listeDesFichesDeSoins;
    private boolean decesPatient;

    public DM(String lettreDeSortie, ArrayList listeDesFichesDeSoins) {
        this.lettreDeSortie = lettreDeSortie;
        this.listeDesFichesDeSoins = new ArrayList<FicheDeSoins>();
        FicheDeSoins fds = new FicheDeSoins();
        this.listeDesFichesDeSoins.add(fds);
        this.decesPatient = false;
    }

    public String getLettreDeSortie() {
        return this.lettreDeSortie;
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
