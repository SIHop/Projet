/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DPI.DM;

import GestionDexploitation.Personnel;
import GestionDexploitation.Service;
import java.util.ArrayList;

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
    public ArrayList listeActes; 
    public ArrayList prescription; 
    
    
    public FicheDeSoins(String resultatPH ,String resultatAnesth, Personnel createur, Service service,String operationInfirmière, ArrayList listeActes, ArrayList prescription,String anesthesiste, String praticienHospitalier, String medecin ){
        this.resultatPH = resultatPH; 
        this.resultatAnesth = resultatAnesth; 
        this.createur = createur; 
        this.service = service;
        this.operationInfirmière = operationInfirmière;
        this.listeActes = listeActes; 
        this.prescription = prescription; 
        this.anesthesiste = anesthesiste;
        this.praticienHospitalier = praticienHospitalier;
        this.medecin = medecin;
       
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
