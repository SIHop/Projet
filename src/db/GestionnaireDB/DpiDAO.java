/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import com.mysql.fabric.xmlrpc.base.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
            Statement stmt = DpiDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
                ArrayList<String> argAdresse = new ArrayList<>(); argAdresse.add("IPP");
                ArrayList<String> valAdresse = new ArrayList<>(); valAdresse.add(rs.getString("IPP"));
                
                DAO<Lit> litDAO = DAOFactory.getLitDAO();
                ArrayList<String> argLit = new ArrayList<>(); argLit.add("IPP");
                ArrayList<String> valLit = new ArrayList<>(); valLit.add(rs.getString("IPP"));
                
                return new DPI(rs.getString("nomNaissance"),rs.getString("nomUsage"),rs.getString("prenom"),adresseDAO.find(argAdresse,valAdresse), new IPP(rs.getInt("IPP")),new DateT(rs.getString("dateNaissance")), null, new InformationDeContact(rs.getString("telephoneFixe"),rs.getString("telephonePortable"),rs.getString("mail"),null),litDAO.find(argLit,valLit), null, DAOFactory.getDmaDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(rs.getString("IPP")))),Sexe.valueOf(rs.getString("sexe")) );
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
            Statement stmt = DpiDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
                    ArrayList<String> argAdresse = new ArrayList<>(); argAdresse.add("IPP");
                    ArrayList<String> valAdresse = new ArrayList<>(); valAdresse.add(rs.getString("IPP"));
                
                    DAO<Lit> litDAO = DAOFactory.getLitDAO();
                    ArrayList<String> argLit = new ArrayList<>(); argLit.add("IPP");
                    ArrayList<String> valLit = new ArrayList<>(); valLit.add(rs.getString("IPP"));
                    retour.add(new DPI(rs.getString("nomNaissance"),rs.getString("nomUsage"),rs.getString("prenom"),adresseDAO.find(argAdresse,valAdresse), new IPP(rs.getInt("IPP")),new DateT(rs.getString("dateNaissance")), null, new InformationDeContact(rs.getString("telephoneFixe"),rs.getString("telephonePortable"),rs.getString("mail"),null),litDAO.find(argLit,valLit), null, null,Sexe.valueOf(rs.getString("sexe"))));
                }

            } else {
                System.out.println("Aucun résultat n'a était trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            System.out.println("Pas de résultats correspondent");
        }
        return retour;
    }

    @Override
    public DPI create(DPI obj) {
        
        this.query = "INSERT INTO dpi (IPP,IdCentreDeSoins, prenom,nomNaissance,nomUsage,sexe, dateNaissance,telephonePortable,telephoneFixe,mail,lit)"
                + " VALUES (" + obj.getiPP().getIPP()+ "," + 1 + ",'" + obj.getPrenom().replace("'", "''") + "','" + obj.getNomNaissance().replace("'", "''") + "','" + obj.getNomUsage().replace("'", "''") + "','"+ obj.getSexe().toString()+ "',"+ obj.getDateDeNaissance().toString()+ ",'"+ obj.getInfoDeContact().getNumeroPortable()+ "','"+ obj.getInfoDeContact().getNumeroFixe()+ "','"+ obj.getInfoDeContact().getEmail()+ "','" + obj.getLit().getIdentifient()+ "')";

        Statement stmt;
        try {
            stmt = DpiDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public DPI update(DPI obj) {
        this.query = "UPDATE dpi SET prenom = '"+ obj.getPrenom().replace("'", "''") + "', nomNaissance = '"+ obj.getNomNaissance().replace("'", "''")+"', nomUsage = '" +obj.getNomUsage().replace("'", "''")+"', sexe = '"+ obj.getSexe().toString()+"', dateNaissance = "+ obj.getDateDeNaissance().toString()+", telephonePortable = '"+ obj.getInfoDeContact().getNumeroPortable()+"', telephoneFixe = '"+obj.getInfoDeContact().getNumeroFixe()+ "', mail = '"+obj.getInfoDeContact().getEmail()+ "', lit = '"+obj.getLit().getIdentifient()+ "' WHERE IPP = " + obj.getiPP()+" AND idCentreDeSoin ="+" 1";

        Statement stmt;
        try {
            stmt = DpiDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public DPI delete(DPI obj) {
        this.query = "DELETE FROM dpi WHERE IPP = " + obj.getiPP()+ "and idCentreDeSoin ="+ "1";

        Statement stmt;
        try {
            stmt = DpiDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(IPP) FROM dpi";

        Statement stmt;
        try {
            stmt = DpiDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(IPP)"));
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
    
}
