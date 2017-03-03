/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;


import java.util.ArrayList;


public class DM extends DPI{
    private String lettreDeSortie; 
    private ArrayList<FicheDeSoins> listeDesFichesDeSoins; 
    private boolean décèsPatient;
    
    public DM(String lettreDeSortie, ArrayList listeDesFichesDeSoins,boolean décèsPatient ){
        super ();
        this.lettreDeSortie = lettreDeSortie; 
        this.listeDesFichesDeSoins = new ArrayList <FicheDeSoins> () ;
        FicheDeSoins fds = new FicheDeSoins();
        this.listeDesFichesDeSoins.add(fds);
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
      FicheDeSoins fds = new FicheDeSoins();
      fds.getResultat();
      fds.getOperationInfirmière();
      fds.getCreateur();
      fds.getService(); 
      Acte mesActes = new Acte(); 
      mesActes.getCode(); 
      mesActes.getCoef(); 
      mesActes.getObservation(); 
      Prescription prescrip = new Prescription(); 
      prescrip.getObservation(); 
      prescrip.getTypePrecription(); 
    }
    
}
