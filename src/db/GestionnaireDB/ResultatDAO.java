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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import nf.DPI.DM.Resultat;

/**
 *
 * @author Loïc
 */
public class ResultatDAO implements DAO<Resultat> {

    private String query = "";

    @Override
    public Resultat find(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére le résultat qui respecte tout les contrainte
        this.query = "SELECT * FROM resultat WHERE ";
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
                //récupération de tout les annalyse et leur resultat
                ArrayList<String> listeAnnalyse = new ArrayList<>(Arrays.asList(rs.getString("annalyse").split("\\s*;\\s*")));
                ArrayList<String> listeResultat = new ArrayList<>(Arrays.asList(rs.getString("contenuResultat").split("\\s*;\\s*")));
                return new Resultat(rs.getInt("idresultat"), rs.getInt("idPrescription"), rs.getInt("idFicheDeSoins"), listeAnnalyse, listeResultat);
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
    public ArrayList<Resultat> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére la liste de résultat qui respecte tout les contrainte
        this.query = "SELECT * FROM resultat WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        ArrayList<Resultat> retour = new ArrayList<>();

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    //récupération de tout les annalyse et leur resultat
                    ArrayList<String> listeAnnalyse = new ArrayList<>(Arrays.asList(rs.getString("annalyse").split("\\s*;\\s*")));
                    ArrayList<String> listeResultat = new ArrayList<>(Arrays.asList(rs.getString("contenuResultat").split("\\s*;\\s*")));
                    retour.add(new Resultat(rs.getInt("idresultat"), rs.getInt("idPrescription"), rs.getInt("idFicheDeSoins"), listeAnnalyse, listeResultat));
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

    @Override
    public Resultat create(Resultat obj) {
        String resultat = "";
        String annalyse = "";

        for (String[] s : obj.getListeResultats()) {
            resultat += s[1] + ";";
            annalyse += s[0] + ";";
        }

        this.query = "INSERT INTO resultat (idresultat, idFicheDeSoins, idPrescription, annalyse,contenuResultat)"
                + " VALUES (" + obj.getIdResultat() + "," + obj.getIdFicheDeSoins() + "," + obj.getIdPrescription() + ",'" + annalyse.replace("'", "''") + "','" + resultat.replace("'", "''") + "')";

        System.out.println(query);
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
    public Resultat update(Resultat obj) {
        String resultat = "";
        String annalyse = "";

        for (String[] s : obj.getListeResultats()) {

            resultat += s[1] + ";";
            annalyse += s[0] + ";";
        }

        this.query = "UPDATE resultat SET idFicheDeSoins = " + obj.getIdFicheDeSoins() + ", idPrescription = " + obj.getIdPrescription() + ", annalyse = '"
                + annalyse + "', contenuResultat = '" + resultat + "' WHERE idresultat = " + obj.getIdResultat();

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
    public Resultat delete(Resultat obj) {
        this.query = "DELETE FROM resultat WHERE idresultat = " + obj.getIdResultat();

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
        this.query = "SELECT max(idresultat) FROM resultat";

        Statement stmt;
        try {
            stmt = PersonelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(idresultat)");
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
