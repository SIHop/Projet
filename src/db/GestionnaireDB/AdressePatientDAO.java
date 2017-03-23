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
import nf.Adresse.Adresse;

/**
 *
 * @author Loïc
 */
public class AdressePatientDAO implements DAO<Adresse>{
    private String query = "";

    @Override
    public Adresse find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer l'adresse de patient qui respecte toutes les contraintes
        this.query = "SELECT * FROM adressepatient WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

       

        try {
            Statement stmt = AdressePatientDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                return new Adresse(rs.getString("Pays"), rs.getString("ville"), rs.getInt("codePostal"), rs.getString("nomVoie"), rs.getInt("numeroVoie"), rs.getString("typeVoie"), rs.getString("complementAdresse"));                                                                                                                  //litDAO.find()           

            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            //System.out.println("Pas de résultat correspondant");
        }
        return null;
    }

    @Override
    public ArrayList<Adresse> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
         //Créer la requête pour récupérer le personnel qui respecte toutes les contraintes
        this.query = "SELECT * FROM adressepatient WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        ArrayList<Adresse> retour = new ArrayList<>();

        try {
            Statement stmt = AdressePatientDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(new Adresse(rs.getString("Pays"), rs.getString("ville"), rs.getInt("codePostal"), rs.getString("nomVoie"), rs.getInt("numeroVoie"), rs.getString("typeVoie"), rs.getString("complementAdresse")));                                                                                                                  //litDAO.find()           
                }

            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            //System.out.println("Pas de résultat correspondant");
        }
        return retour;
    }
    
    public Adresse create(Adresse obj, int IPP) {
        this.query = "INSERT INTO adressepatient (IPP, pays, numeroVoie,typeVoie,nomVoie,codePostal, ville)"
                + " VALUES (" + IPP + ", '" + obj.getPays().replace("'", "''").toUpperCase() + "'," + obj.getNumeroVoie() + ",'" + obj.getTypeVoie().replace("'", "''") + "','" + obj.getNomVoie().replace("'", "''") + "'," + obj.getCodePostal() + ",'" + obj.getVille().replace("'", "''").toUpperCase()+"')";

        Statement stmt;
        try {
            stmt = AdressePatientDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Adresse update(Adresse obj, int IPP) {
        this.query = "UPDATE adressepatient SET Pays = '" + obj.getPays().replace("'", "''").toUpperCase() + "', numeroVoie = " + obj.getNumeroVoie() + ", typeVoie = '" + obj.getTypeVoie().replace("'", "''")
                + "', nomVoie = '" + obj.getNomVoie().replace("'", "''") + "', codePostal = " + obj.getCodePostal() + ", ville = '" + obj.getVille().replace("'", "''").toUpperCase() + "' WHERE IPP = " + IPP;
        Statement stmt;
        try {
            stmt = AdressePatientDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;

    }
    
    public boolean delete(int IPP) {
        this.query = "DELETE FROM adressepatient WHERE IPP = " + IPP;

        Statement stmt;
        try {
            stmt = AdressePatientDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(IPP) FROM adressepatient";

        Statement stmt;
        try {
            stmt = AdressePatientDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(IPP)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    
    /**
     * Inutilisé car besoin de l'IPP à qui appartient l'adresse pour
     * faire la db Utiliser plutôt create(Adresse obj, String idPersonnel)
     *
     * @param obj
     * @return
     */
    @Override
    public Adresse create(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Inutilisé car besoin de l'IPP à qui appartient l'adresse pour
     * faire la db Utiliser plutôt update(Adresse obj, String idPersonnel)
     *
     * @param obj
     * @return
     */
    @Override
    public Adresse update(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Inutilisé car besoin de l'IPP à qui appartient l'adresse pour
     * faire la db Utiliser plutôt delete(Adresse obj, String idPersonnel)
     *
     * @param obj
     * @return
     */

    @Override
    public Adresse delete(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
