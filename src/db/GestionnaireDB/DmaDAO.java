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
        String IPP = "";
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
            Statement stmt = DmaDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                DAO<Sejour> sejourDAO = DAOFactory.getSejourDAO();
                rs.first();
                IPP = rs.getString("IPP");
                String query2 = "SELECT numeroSejour FROM dma WHERE IPP = " + rs.getString("IPP");
                System.out.println(query2);
                ResultSet rs2 = stmt.executeQuery(query2);

                ArrayList<String> argSejour = new ArrayList<>();
                argSejour.add("numeroSejour");
                rs2.first();
                ArrayList<String> valSejour = new ArrayList<>();
                //travaille pour la 1ère valeur
                valSejour.add(rs2.getString("numeroSejour"));
                if (sejourDAO.find(argSejour, valSejour) != null) {

                    listeSejour.add(sejourDAO.find(argSejour, valSejour));
                    //si il y a d'autre sejour
                    while (rs2.next()) {
                        valSejour.set(0, rs2.getString("numeroSejour"));
                        listeSejour.add(sejourDAO.find(argSejour, valSejour));
                    }
                }

                return new DMA(listeSejour, IPP);
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
    public ArrayList<DMA> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<DMA> lDma = new ArrayList<>();
        String IPP = "";
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
            Statement stmt = DmaDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                DAO<Sejour> sejourDAO = DAOFactory.getSejourDAO();
                while (rs.next()) {

                    IPP = rs.getString("IPP");
                    String query2 = "SELECT numeroSejour FROM dma WHERE IPP = " + rs.getString("IPP");
                    System.out.println(query2);
                    Statement stmt2 = DmaDAO.connect.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(query2);

                    ArrayList<String> argSejour = new ArrayList<>();
                    argSejour.add("numeroSejour");

                    rs2.first();
                    ArrayList<String> valSejour = new ArrayList<>();
                    //travaille pour la 1ère valeur
                    valSejour.add(rs2.getString("numeroSejour"));
                    listeSejour.add(sejourDAO.find(argSejour, valSejour));
                    //si il y a d'autre sejour
                    while (rs2.next()) {
                        valSejour.set(0, rs2.getString("numeroSejour"));
                        listeSejour.add(sejourDAO.find(argSejour, valSejour));
                    }

                    lDma.add(new DMA(listeSejour, IPP));
                }

            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DmaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                stmt = DmaDAO.connect.createStatement();
                int rowEffected = stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DmaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                stmt = DmaDAO.connect.createStatement();
                int rowEffected = stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DmaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                stmt = DmaDAO.connect.createStatement();
                int rowEffected = stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(DmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(numeroSejour) FROM dma";

        Statement stmt;
        try {
            stmt = DmaDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(numeroSejour)");
        } catch (SQLException ex) {
            Logger.getLogger(DmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    /**
     * Permet de retrouver pour un IPP donnez le sejour en cours, si aucune
     * sejour n'est en cours, retourne null
     *
     * @param IPP
     * @return
     */
    public Sejour findSejourActuel(int IPP) {
        DMA dma = this.find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(Integer.toString(IPP))));
        Sejour retour = null;
        for (Sejour s : dma.getListeDeSejour()) {
            if (s.isEnCours()) {
                retour = s;
            }
        }
        return retour;
    }

    public void ajoutDunSejour(DMA dma, String numeroSejour) {

        this.query = "INSERT INTO dma (IPP, numeroSejour)"
                + " VALUES (" + dma.getIPP() + "," + numeroSejour + ");";

        Statement stmt;
        try {
            stmt = DmaDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
