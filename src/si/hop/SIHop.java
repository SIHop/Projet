/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.hop;

import java.time.Year;
import java.util.ArrayList;
import nf.DPI.DM.Acte;
import nf.DPI.DM.Prescription;
import nf.DPI.DM.TypePrescription;

/**
 *
 * @author Loïc
 */
public class SIHop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int maxIPP = 1612345;
        int annee = maxIPP;
        while (annee > 100) {
            System.out.println(annee % 10);
            annee = annee / 10;
        }
        System.out.println(annee);
        int year = Year.now().getValue();
        System.out.println(year);
        annee += 2000;
        if(year == annee){
            maxIPP += 1;
            System.out.println("valeur finale : " + maxIPP);
        } else {
            year = year - 2000;
            maxIPP = year*100000+1;
            System.out.println("valeur finale : " + maxIPP);
        }
        
        
        Acte acte = new Acte(null,1,"23",null);
        System.out.println(acte.getObservations()+ "\n" + acte.getCode() + '\n' +acte.getCoef() + '\n' +acte.getCout() + '\n');
        Prescription prescription = new Prescription("Maladie : ",TypePrescription.CONSULTATION);
        System.out.println(prescription.getObservation() + "\n" +prescription.getTypePrescription());
        ArrayList<String> listeRes = new ArrayList<>();
        listeRes.add("mal");
        listeRes.add("bien");
        listeRes.add("contagieux");
        System.out.println(listeRes);
        //Resultat resultat = new Resultat (listeRes);
        //System.out.println(resultat);
        ArrayList<String> listeActes = new ArrayList<>();
        listeActes.add("CS");
        listeActes.add("KS"); 
        System.out.println(listeActes);
        ArrayList<String> listePrescriptions = new ArrayList<>();
        listePrescriptions.add("EXAMEN_SANGUIN");
        listePrescriptions.add("EXAMEN_RADIOLOGIQUE");
        listePrescriptions.add("CONSULTATION");
        listePrescriptions.add("MEDICAMENT");
        System.out.println(listePrescriptions);
       // Personnel createur = new Personnel ("GERES", "DENIZ","479283743", Sexe.FEMME, "24 rue de la jonquière 38000 GRENOBLE");
       // System.out.println(createur.getNom()+ "\n" + createur.getPrenom()+ "\n" +  createur.getIdPersonel() + "\n" + createur.getSexe() + "\n" + createur.getAdresse());
        //FicheDeSoins fds = new FicheDeSoins(listeActes,listePrescriptions, listeRes);
    }
}
