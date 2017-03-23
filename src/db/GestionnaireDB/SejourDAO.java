/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import nf.Adresse.Adresse;
import nf.Adresse.DateT;
import nf.DPI.DM.FicheDeSoins;
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.LettreDeSortie;
import nf.DPI.DMA.Sejour;
import nf.GestionDexploitation.Medecin;
import nf.GestionDexploitation.Personnel;

/**
 *
 * @author Loïc
 */
public class SejourDAO implements DAO<Sejour> {

    private String query = "";

    @Override
    public Sejour find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer le séjour qui respecte toutes les contraintes
        this.query = "SELECT * FROM sejour WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        //Initialisation des services utiles pour récupérer un séjour
        DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
        DAO<DMA> dmaDAO = DAOFactory.getDmaDAO();
        DAO<Personnel> personnelDAO = DAOFactory.getPersonelDAO();
        DAO<FicheDeSoins> fdsDAO = DAOFactory.getFicheDeSoinsDAO();

        try {
            Statement stmt = SejourDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {

                rs.first();

                //Récupère le médecin responsable
                ArrayList<String> argMed = new ArrayList<>();
                argMed.add("idPersonnel");
                ArrayList<String> valMed = new ArrayList<>();
                valMed.add(rs.getString("idPersonnel"));
                Medecin medecinResp = (Medecin) personnelDAO.find(argMed, valMed);

                //Récupère la liste des fiches de soins
                ArrayList<String> argFds = new ArrayList<>();
                argFds.add("numeroSejour");
                ArrayList<String> valFds = new ArrayList<>();
                valFds.add(rs.getString("numeroSejour"));
                ArrayList<FicheDeSoins> lfds = fdsDAO.findMultiple(argFds, valFds);

                //dateFin
                DateT dateFin = null;
                boolean enCours = true;
                boolean facturer = false;
                if (rs.getString("dateFin") != null) {
                    dateFin = new DateT(rs.getString("dateFin"));
                    enCours = false;
                }
                if (rs.getString("facturer").equals("1")) {                    
                    facturer = true;                   
                }

                return new Sejour(new LettreDeSortie(rs.getInt("idPersonnel"), new Adresse("", "", 0, "", 0, "", ""), rs.getInt("numeroSejour"), rs.getString("lettreSortie")),
                        rs.getString("numeroSejour"), new ArrayList<>(Arrays.asList(rs.getString("naturePrestation").split("\\s*;\\s*"))), new DateT(rs.getString("dateDebut")),
                        dateFin, medecinResp, lfds, enCours, facturer, new ArrayList<>(Arrays.asList(rs.getString("observation").split("\\s*;\\s*"))));
            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public ArrayList<Sejour> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<Sejour> retour = new ArrayList<>();

        //Créer la requête pour récupérer le séjour qui respecte toutes les contraintes
        this.query = "SELECT * FROM sejour WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        //Initialisation des services utiles pour récupérer un séjour
        DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
        DAO<DMA> dmaDAO = DAOFactory.getDmaDAO();
        DAO<Personnel> personnelDAO = DAOFactory.getPersonelDAO();
        DAO<FicheDeSoins> fdsDAO = DAOFactory.getFicheDeSoinsDAO();

        try {
            Statement stmt = SejourDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    //Récupère l'IP du patient
                    ArrayList<String> argDMA = new ArrayList<>();
                    argDMA.add("numeroSejour");
                    ArrayList<String> valDMA = new ArrayList<>();
                    valDMA.add(rs.getString("numeroSejour"));

                    //Récupère l'adresse du patient
                    ArrayList<String> argAdress = new ArrayList<>();
                    argAdress.add("IPP");
                    ArrayList<String> valAdress = new ArrayList<>();
                    valAdress.add(dmaDAO.find(argDMA, valDMA).getIPP());

                    //Récupère le médecin responsable
                    ArrayList<String> argMed = new ArrayList<>();
                    argMed.add("idPersonnel");
                    ArrayList<String> valMed = new ArrayList<>();
                    valMed.add(rs.getString("idPersonnel"));
                    Medecin medecinResp = (Medecin) personnelDAO.find(argMed, valMed);

                    //Récupère la liste des fiches de soins
                    ArrayList<String> argFds = new ArrayList<>();
                    argFds.add("numeroSejour");
                    ArrayList<String> valFds = new ArrayList<>();
                    valFds.add(rs.getString("numeroSejour"));
                    ArrayList<FicheDeSoins> lfds = fdsDAO.findMultiple(argFds, valFds);

                    //dateFin
                    DateT dateFin = null;
                    boolean enCours = true;
                    boolean facturer = false;
                    if (rs.getString("dateFin") != null) {
                        dateFin = new DateT(rs.getString("dateFin"));
                        enCours = false;
                    }
                    if (rs.getString("facturer").equals("1")) {
                        
                        facturer = true;
                    }

                    retour.add(new Sejour(new LettreDeSortie(rs.getInt("idPersonnel"), adresseDAO.find(argAdress, valAdress), rs.getInt("numeroSejour"), rs.getString("lettreSortie")),
                            rs.getString("numeroSejour"), new ArrayList<>(Arrays.asList(rs.getString("naturePrestation").split("\\s*;\\s*"))), new DateT(rs.getString("dateDebut")),
                            dateFin, medecinResp, lfds, enCours,facturer,new ArrayList<>(Arrays.asList(rs.getString("observation").split("\\s*;\\s*")))));
                }

            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            //System.out.println("Pas de résultat correspondant");
        }
        return retour;
    }

    @Override
    public Sejour create(Sejour obj) {
        String natureDesPrestation = "";        
        for (String s : obj.getNatureDesPrestation()) {
            natureDesPrestation += s + ";";
        }
        String observation = "";
        for(String s : obj.getObservation()){
            observation += s.replace("'", "''")+";";
        }
        String lettre;
        String dateFin;
        if (obj.getLettreDeSortie() != null) {
            lettre = "'" + obj.getLettreDeSortie().getLettre().replace("'", "''") + "'";
        } else {
            lettre = null;
        }
        if (obj.getDateDeFin() == null) {
            dateFin = null;
        } else {
            dateFin = "'" + obj.getDateDeFin().toString() + "'";
        }

        this.query = "INSERT INTO sejour (numeroSejour, naturePrestation, lettreSortie,dateDebut,dateFin, idPersonnel, enCours, facturer,observation)"
                + " VALUES (" + obj.getNumeroDeSejour() + ",'" + natureDesPrestation + "'," + lettre + ",'" + obj.getDateDebut().toString()
                + "'," + dateFin + "," + obj.getMedecinResponsable().getIdPersonel() + "," + obj.isEnCours() +"," + obj.isFacturer() + ",'" + observation+ "')";

        Statement stmt;

        try {
            stmt = SejourDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;

    }

    @Override
    public Sejour update(Sejour obj) {
        String natureDesPrestation = "";
        for (String s : obj.getNatureDesPrestation()) {
            natureDesPrestation += s + ";";
        }
        String observation = "";
        for(String s : obj.getObservation()){
            observation += s.replace("'", "''")+";";
        }
        String lettre;
        String dateFin;
        if (obj.getLettreDeSortie() != null) {
            lettre = obj.getLettreDeSortie().getLettre();
        } else {
            lettre = "NULL";
        }
        if (obj.getDateDeFin() == null) {
            dateFin = null;
        } else {
            dateFin = "'" + obj.getDateDeFin().toString() + "'";
        }
        this.query = "UPDATE sejour SET lettreSortie = '" + obj.getLettreDeSortie().getLettre() + "', naturePrestation = '" + natureDesPrestation + "', dateDebut = '"
                + obj.getDateDebut() + "', dateFin =" + dateFin + ", enCours = " + obj.isEnCours() +", facturer = "+ obj.isFacturer() + ", observation = '" + observation + "' WHERE numeroSejour = " + obj.getNumeroDeSejour();

        Statement stmt;
        try {
            stmt = SejourDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Sejour delete(Sejour obj) {

        this.query = "DELETE FROM sejour WHERE numeroSejour = " + obj.getNumeroDeSejour();

        Statement stmt;
        try {
            stmt = SejourDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(numeroSejour) FROM sejour";

        Statement stmt;
        try {
            stmt = SejourDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return rs.getInt("max(numeroSejour)");
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
