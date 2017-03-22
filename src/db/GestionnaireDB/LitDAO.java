/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import nf.GestionDexploitation.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nf.GestionDexploitation.Localisation;

/**
 *
 * @author Loïc
 */
public class LitDAO implements DAO<Location> {

    private String query = "";

    @Override
    public Location find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer le lit qui respecte toutes les contraintes
        this.query = "SELECT * FROM lit WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        try {
            Statement stmt = LitDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                return new Location(rs.getString("idLit"), rs.getBoolean("estOccuper"), rs.getString("cote").charAt(0), new Localisation(rs.getString("batiment"), rs.getInt("etage"), rs.getString("couloir")), rs.getString("idService"), rs.getInt("IPP"));
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
    public ArrayList<Location> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<Location> retour = new ArrayList<>();

        //Créer la requête pour récupérer la liste de lit qui respecte toutes les contraintes
        this.query = "SELECT * FROM lit WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        try {
            Statement stmt = LitDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(new Location(rs.getString("idLit"), rs.getBoolean("estOccuper"), rs.getString("cote").charAt(0), new Localisation(rs.getString("batiment"), rs.getInt("etage"), rs.getString("couloir")), rs.getString("idService"), rs.getInt("IPP")));
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

    @Override
    public Location create(Location obj) {
        int occuper = 0;
        if (obj.isIsOccuped()) {
            occuper = 1;
        }
        //si l'ipp a les 9 chiffres demandés : set ipp
        String ipp = null;
        if(obj.getIPPoccupent() > 010000000){
            ipp = "'" + Integer.toString(obj.getIPPoccupent()) + "'";
        }

        this.query = "INSERT INTO lit (idLit, IPP, idService,estOccuper,cote, batiment, etage,couloir)"
                + " VALUES ('" + obj.getIdLit() + "'," + ipp + "," + obj.getService().getCodeService() + "," + occuper + ",'" + obj.getCote() + "','"
                + obj.getLocalisation().getBatiment().replace("'", "''") + "'," + obj.getLocalisation().getEtage() + ",'" + obj.getLocalisation().getCouloir() + "')";

        System.out.println(query);
        Statement stmt;
        try {
            stmt = LitDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Location update(Location obj) {
        int occuper = 0;
        String IPPcorriger = Integer.toString(obj.getIPPoccupent());
        if (obj.isIsOccuped()) {
            occuper = 1;
        }
        if(obj.getIPPoccupent() == 0){
            IPPcorriger = "NULL";
        }
        

        this.query = "UPDATE lit SET IPP = " + IPPcorriger + ", idService = '" + obj.getService().getCodeService() + "', estOccuper = "
                + occuper + ", cote = '" + obj.getCote() + "', batiment = '" + obj.getLocalisation().getBatiment().replace("'", "''") + "', etage = " + obj.getLocalisation().getEtage()
                + ", couloir = '" + obj.getLocalisation().getCouloir() + "' WHERE idLit = '" + obj.getIdLit() + "'";

        Statement stmt;
        try {
            stmt = LitDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Location delete(Location obj) {
        this.query = "DELETE FROM lit WHERE idLit = " + obj.getIdLit();

        Statement stmt;
        try {
            stmt = LitDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idLit) FROM lit";

        Statement stmt;
        try {
            stmt = LitDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(idLit)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
