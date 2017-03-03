/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import nf.DPI.Historique;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nf.Adresse.DateT;
import nf.DPI.DMA.IPP;

/**
 *
 * @author Loïc
 */
public class HistoriqueDAO implements DAO<Historique>{
    private String query = "";

    @Override
    public Historique find(ArrayList<String> arg, ArrayList<String> val) {
        //Créait la requete pour recupére les informations du DPI qui respecte toutes les contraintes
        this.query = "SELECT * FROM historique WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                return new Historique(new IPP(rs.getInt("IPP")),new DateT(rs.getString("dateDeces")),new DateT(rs.getString("dateFinArchivage")));
            } 
            else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return null;  
    }

    @Override
    public ArrayList<Historique> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<Historique> retour = new ArrayList<>();

        //Crée la requete pour recupére la liste de lit qui respecte tout les contrainte
        this.query = "SELECT * FROM historique WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    
                    retour.add(new Historique(new IPP(rs.getInt("IPP")),new DateT(rs.getString("dateDeces")),new DateT(rs.getString("dateFinArchivage"))));
                }

            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return null;
    }

    @Override
    public Historique create(Historique obj) {
        this.query = "INSERT INTO historique (IPP,dateDeces,dateFinArchivage)" 
                + " VALUES (" + obj.getIpp()+ "," + obj.getDateDeces()+ "," + obj.getDateFinArchivage()+")";

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(HistoriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Historique update(Historique obj) {
        this.query = "UPDATE historique SET dateDeces = "+obj.getDateDeces() + ", dateFinArchivage = "+ obj.getDateFinArchivage()+"WHERE IPP = " + obj.getIpp();

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(HistoriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }
 /** 
  * delete ne detruit que les données relative à la qualité 
  * de l'archivage. Pour que le dossier patient soit suprimé en 
  * entier se reporter au nf.historique.
  * @param obj
  * @return 
  */
    @Override
    public Historique delete(Historique obj) {
        
        this.query = "DELETE FROM historique WHERE IPP = " + obj.getIpp();

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(HistoriqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
