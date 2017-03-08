/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.util.ArrayList;
import java.util.Arrays;
import nf.DPI.DM.Prescription;
import nf.DPI.DM.TypePrescription;
import nf.GestionDexploitation.Lit;
import nf.GestionDexploitation.Localisation;

/**
 *
 * @author Loïc
 */
public class testDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test unitaires
//        //ActesDAO :
//        //**find OK
//        Acte acte2 = DAOFactory.getActeDAO().find();
//        System.out.println(acte2.toString());
//        //**findMultiple OK
//        ArrayList<Acte> lActe = DAOFactory.getActeDAO().findMultiple(new ArrayList<String>(Arrays.asList("idFicheDeSoins")), new ArrayList<String>(Arrays.asList("0")));
//        for(Acte a : lActe){
//            System.out.println(a.toString());
//        }
//        
//        System.out.println(acte2.getCode().getCode());
//        //**create -> inclus dans le constructeur OK
//        Acte acte = new Acte(1,Code.CSC,20,"Ras",TypeActe.DIAGNOSTIQUE);
//        System.out.println(acte.toString());
//        System.out.println(acte.getIdActe());
//        
//        //**delete  OK
//        DAOFactory.getActeDAO().delete(acte);
        
//        //**update OK
//        acte2.setObservations("Tumeur begnin");
//        DAOFactory.getActeDAO().update(acte2);
        
//        //AdressePatientDAO
//       AdressePatientDAO adressePatientDAO = DAOFactory.getAdressePatientDAO();
//        //**find OK
//       Adresse ap = adressePatientDAO.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList("150000011")));
//        System.out.println(ap.toString());
//        //** findMultiple OK
//        ArrayList<Adresse> lap = adressePatientDAO.findMultiple(new ArrayList<>(Arrays.asList("pays")), new ArrayList<>(Arrays.asList("'France'")));
//        for(Adresse a : lap){
//            System.out.println(a.toString());
//        }
//        //**create OK
//        adressePatientDAO.create(ap,170000002);
//        //**update OK
//        ap.setCodePostal(38400);
//        ap.setVille("SMH");
//        adressePatientDAO.update(ap, 170000001);
//        
//        //**delete OK
//        adressePatientDAO.delete(170000002);
        
//        //AdressePersonnelDAO
//        AdressePersonnelDAO adressePersonnelDAO = DAOFactory.getAdressePersonnelDAO();
//        //**find OK
//        Adresse ap = adressePersonnelDAO.find(new ArrayList<>(Arrays.asList("idPersonnel")), new ArrayList<>(Arrays.asList("1")));
//        System.out.println(ap.toString());
//        //**findMultiple OK
//        ArrayList<Adresse> lap = adressePersonnelDAO.findMultiple(new ArrayList<>(Arrays.asList("pays")), new ArrayList<>(Arrays.asList("'France'")));
//        for(Adresse a : lap){
//            System.out.println(a.toString());
//        }
//        //**create OK
//        adressePersonnelDAO.create(ap,6);
//        //**update  OK
//        ap.setCodePostal(38400);
//        ap.setVille("SMH");
//        adressePersonnelDAO.update(ap, 6);
//        //** delete OK
//        adressePersonnelDAO.delete(6);
        
//        //LitDAO
//        LitDAO litDAO = (LitDAO)DAOFactory.getLitDAO();
//        //** find OK
//        Lit lit = litDAO.find(new ArrayList<>(Arrays.asList("idLit")), new ArrayList<>(Arrays.asList("1")));
//        System.out.println(lit.toString());        
//        //** find multiple OK
//        ArrayList<Lit> lLit = litDAO.findMultiple(new ArrayList<>(Arrays.asList("idService")), new ArrayList<>(Arrays.asList("1")));
//        for(Lit l : lLit){
//            System.out.println(l.toString());
//        }
//        //** create OK
//        Lit litAjouter = new Lit(Integer.toString(litDAO.getMaxId()+1),true,'F',new Localisation("main", 1, "D"),"38300111",170000001);
//        litDAO.create(litAjouter);
//        //**update OK
//         lLit.get(1).setCote('P');        
//         litDAO.update(lLit.get(1));
//         //**delete OK
//         litDAO.delete(litAjouter);
        
//        //PrescriptionDAO
//        PrescriptionDAO prescDAO = (PrescriptionDAO)DAOFactory.getPrescriptionDAO();
//        //**find OK
//        Prescription prescr = prescDAO.find(new ArrayList<>(Arrays.asList("idprescription")), new ArrayList<>(Arrays.asList("1")));
//        System.out.println(prescr.toString());
//        //**findmultiple OK
//        ArrayList<Prescription> lprescr = prescDAO.findMultiple(new ArrayList<>(Arrays.asList("idFicheDeSoins")), new ArrayList<>(Arrays.asList("1")));
//        for(Prescription p : lprescr){
//            System.out.println(p.toString());
//        }
//        //create OK (integre a l'utilisation du constructeur classique non reserver a la bd)
//        Prescription prescriptionAjouter = new Prescription(1, "doliprane", "pas d'advil", TypePrescription.MEDICAMENT);
//        //update OK
//        prescriptionAjouter.setObservation("pas d'advil, effervescent");
//        prescDAO.update(prescriptionAjouter);
//        //delete OK
//        prescDAO.delete(prescriptionAjouter);
        
        
        
        
        
        
    }

}
