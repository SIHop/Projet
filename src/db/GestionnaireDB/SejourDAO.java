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
        //Crée la requete pour recupére le sejour qui respecte tout les contrainte
        this.query = "SELECT * FROM sejour WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        //Initialisation des service utile pour recupéré un Sejour
        DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
        DAO<DMA> dmaDAO = DAOFactory.getDmaDAO();
        DAO<Personnel> personnelDAO = DAOFactory.getPersonelDAO();
        DAO<FicheDeSoins> fdsDAO = DAOFactory.getFicheDeSoinsDAO();

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                //Recupére l'IP du patient
                ArrayList<String> argDMA = new ArrayList<>();
                argDMA.add("numeroSejour");
                ArrayList<String> valDMA = new ArrayList<>();
                valDMA.add(rs.getString("numeroSejour"));

                //Recupere l' adresse du patient
                ArrayList<String> argAdress = new ArrayList<>();
                argAdress.add("IPP");
                ArrayList<String> valAdress = new ArrayList<>();
                valAdress.add(dmaDAO.find(argDMA, valDMA).getIPP());

                //Recupere le médecin responsable
                ArrayList<String> argMed = new ArrayList<>();
                argMed.add("idPersonnel");
                ArrayList<String> valMed = new ArrayList<>();
                valMed.add(rs.getString("idPersonnel"));
                Medecin medecinResp = (Medecin) personnelDAO.find(argMed, valMed);

                //Recupere la liste des fiche de soint
                ArrayList<String> argFds = new ArrayList<>();
                argFds.add("numeroSejour");
                ArrayList<String> valFds = new ArrayList<>();
                valFds.add(rs.getString("numeroSejour"));
                ArrayList<FicheDeSoins> lfds = fdsDAO.findMultiple(argFds, valFds);

                return new Sejour(new LettreDeSortie(rs.getInt("idPersonnel"), adresseDAO.find(argAdress, valAdress), rs.getInt("numeroSejour"), rs.getString("lettreSortie")),
                        rs.getString("numeroSejour"), new ArrayList<String>(Arrays.asList(rs.getString("naturePrestation").split("\\s*;\\s*"))), new DateT(rs.getString("dateDebut")).getC().getTime(),
                        new DateT(rs.getString("dateFin")).getC().getTime(), medecinResp, lfds);
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
    public ArrayList<Sejour> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        ArrayList<Sejour> retour = new ArrayList<>();

        //Crée la requete pour recupére le sejour qui respecte tout les contrainte
        this.query = "SELECT * FROM sejour WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        //Initialisation des service utile pour recupéré un Sejour
        DAO<Adresse> adresseDAO = DAOFactory.getAdressePatientDAO();
        DAO<DMA> dmaDAO = DAOFactory.getDmaDAO();
        DAO<Personnel> personnelDAO = DAOFactory.getPersonelDAO();
        DAO<FicheDeSoins> fdsDAO = DAOFactory.getFicheDeSoinsDAO();

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    //Recupere l'IP du patient
                    ArrayList<String> argDMA = new ArrayList<>();
                    argDMA.add("numeroSejour");
                    ArrayList<String> valDMA = new ArrayList<>();
                    valDMA.add(rs.getString("numeroSejour"));

                    //Recupere l' adresse du patient
                    ArrayList<String> argAdress = new ArrayList<>();
                    argAdress.add("IPP");
                    ArrayList<String> valAdress = new ArrayList<>();
                    valAdress.add(dmaDAO.find(argDMA, valDMA).getIPP());

                    //Recupere le médecin responsable
                    ArrayList<String> argMed = new ArrayList<>();
                    argMed.add("idPersonnel");
                    ArrayList<String> valMed = new ArrayList<>();
                    valMed.add(rs.getString("idPersonnel"));
                    Medecin medecinResp = (Medecin) personnelDAO.find(argMed, valMed);

                    //Recupere la liste des fiche de soint
                    ArrayList<String> argFds = new ArrayList<>();
                    argFds.add("numeroSejour");
                    ArrayList<String> valFds = new ArrayList<>();
                    valFds.add(rs.getString("numeroSejour"));
                    ArrayList<FicheDeSoins> lfds = fdsDAO.findMultiple(argFds, valFds);

                    retour.add(new Sejour(new LettreDeSortie(rs.getInt("idPersonnel"), adresseDAO.find(argAdress, valAdress), rs.getInt("numeroSejour"), rs.getString("lettreSortie")),
                            rs.getString("numeroSejour"), new ArrayList<>(Arrays.asList(rs.getString("naturePrestation").split("\\s*;\\s*"))), new DateT(rs.getString("dateDebut")).getC().getTime(),
                            new DateT(rs.getString("dateFin")).getC().getTime(), medecinResp, lfds));
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
    public Sejour create(Sejour obj) {
        String natureDesPrestation = "";
        for (String s : obj.getNatureDesPrestation()) {
            natureDesPrestation += s + ";";
        }
        this.query = "INSERT INTO dma (numeroSejour, naturePrestation, lettreSortie,dateDebut,dateFin, idPersonnel)"
                + " VALUES (" + obj.getNumeroDeSejour() + "," + natureDesPrestation + "," + obj.getLettreDeSortie().getLettre() + "," + obj.getDateDebut().toString()
                + "," + obj.getDateDeFin().toString() + "," + obj.getMedecinResponsable().getIdPersonel() + ")";

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
    public Sejour update(Sejour obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sejour delete(Sejour obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
