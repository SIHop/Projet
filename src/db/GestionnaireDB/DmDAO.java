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
import nf.DPI.DM.DM;
import nf.GestionDexploitation.Lit;

/**
 *
 * @author Loïc
 */
public class DmDAO implements DAO<DM>{
private String query = "";

    @Override
    public DM find(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére le lit qui respecte tout les contrainte
        this.query = "SELECT * FROM dm WHERE ";
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
                return new DM();
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
    public ArrayList<DM> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<Lit> retour = new ArrayList<>();

        //Crée la requete pour recupére la liste de lit qui respecte tout les contrainte
        this.query = "SELECT * FROM Lit WHERE ";
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
                    retour.add(new Lit(rs.getString("idLit"), rs.getBoolean("estOccuper"), rs.getString("cote").charAt(0), null, rs.getString("idService"), rs.getString("IPP")));
                }

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
    public DM create(DM obj) {
        int occuper = 0;
        if (obj.isIsOccuped()) {
            occuper = 1;
        }

        this.query = "INSERT INTO Lit (idLit, IPP, idService,estOccuper,cote)"
                + " VALUES (" + obj.getIdentifient() + "," + obj.getIPPoccupent() + "," + obj.getService().getCodeService() + "," + occuper + "," + obj.getCote() + ")";

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public DM update(DM obj) {
        int occuper = 0;
        if (obj.isIsOccuped()) {
            occuper = 1;
        }

        this.query = "UPDATE Lit SET IPP = " + obj.getIPPoccupent() + ", idService = " + obj.getService().getCodeService() + ", estOccuper = "
                + occuper + ", cote = " + obj.getCote() + "WHERE idLit = " + obj.getIdentifient();

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public DM delete(DM obj) {
        this.query = "DELETE FROM Lit WHERE idLit = " + obj.getIdentifient();

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idLit) FROM Lit";

        Statement stmt;
        try {
            stmt = PersonelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(idLit)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
}
