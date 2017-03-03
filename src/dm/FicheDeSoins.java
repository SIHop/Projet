/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deniz
 */
public class FicheDeSoins {
    private String resultatPH; 
    private String resultatAnesth;
    public String anesthesiste; 
    public String praticienHospitalier; 
    public Personnel createur; 
    public String medecin;
    public Service service;
    private String operationInfirmière;
    public ArrayList<Acte> listeActes; 
    public ArrayList<Prescription> prescription; 
    
    
    public FicheDeSoins(String resultatPH ,String resultatAnesth, Personnel createur, Service service,String operationInfirmière, ArrayList listeActes, ArrayList prescription,String anesthesiste, String praticienHospitalier, String medecin ){
        this.resultatPH = resultatPH; 
        this.resultatAnesth = resultatAnesth; 
        this.createur = createur; 
        this.service = service;
        this.operationInfirmière = operationInfirmière;
        this.listeActes = new ArrayList<Acte> (); 
        Acte acte = new Acte();
        this.listeActes.add(acte);
        this.prescription = new ArrayList<Prescription> (); 
        Prescription presc =new Prescription();
        this.prescription.add(presc);
        this.anesthesiste = anesthesiste;
        this.praticienHospitalier = praticienHospitalier;
        this.medecin = medecin;
       
    }

    FicheDeSoins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getResultat(){
        if (this.praticienHospitalier.equals(medecin) ){
        return resultatPH;
    }
        else if (this.anesthesiste.equals(medecin)){
            return resultatAnesth;
        }
        else {
            return null;
        }
    }
    
    public String setResultatPH(String fResultatPH){
        resultatPH = fResultatPH;
        return fResultatPH;
    }
    public String setResultatAnesth(String fResultatAnesth){
        resultatAnesth = fResultatAnesth;
        return fResultatAnesth;
    }
    
    
      public String getOperationInfirmière(){
        return operationInfirmière;
    }
      
    public String setOperationInfirmière(String fOperationInfirmière){
        operationInfirmière = fOperationInfirmière;
        return fOperationInfirmière;
    }
    
    public Personnel getCreateur(){
        return createur;
    }
    
    public Service getService(){
        return service;
    }

   
}
