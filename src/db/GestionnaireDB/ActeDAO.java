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
import nf.DPI.DM.Acte;
import nf.DPI.DM.Code;
import nf.DPI.DM.TypeActe;
import nf.GestionDexploitation.Lit;

/**
 *
 * @author Loïc
 */
public class ActeDAO implements DAO<Acte> {

    private String query = "";

    @Override
    public Acte find(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére l'acte qui respecte tout les contrainte
        this.query = "SELECT * FROM acte WHERE ";
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
                return new Acte(rs.getInt("idActe"), rs.getInt("idFicheDeSoins"), Code.valueOf(rs.getString("code")), rs.getInt("coef"), rs.getString("observation"), TypeActe.valueOf(rs.getString("typeActe")));
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
    public ArrayList<Acte> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<Acte> retour = new ArrayList<>();

        //Crée la requete pour recupére la liste d'acte qui respecte tout les contrainte
        this.query = "SELECT * FROM acte WHERE ";
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
                while(rs.next()){
                    retour.add(new Acte(rs.getInt("idActe"), rs.getInt("idFicheDeSoins"), Code.valueOf(rs.getString("code")), rs.getInt("coef"), rs.getString("observation"), TypeActe.valueOf(rs.getString("typeActe"))));
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
    public Acte create(Acte obj) {
        this.query = "INSERT INTO acte (idActe, idFicheDeSoins, code, coef,typeActe, observation)"
                + " VALUES (" + obj.getIdActe() + "," + obj.getIdFicheDeSoins() + "," + obj.getCode().getCode() + "," + obj.getCoef() + "," + obj.getTypeActe().getTypeActe() + "," + obj.getObservations() + ")";

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
    public Acte update(Acte obj) {
        this.query = "UPDATE acte SET idFicheDeSoins = " + obj.getIdFicheDeSoins() + ", code = " + obj.getCode().getCode() + ", coef = "
                + obj.getCoef() + ", typeActe =" + obj.getTypeActe().getTypeActe() + ", observation = " + obj.getObservations() + " WHERE idActe = " + obj.getIdActe();

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
    public Acte delete(Acte obj) {
        this.query = "DELETE FROM acte WHERE idActe = " + obj.getIdActe();

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
        this.query = "SELECT max(idActe) FROM acte";

        Statement stmt;
        try {
            stmt = PersonelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(idActe)");
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
