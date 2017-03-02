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
import nf.Adresse.Adresse;
import nf.Adresse.DateT;
import nf.DPI.DMA.IPP;
import nf.DPI.DPI;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Lit;
import nf.GestionDexploitation.Sexe;

/**
 *
 * @author audre
 */
public class DpiDAO implements DAO<DPI> {
    private String query = "";
    
    @Override
    public DPI find(ArrayList<String> arg, ArrayList<String> val) {
        //Créait la requete pour recupére les informations du DPI qui respecte toutes les contraintes
        this.query = "SELECT * FROM dpi WHERE ";
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
                DAO<Adresse> adresseDAO = DAOFactory.getAdressePatient();
                ArrayList<String> argAdresse = new ArrayList<>(); argAdresse.add("IPP");
                ArrayList<String> valAdresse = new ArrayList<>(); valAdresse.add(rs.getString("IPP"));
                
                DAO<Lit> litDAO = DAOFactory.getLitDAO();
                ArrayList<String> argLit = new ArrayList<>(); argLit.add("IPP");
                ArrayList<String> valLit = new ArrayList<>(); valLit.add(rs.getString("IPP"));
                
                return new DPI(rs.getString("nomNaissance"),rs.getString("nomUsage"),rs.getString("prenom"),adresseDAO.find(argAdresse,valAdresse), new IPP(rs.getInt("IPP")),new DateT(rs.getString("dateNaissance")).getC().getTime(), null, new InformationDeContact(rs.getString("telephoneFixe"),rs.getString("telephonePortable"),rs.getString("mail"),null),litDAO.find(argLit,valLit), null, null,Sexe.valueOf(rs.getString("sexe")) );
            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return null;  
    }

    @Override
    public ArrayList<DPI> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<DPI> retour = new ArrayList<>();

        //Crée la requete pour recupére la liste de lit qui respecte tout les contrainte
        this.query = "SELECT * FROM dpi WHERE ";
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
                    DAO<Adresse> adresseDAO = DAOFactory.getAdressePatient();
                    ArrayList<String> argAdresse = new ArrayList<>(); argAdresse.add("IPP");
                    ArrayList<String> valAdresse = new ArrayList<>(); valAdresse.add(rs.getString("IPP"));
                
                    DAO<Lit> litDAO = DAOFactory.getLitDAO();
                    ArrayList<String> argLit = new ArrayList<>(); argLit.add("IPP");
                    ArrayList<String> valLit = new ArrayList<>(); valLit.add(rs.getString("IPP"));
                    retour.add(new DPI(rs.getString("nomNaissance"),rs.getString("nomUsage"),rs.getString("prenom"),adresseDAO.find(argAdresse,valAdresse), new IPP(rs.getInt("IPP")),new DateT(rs.getString("dateNaissance")).getC().getTime(), null, new InformationDeContact(rs.getString("telephoneFixe"),rs.getString("telephonePortable"),rs.getString("mail"),null),litDAO.find(argLit,valLit), null, null,Sexe.valueOf(rs.getString("sexe"))));
                }

            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return null;
    }

    @Override
    public DPI create(DPI obj) {
        
        this.query = "INSERT INTO dpi (IPP,IdCentreDeSoins, prenom,nomNaissance,nomUsage,sexe, dateNaissance,telephonePortable,telephoneFixe,mail,lit)"
                + " VALUES (" + obj.getiPP().getIPP()+ "," + 1 + "," + obj.getPrenom() + "," + obj.getNomNaissance() + "," + obj.getNomUsage() + ","+ obj.getSexe().toString()+ ","+ obj.getDateDeNaissance()+ ","+ obj.getInfoDeContact().getNumeroPortable()+ ","+ obj.getInfoDeContact().getNumeroFixe()+ ","+ obj.getInfoDeContact().getEmail()+ "," + obj.getLit().toString()+ ")";

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public DPI update(DPI obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DPI delete(DPI obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}