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
import nf.DPI.DM.Prescription;
import nf.DPI.DM.TypePrescription;


/**
 *
 * @author Loïc
 */
public class PrescriptionDAO implements DAO<Prescription>{

    private String query = "";
    
    @Override
    public Prescription find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer la prescription qui respecte toutes les contraintes
        this.query = "SELECT * FROM prescription WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        try {
            Statement stmt = PrescriptionDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                return new Prescription(rs.getInt("idprescription"),rs.getInt("idFicheDeSoins"), rs.getString("contenuePrescription"),rs.getString("observation"), TypePrescription.valueOf(rs.getString("typePrescription")));
            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return null;
    }

    @Override
    public ArrayList<Prescription> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer la prescription qui respecte toutes les contraintes
        this.query = "SELECT * FROM prescription WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);
        
        ArrayList<Prescription> retour = new ArrayList<>();

        try {
            Statement stmt = PrescriptionDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while(rs.next()){
                    retour.add(new Prescription(rs.getInt("idprescription"),rs.getInt("idFicheDeSoins"), rs.getString("contenuePrescription"),rs.getString("observation"), TypePrescription.valueOf(rs.getString("typePrescription"))));
                }
                
            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return retour;
    }

    @Override
    public Prescription create(Prescription obj) {
        this.query = "INSERT INTO prescription (idprescription, idFicheDeSoins, typePrescription, contenuePrescription, observation)"
                + " VALUES (" + obj.getIdPrescription()+ "," + obj.getIdFicheDeSoins() + ",'" + obj.getTypePrescription().getLabelle() + "','" + obj.getPrescription().replace("'", "''") + "','"  + obj.getObservation().replace("'", "''")+ "')";

        ;
        System.out.println(query);
        Statement stmt;
        try {
            stmt = PrescriptionDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Prescription update(Prescription obj) {
        this.query = "UPDATE prescription SET idFicheDeSoins = " + obj.getIdFicheDeSoins() + ", typePrescription = '" +  obj.getTypePrescription().getLabelle() + "', contenuePrescription = '"
                +obj.getPrescription().replace("'", "''") + "', observation = '" + obj.getObservation().replace("'", "''") + "' WHERE idprescription = " + obj.getIdPrescription();

        Statement stmt;
        try {
            stmt = PrescriptionDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Prescription delete(Prescription obj) {
        this.query = "DELETE FROM prescription WHERE idprescription = " + obj.getIdPrescription();

        Statement stmt;
        try {
            stmt = PrescriptionDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idprescription) FROM prescription";

        Statement stmt;
        try {
            stmt = PrescriptionDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(idprescription)");
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
    
}
