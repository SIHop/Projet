/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import GestionDexploitation.Medecin;
import GestionDexploitation.Personnel;
import GestionDexploitation.RangMedecin;
import GestionDexploitation.Service;
import GestionDexploitation.Sexe;
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
public class PersonelDAO implements DAO<Personnel> {

    String query = null;

    @Override
    public Personnel find(ArrayList<String> arg, ArrayList<String> val) {

        this.query = "SELECT * FROM personnel where ";
        query += arg.get(0) + " = " + val.get(0);

        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);
        try {
            Statement stmt = this.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.next();
                return new Medecin("102", RangMedecin.CHEF_DE_CLINIQUE, null, rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.HOMME, null, null, null, null, null);
                
            }else{
                System.out.println("Aucun résultat n'a était trouver");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résulta correspondent");
        }

        return null;

    }

    @Override
    public ArrayList<Personnel> findMultiple(String[] arg, String[] val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personnel create(Personnel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personnel update(Personnel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personnel delete(Personnel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
