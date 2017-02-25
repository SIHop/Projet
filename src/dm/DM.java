/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import java.util.ArrayList;


public class DM extends DPI{
    private String lettreDeSortie; 
    private ArrayList listeDesFichesDeSoins ; 
    private boolean décèsPatient;
    
    public DM(String lettreDeSortie, ArrayList listeDesFichesDeSoins){
        this.lettreDeSortie = lettreDeSortie; 
        this.listeDesFichesDeSoins = listeDesFichesDeSoins;
        décèsPatient = false; 
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
    
    
    public static void main(String[] args) {
      
    }
    
}
