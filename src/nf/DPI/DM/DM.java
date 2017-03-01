/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import nf.DPI.DPI;
import java.util.ArrayList;


public class DM extends DPI{
    private String lettreDeSortie; 
    private ArrayList listeDesFichesDeSoins ; 
    private boolean décèsPatient;

    public DM(String lettreDeSortie, ArrayList listeDesFichesDeSoins, boolean décèsPatient, DPI dpi) {
        super(dpi.getNomNaissance(), dpi.getNomUsage(), dpi.getPrenom(), dpi.getAdresse(), dpi.getiPP(), dpi.getDateDeNaissance(), dpi.getnSecuriteSocial(), dpi.getInfoDeContact(), dpi.getLit(), dpi.getMyDM(), dpi.getMyDMA(), dpi.getSexe());
        this.lettreDeSortie = lettreDeSortie;
        this.listeDesFichesDeSoins = listeDesFichesDeSoins;
        this.décèsPatient = décèsPatient;
    }
    
    
    
    public String getLettreDeSortie(){
        return lettreDeSortie;
    }
    public String setLettreDeSortie(String dmLettreDeSortie){
        lettreDeSortie = dmLettreDeSortie;
        return dmLettreDeSortie;
    }
    
   public boolean archivageDM(){
        if (décèsPatient = true ){
            return true; 
        }
        else{
            return false;
        }
    }
}
