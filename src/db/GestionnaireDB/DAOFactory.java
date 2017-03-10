/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import nf.Adresse.Adresse;
import nf.DPI.DM.Acte;
import nf.DPI.DM.FicheDeSoins;
import nf.DPI.DM.Prescription;
import nf.DPI.DM.Resultat;
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.Sejour;
import nf.DPI.DPI;
import nf.DPI.Historique;
import nf.GestionDexploitation.Lit;
import nf.GestionDexploitation.Personnel;
import nf.GestionDexploitation.Service;

/**
 *
 * @author Loïc
 */
public class DAOFactory {

    public static DAO<Personnel> getPersonelDAO() {
        return new PersonnelDAO();
    }

    public static DAO<Service> getServiceDAO() {
        return new ServiceDAO();
    }

    public static AdressePersonnelDAO getAdressePersonnelDAO() {
        return new AdressePersonnelDAO();
    }

    public static DAO<Lit> getLitDAO() {
        return new LitDAO();
    }

    public static AdressePatientDAO getAdressePatientDAO() {
        return new AdressePatientDAO();
    }

    public static DAO<Sejour> getSejourDAO() {
        return new SejourDAO();
    }

    public static DAO<DMA> getDmaDAO() {
        return new DmaDAO();
    }
    
    public static DAO<FicheDeSoins> getFicheDeSoinsDAO() {
        return new FicheDeSoinsDAO();
    }
    
    public static DAO<Acte> getActeDAO() {
        return new ActeDAO();
    }
    public static DAO<Prescription> getPrescriptionDAO() {
        return new PrescriptionDAO();
    }
    public static DAO<Resultat> getResultatDAO() {
        return new ResultatDAO();
    }
    
    public static DAO<DPI> getDpiDAO(){
        return new DpiDAO();
    }
    
    public static DAO<Historique> getHistoriqueDAO(){
        return new HistoriqueDAO();
    }
}
