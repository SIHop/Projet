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
import nf.Adresse.Adresse;
import nf.Adresse.DateT;
import nf.DPI.DM.DM;
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.IPP;
import nf.DPI.DMA.Sejour;
import nf.DPI.DPI;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Location;
import nf.GestionDexploitation.Sexe;

/**
 *
 * @author audrey
 */
public class DpiDAO implements DAO<DPI> {

    private String query = "";

    @Override
    public DPI find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer les informations du DPI qui respecte toutes les contraintes
        this.query = "SELECT * FROM dpi WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        try {
            Statement stmt = DpiDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
                ArrayList<String> argAdresse = new ArrayList<>();
                argAdresse.add("IPP");
                ArrayList<String> valAdresse = new ArrayList<>();
                valAdresse.add(rs.getString("IPP"));

                DAO<Location> litDAO = DAOFactory.getLitDAO();
                ArrayList<String> argLit = new ArrayList<>();
                argLit.add("IPP");
                ArrayList<String> valLit = new ArrayList<>();
                valLit.add(rs.getString("IPP"));

                DMA myDMA = DAOFactory.getDmaDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(rs.getString("IPP"))));

                DM myDM;
                if (myDMA != null) {
                    myDM = new DM(myDMA.getListeDeSejour(), new IPP(rs.getInt("IPP")));
                } else {
                    myDMA = new DMA(new ArrayList<>(), rs.getString("IPP"));
                    myDM = new DM(new ArrayList<>(), new IPP(rs.getInt("IPP")));
                }

                return new DPI(rs.getString("nomNaissance"), rs.getString("nomUsage"), rs.getString("prenom"), adresseDAO.find(argAdresse, valAdresse), new IPP(rs.getInt("IPP")), new DateT(rs.getString("dateNaissance")), null, new InformationDeContact(rs.getString("telephoneFixe"), rs.getString("telephonePortable"), rs.getString("mail"), null), litDAO.find(argLit, valLit), myDM, myDMA, Sexe.valueOf(rs.getString("sexe")), rs.getString("lieuNaissance"));
            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            //System.out.println("Pas de résultat correspondant");
        }
        return null;
    }

    @Override
    public ArrayList<DPI> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<DPI> retour = new ArrayList<>();

        //Créer la requête pour récupérer la liste de lit qui respecte toutes les contraintes
        this.query = "SELECT * FROM dpi WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        try {
            Statement stmt = DpiDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
                    ArrayList<String> argAdresse = new ArrayList<>();
                    argAdresse.add("IPP");
                    ArrayList<String> valAdresse = new ArrayList<>();
                    valAdresse.add(rs.getString("IPP"));

                    DAO<Location> litDAO = DAOFactory.getLitDAO();
                    ArrayList<String> argLit = new ArrayList<>();
                    argLit.add("IPP");
                    ArrayList<String> valLit = new ArrayList<>();
                    valLit.add(rs.getString("IPP"));

                    DMA myDMA = DAOFactory.getDmaDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(rs.getString("IPP"))));
                    DM myDM;
                    if (myDMA != null) {
                        myDM = new DM(myDMA.getListeDeSejour(), new IPP(rs.getInt("IPP")));
                    } else {
                        myDMA = new DMA(new ArrayList<>(), rs.getString("IPP"));
                        myDM = new DM(new ArrayList<>(), new IPP(rs.getInt("IPP")));
                    }

                    retour.add(new DPI(rs.getString("nomNaissance"), rs.getString("nomUsage"), rs.getString("prenom"), adresseDAO.find(argAdresse, valAdresse), new IPP(rs.getInt("IPP")), new DateT(rs.getString("dateNaissance")), null, new InformationDeContact(rs.getString("telephoneFixe"), rs.getString("telephonePortable"), rs.getString("mail"), null), litDAO.find(argLit, valLit), myDM, myDMA, Sexe.valueOf(rs.getString("sexe")), rs.getString("lieuNaissance")));
                }

            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            //System.out.println("Pas de résultat correspondant");
        }
        return retour;
    }

    @Override
    public DPI create(DPI obj) {
        String lit;
        if (obj.getLit() == null) {
            lit = null;
        } else {
            lit = "'" + obj.getLit().getIdLit() + "'";
        }
        this.query = "INSERT INTO dpi (IPP,IdCentreDeSoin, prenom,nomNaissance,nomUsage,sexe, dateNaissance,telephonePortable,telephoneFixe,mail,lit, lieuNaissance)"
                + " VALUES (" + obj.getiPP().getIPP()
                + "," + 38100111 + ",'"
                + obj.getPrenom().replace("'", "''")
                + "','" + obj.getNomNaissance().replace("'", "''")
                + "','" + obj.getNomUsage().replace("'", "''")
                + "','" + obj.getSexe().toString() + "','"
                + obj.getDateDeNaissance().toString()
                + "','" + obj.getInfoDeContact().getNumeroPortable()
                + "','" + obj.getInfoDeContact().getNumeroFixe() + "','"
                + obj.getInfoDeContact().getEmail() + "',"
                + lit
                + ",'" + obj.getLieuNaissance().replace("'", "''").toUpperCase() + "')";

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
        String lit;
        if (obj.getLit() == null) {
            lit = null;
        } else {
            lit = "'" + obj.getLit().getIdLit() + "'";
        }
        this.query = "UPDATE dpi SET prenom = '" + obj.getPrenom().replace("'", "''") + "', nomNaissance = '" + obj.getNomNaissance().replace("'", "''") + "', nomUsage = '" + obj.getNomUsage().replace("'", "''") + "', sexe = '" + obj.getSexe().toString() + "', dateNaissance = '" + obj.getDateDeNaissance().toString() + "', telephonePortable = '" + obj.getInfoDeContact().getNumeroPortable() + "', telephoneFixe = '" + obj.getInfoDeContact().getNumeroFixe() + "', mail = '" + obj.getInfoDeContact().getEmail() + "', lit = " + lit + ", lieuNaissance = '" + obj.getLieuNaissance().replace("'", "''").toUpperCase() + "' WHERE IPP = " + obj.getiPP().getIPP() + " AND idCentreDeSoin =" + "38100111";

        
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
        this.query = "DELETE FROM dpi WHERE IPP = " + obj.getiPP().getIPP() + " and idCentreDeSoin =" + "1";

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
/**
 * Permet de trouver toute les listes de DPI
 * @return 
 */
    public ArrayList<DPI> findAll() {
        ArrayList<DPI> retour = new ArrayList<>();

        //Créer la requête pour récupérer la liste de tous les DPI
        this.query = "SELECT * FROM dpi";
        

        try {
            Statement stmt = DpiDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
                    ArrayList<String> argAdresse = new ArrayList<>();
                    argAdresse.add("IPP");
                    ArrayList<String> valAdresse = new ArrayList<>();
                    valAdresse.add(rs.getString("IPP"));

                    DAO<Location> litDAO = DAOFactory.getLitDAO();
                    ArrayList<String> argLit = new ArrayList<>();
                    argLit.add("IPP");
                    ArrayList<String> valLit = new ArrayList<>();
                    valLit.add(rs.getString("IPP"));

                    
                    DMA myDMA = DAOFactory.getDmaDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(rs.getString("IPP"))));
                    DM myDM;
                    if (myDMA != null) {                        
                        myDM = new DM(myDMA.getListeDeSejour(), new IPP(rs.getInt("IPP")));
                    } else {
                        myDMA = new DMA(new ArrayList<>(), rs.getString("IPP"));
                        myDM = new DM(new ArrayList<>(), new IPP(rs.getInt("IPP")));
                    }

                    retour.add(new DPI(rs.getString("nomNaissance"), rs.getString("nomUsage"), rs.getString("prenom"), adresseDAO.find(argAdresse, valAdresse), new IPP(rs.getInt("IPP")), new DateT(rs.getString("dateNaissance")), null, new InformationDeContact(rs.getString("telephoneFixe"), rs.getString("telephonePortable"), rs.getString("mail"), null), litDAO.find(argLit, valLit), myDM, myDMA, Sexe.valueOf(rs.getString("sexe")), rs.getString("lieuNaissance")));
                }

            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DpiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;

    }

}
