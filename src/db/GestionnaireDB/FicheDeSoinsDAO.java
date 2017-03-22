/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nf.Adresse.DateT;
import nf.DPI.DM.Acte;
import nf.DPI.DM.FicheDeSoins;
import nf.DPI.DM.Prescription;
import nf.DPI.DM.Resultat;
import nf.GestionDexploitation.Personnel;

/**
 *
 * @author Loïc
 */
public class FicheDeSoinsDAO implements DAO<FicheDeSoins> {

    private String query = "";

    @Override
    public FicheDeSoins find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer la fiche de soins qui respecte toutes les contraintes
        this.query = "SELECT * FROM ficheDeSoins WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        //Instanciation des DAO nécessaire à la création d'une fiche de soins
        DAO<Acte> acteDao = DAOFactory.getActeDAO();
        DAO<Prescription> prescriptionDao = DAOFactory.getPrescriptionDAO();
        DAO<Resultat> resultatDao = DAOFactory.getResultatDAO();
        DAO<Personnel> personnelDao = DAOFactory.getPersonelDAO();

        try {
            Statement stmt = FicheDeSoinsDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //Récupération des données pour trouver les actes, prescriptions et résultats de la fds(fiche de soins), plus du personnel créateur
            ArrayList<String> argData = new ArrayList<>();
            ArrayList<String> valData = new ArrayList<>();
            argData.add("idFicheDeSoins");

            ArrayList<String> argCreateur = new ArrayList<>();
            ArrayList<String> valCreateur = new ArrayList<>();
            argCreateur.add("idPersonnel");

            if (rs.isBeforeFirst()) {
                rs.first();
                valData.add(rs.getString("idFicheDeSoins"));
                valCreateur.add(rs.getString("createur"));
                Personnel p = personnelDao.find(argCreateur, valCreateur);

                return new FicheDeSoins(rs.getInt("idFicheDeSoins"), acteDao.findMultiple(argData, valData), prescriptionDao.findMultiple(argData, valData), resultatDao.findMultiple(argData, valData), p,
                        new DateT(rs.getString("dateCreationFiche")), rs.getString("numeroSejour"));
            } else {
                System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultat correspondant");
        }
        return null;
    }

    @Override
    public ArrayList<FicheDeSoins> findMultiple(ArrayList<String> arg, ArrayList<String> val) {

        ArrayList<FicheDeSoins> lfds = new ArrayList<>();
        //Créer la requête pour récupérer la fiche de soins qui respecte toutes les contraintes
        this.query = "SELECT * FROM ficheDeSoins WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        //Instanciation des DAO nécessaire à la création d'une fiche de soins
        DAO<Acte> acteDao = DAOFactory.getActeDAO();
        DAO<Prescription> prescriptionDao = DAOFactory.getPrescriptionDAO();
        DAO<Resultat> resultatDao = DAOFactory.getResultatDAO();
        DAO<Personnel> personnelDao = DAOFactory.getPersonelDAO();

        try {
            Statement stmt = FicheDeSoinsDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    //Récupération des données pour trouver les actes, prescriptions et résultats de la fds, plus du personnel créateur
                    ArrayList<String> argData = new ArrayList<>();
                    ArrayList<String> valData = new ArrayList<>();
                    argData.add("idFicheDeSoins");
                    valData.add(rs.getString("idFicheDeSoins"));

                    ArrayList<String> argCreateur = new ArrayList<>();
                    ArrayList<String> valCreateur = new ArrayList<>();
                    argCreateur.add("idPersonnel");
                    valCreateur.add(rs.getString("createur"));
                    Personnel p = personnelDao.find(argCreateur, valCreateur);
                    
                    lfds.add(new FicheDeSoins(rs.getInt("idFicheDeSoins"), acteDao.findMultiple(argData, valData), prescriptionDao.findMultiple(argData, valData), resultatDao.findMultiple(argData, valData), p,
                            new DateT(rs.getString("dateCreationFiche")), rs.getString("numeroSejour")));
                }

            } else {
                System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultat correspondant");
        }
        return lfds;
    }

    @Override
    public FicheDeSoins create(FicheDeSoins obj) {

        this.query = "INSERT INTO ficheDeSoins (idFicheDeSoins, numeroSejour, createur,dateCreationFiche)"
                + " VALUES (" + obj.getIdFicheDeSoins() + "," + obj.getNumeroSejour() + "," + obj.getCreateur().getIdPersonel() + ",'" + obj.getDateDeCreation().toString() + "')";

        Statement stmt;
        try {
            stmt = FicheDeSoinsDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public FicheDeSoins update(FicheDeSoins obj) {
        this.query = "UPDATE ficheDeSoins SET numeroSejour = " + obj.getNumeroSejour() + ", createur = " + obj.getCreateur().getIdPersonel() + ", dateCreationFiche = '"
                + obj.getDateDeCreation().toString() + "' WHERE idFicheDeSoins = " + obj.getIdFicheDeSoins();

        Statement stmt;
        try {
            stmt = FicheDeSoinsDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public FicheDeSoins delete(FicheDeSoins obj) {
        this.query = "DELETE FROM ficheDeSoins WHERE idFicheDeSoins = " + obj.getIdFicheDeSoins();

        Statement stmt;
        try {
            stmt = FicheDeSoinsDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idFicheDeSoins) FROM ficheDeSoins";

        Statement stmt;
        try {
            stmt = FicheDeSoinsDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(idFicheDeSoins)");
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
