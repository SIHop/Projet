/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import nf.Adresse.Adresse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Loïc
 */
public class AdressePersonnelDAO implements DAO<Adresse> {

    private String query = "";

    @Override
    public Adresse find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer l'adresse de personnel qui respecte toutes les contraintes
        this.query = "SELECT * FROM adressepersonnel WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        try {
            Statement stmt = AdressePersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                return new Adresse(rs.getString("Pays"), rs.getString("ville"), rs.getInt("codePostal"), rs.getString("nomVoie"), rs.getInt("numeroVoie"), rs.getString("typeVoie"), rs.getString("complementAdresse"));                                                                                                                  //litDAO.find()           

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
    public ArrayList<Adresse> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer le personel qui respecte toutes les contraintes
        this.query = "SELECT * FROM adressepersonnel WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
       

        ArrayList<Adresse> retour = new ArrayList<>();

        try {
            Statement stmt = AdressePersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(new Adresse(rs.getString("Pays"), rs.getString("ville"), rs.getInt("codePostal"), rs.getString("nomVoie"), rs.getInt("numeroVoie"), rs.getString("typeVoie"), rs.getString("complementAdresse")));                                                                                                                  //litDAO.find()           
                }

            } else {
                System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultat correspondant");
        }
        return retour;
    }

    public Adresse create(Adresse obj, int idPersonnel) {
        this.query = "INSERT INTO adressepersonnel (idPersonnel, pays, numeroVoie,typeVoie,nomVoie,codePostal, ville)"
                + " VALUES (" + idPersonnel + ", '" + obj.getPays().replace("'", "''").toUpperCase() + "'," + obj.getNumeroVoie() + ",'" + obj.getTypeVoie().replace("'", "''") + "','" + obj.getNomVoie().replace("'", "''") + "'," + obj.getCodePostal() + ",'" + obj.getVille().replace("'", "''").toUpperCase() + "')";

        
        Statement stmt;
        try {
            stmt = AdressePersonnelDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Adresse update(Adresse obj, int idPersonnel) {
        this.query = "UPDATE adressepersonnel SET Pays = '" + obj.getPays().replace("'", "''").toUpperCase() + "', numeroVoie = " + obj.getNumeroVoie() + ", typeVoie = '" + obj.getTypeVoie().replace("'", "''")
                + "', nomVoie = '" + obj.getNomVoie().replace("'", "''") + "', codePostal = " + obj.getCodePostal() + ", ville = '" + obj.getVille().replace("'", "''").toUpperCase() + "' WHERE idPersonnel = " + idPersonnel;
        Statement stmt;
        try {
            stmt = AdressePersonnelDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;

    }

    /**
     * Inutilisé car besoin de l'id du personnel à qui appartient l'adresse pour
     * faire la db Utiliser plutôt delete(String idPersonnel)
     *
     * @param obj
     * @return
     */
    @Override
    public Adresse delete(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param idPersonnel
     * @return false si la suppression a échoué
     */
    public boolean delete(int idPersonnel) {
        this.query = "DELETE FROM adressepersonnel WHERE idPersonnel = " + idPersonnel;

        Statement stmt;
        try {
            stmt = AdressePersonnelDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idPersonnel) FROM adressepersonnel";

        Statement stmt;
        try {
            stmt = AdressePersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(idPersonnel)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    /**
     * Inutilisé car besoin de l'id du personnel à qui appartient l'adresse pour
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
     * Inutilisé car besoin de l'id du personnel à qui appartient l'adresse pour
     * faire la db Utiliser plutôt update(Adresse obj, String idPersonnel)
     *
     * @param obj
     * @return
     */
    @Override
    public Adresse update(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
