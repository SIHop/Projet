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
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.Sejour;

/**
 *
 * @author Loïc
 */
public class DmaDAO implements DAO<DMA> {

    private String query = "";

    @Override
    public DMA find(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére le sejour qui respecte tout les contrainte
        this.query = "SELECT * FROM dma WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);
        ArrayList<Sejour> listeSejour = new ArrayList<>();
        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                DAO<Sejour> sejourDAO = DAOFactory.getSejourDAO();
                rs.first();
                ResultSet rs2 = stmt.executeQuery("SELECT numeroSejour FROM dma WHERE IPP = " + rs.getString("IPP"));

                ArrayList<String> argSejour = new ArrayList<>();
                argSejour.add("numeroSejour");
                rs2.first();
                ArrayList<String> valSejour = new ArrayList<>();
                argSejour.add(rs2.getString("numeroSejour"));
                while (rs2.next()) {
                    listeSejour.add(sejourDAO.find(argSejour, valSejour));
                    argSejour.set(0, rs2.getString("numeroSejour"));
                }

                return new DMA(listeSejour, rs.getString("IPP"));
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
    public ArrayList<DMA> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<DMA> lDma = new ArrayList<>();

        //Crée la requete pour recupére le sejour qui respecte tout les contrainte
        this.query = "SELECT * FROM dma WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);
        ArrayList<Sejour> listeSejour = new ArrayList<>();
        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                DAO<Sejour> sejourDAO = DAOFactory.getSejourDAO();
                rs.first();
                ResultSet rs2 = stmt.executeQuery("SELECT numeroSejour FROM dma WHERE IPP = " + rs.getString("IPP"));

                ArrayList<String> argSejour = new ArrayList<>();
                argSejour.add("numeroSejour");
                rs2.first();
                ArrayList<String> valSejour = new ArrayList<>();
                argSejour.add(rs2.getString("numeroSejour"));
                while (rs2.next()) {
                    listeSejour.add(sejourDAO.find(argSejour, valSejour));
                    argSejour.set(0, rs2.getString("numeroSejour"));
                }

                lDma.add(new DMA(listeSejour, rs.getString("IPP")));
            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return lDma;
    }

    @Override
    public DMA create(DMA obj) {
        for (Sejour s : obj.getListeDeSejour()) {
            this.query = "INSERT INTO dma (IPP, numeroSejour)"
                    + " VALUES (" + obj.getIPP() + "," + s.getNumeroDeSejour() + ");";

            Statement stmt;
            try {
                stmt = ServiceDAO.connect.createStatement();
                int rowEffected = stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return obj;
    }

    @Override
    public DMA update(DMA obj) {
        for (Sejour s : obj.getListeDeSejour()) {
            this.query = "UPDATE dma SET IPP = " + obj.getIPP() + " WHERE numeroSejour = " + s.getNumeroDeSejour() + ";\n";
            System.out.println(query);

            Statement stmt;
            try {
                stmt = ServiceDAO.connect.createStatement();
                int rowEffected = stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return obj;
    }

    @Override
    public DMA delete(DMA obj) {
        for (Sejour s : obj.getListeDeSejour()) {

            this.query = "DELETE FROM dma WHERE numeroSejour = " + s.getNumeroDeSejour();

            Statement stmt;
            try {
                stmt = ServiceDAO.connect.createStatement();
                int rowEffected = stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(numeroSejour) FROM dma";

        Statement stmt;
        try {
            stmt = PersonelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(numeroSejour)");
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
