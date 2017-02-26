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
        //Crée la requete pour recupére le personelle qui respecte tout les contrainte
        this.query = "SELECT * FROM adressepersonnel WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        //code pour recupere le medecin responsable
        DAO<Personnel> personelleDAO = DAOFactory.getPersonelDAO();

        try {
            Statement stmt = this.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.next();
                return new Adresse("France",rs.getString("ville"),rs.getInt("codePostal"),"", rs.getInt("numeroVoie"), rs.getString("typeVoie"),rs.getString("complementAdresse"));                                                                                                                  //litDAO.find()           
                                                                                         //rs.getString("nomVoie"),
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adresse create(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adresse update(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adresse delete(Adresse obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
