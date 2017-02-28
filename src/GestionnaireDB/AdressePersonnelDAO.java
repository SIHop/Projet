/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import Adresse.Adresse;
import GestionDexploitation.Personnel;
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
        //Crée la requete pour recupére l'adresse de personnel qui respecte tout les contrainte
        this.query = "SELECT * FROM adressepersonnel WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

       

        try {
            Statement stmt = AdressePersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                return new Adresse(rs.getString("Pays"), rs.getString("ville"), rs.getInt("codePostal"), rs.getString("nomVoie"), rs.getInt("numeroVoie"), rs.getString("typeVoie"), rs.getString("complementAdresse"));                                                                                                                  //litDAO.find()           

            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return null;
    }

    @Override
    public ArrayList<Adresse> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére le personelle qui respecte tout les contrainte
        this.query = "SELECT * FROM adressepersonnel WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        ArrayList<Adresse> retour = new ArrayList<>();

        try {
            Statement stmt = AdressePersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(new Adresse(rs.getString("Pays"), rs.getString("ville"), rs.getInt("codePostal"), rs.getString("nomVoie"), rs.getInt("numeroVoie"), rs.getString("typeVoie"), rs.getString("complementAdresse")));                                                                                                                  //litDAO.find()           
                }

            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return retour;
    }

    public Adresse create(Adresse obj, String idPersonnel) {
        this.query = "INSERT INTO adressepersonnel (idPersonnel, pays, numeroVoie,typeVoie,nomVoie,codePostal, ville)"
                + " VALUES (" + idPersonnel + ", " + obj.getPays() + "," + obj.getNumeroVoie() + "," + obj.getTypeVoie() + "," + obj.getNomVoie() + "," + obj.getCodePostal() + "," + obj.getVille()+")";

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Adresse update(Adresse obj, String idPersonnel) {
        this.query = "UPDATE adressepersonnel SET Pays = " + obj.getPays() + ", numeroVoie = " + obj.getNumeroVoie() + ", typeVoie = " + obj.getTypeVoie()
                + ", nomVoie = " + obj.getNomVoie() + ", codePostal = " + obj.getCodePostal() + ", ville = " + obj.getVille() + "WHERE idPersonnel = " + idPersonnel;
        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;

    }

    /**
     * Inutiliser car besoin de l'id du personnel à qui appartien l'adresse pour
     * faire la db Utiliser plus tôt delete(String idPersonnel)
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
     * @return false si la suppression à échouer
     */
    public boolean delete(String idPersonnel) {
        this.query = "DELETE FROM adressepersonnel WHERE idPersonnel = " + idPersonnel;

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idPersonnel) FROM adressepersonnel";

        Statement stmt;
        try {
            stmt = PersonelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(idPersonnel)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    /**
     * Inutiliser car besoin de l'id du personnel à qui appartien l'adresse pour
     * faire la db Utiliser plus tôt create(Adresse obj, String idPersonnel)
     *
     * @param obj
     * @return
     */
    @Override
    public Adresse create(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Inutiliser car besoin de l'id du personnel a qui appartien l'adresse pour
     * faire la db Utiliser plus tôt update(Adresse obj, String idPersonnel)
     *
     * @param obj
     * @return
     */
    @Override
    public Adresse update(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
