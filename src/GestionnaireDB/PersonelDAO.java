/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import Adresse.Adresse;
import Adresse.DateT;
import GestionDexploitation.AideSoignante;
import GestionDexploitation.Infirmier;
import GestionDexploitation.InformationDeContact;
import GestionDexploitation.Medecin;
import GestionDexploitation.MotDePasse;
import GestionDexploitation.Personnel;
import GestionDexploitation.RangMedecin;
import GestionDexploitation.SecretaireAdministratif;
import GestionDexploitation.SecretaireMedicale;
import GestionDexploitation.Service;
import GestionDexploitation.Sexe;
import GestionDexploitation.TypeInfirmier;
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

    String query = "";

    @Override
    public Personnel find(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére le personelle qui respecte tout les contrainte
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
                return this.instancier(rs);
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

    /**
     * Fonction qui instancie correctemennt le personnel celon son type
     * (medecin, infimier,etc.)
     *
     * @return Personnel correctement instancier
     */
    private Personnel instancier(ResultSet rs) throws SQLException {
        DAO<Service> serviceDAO = DAOFactory.getServiceDAO();
        DAO<Adresse> adresseDAO = DAOFactory.getAdresseDAO();

        rs.first();
        String type = rs.getString("typePersonnel");

        ArrayList<String> argSer = new ArrayList<String>();
        argSer.add("idService");
        ArrayList<String> valSer = new ArrayList<String>();
        valSer.add(rs.getString("idService"));

        ArrayList<String> argAdr = new ArrayList<String>();
        argAdr.add("idPersonnel");
        ArrayList<String> valAdr = new ArrayList<String>();
        valAdr.add(rs.getString("idPersonnel"));

        if (type.equalsIgnoreCase("Medecin")) {
            return new Medecin("123", RangMedecin.valueOf(rs.getString("niveauSpecialisation")), serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), new MotDePasse(rs.getString("motDePasse"), true), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"), null);
        } else if (type.equalsIgnoreCase("Infirmier")) {
            return new Infirmier(serviceDAO.find(argSer, valSer), TypeInfirmier.valueOf("niveauSpecialisation"), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), new MotDePasse(rs.getString("motDePasse"), true), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"), null);
        } else if (type.equalsIgnoreCase("AS")) {
            return new AideSoignante(serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), new MotDePasse(rs.getString("motDePasse"), true), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"), null);
        } else if (type.equalsIgnoreCase("SM")) {
            return new SecretaireMedicale(serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), new MotDePasse(rs.getString("motDePasse"), true), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"), null);
        } else//SA
        {
            return new SecretaireAdministratif("Main1C15", serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), new MotDePasse(rs.getString("motDePasse"), true), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"), null);
                                               //rs.getString("bureau")
        }
    }

    @Override
    public ArrayList<Personnel> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére les personelles qui respecte toutes les contraintes
        this.query = "SELECT * FROM personnel where ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        System.out.println(query);

        ArrayList<Personnel> retour = new ArrayList<>();

        try {
            Statement stmt = this.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(this.instancier(rs));
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
    public Personnel create(Personnel obj) {
        if (obj instanceof Medecin) {
            this.query = "INSERT INTO personnel (niveauSpecialisation, typePersonnel, idPersonnel,idService,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + ((Medecin) obj).getType().toString() + "," + "Medecin," + obj.getIdPersonel() + "," + ((Medecin) obj).getService().getCodeService() + "," + obj.getPassword().getPassword() + ","
                    + obj.getPrenom() + "," + obj.getNom() + "," + obj.getNom() + "," + obj.getSexe().toString() + "," + obj.getDateDeNaissance().toString() + obj.getInfoDeContact().getNumeroFixe() + "," + obj.getInfoDeContact().getEmail()
                    + ")";
        } else if (obj instanceof Infirmier) {
            this.query = "INSERT INTO personnel (niveauSpecialisation, typePersonnel, idPersonnel,idService,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + ((Infirmier) obj).getType().toString() + ",Infirmier," + obj.getIdPersonel() + "," + ((Infirmier) obj).getService().getCodeService() + "," + obj.getPassword().getPassword() + ","
                    + obj.getPrenom() + "," + obj.getNom() + "," + obj.getNom() + "," + obj.getSexe().toString() + "," + obj.getDateDeNaissance().toString() + obj.getInfoDeContact().getNumeroFixe() + "," + obj.getInfoDeContact().getEmail()
                    + ")";
        } else if (obj instanceof AideSoignante) {
            this.query = "INSERT INTO personnel (typePersonnel, idPersonnel,idService,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + "AS," + obj.getIdPersonel() + "," + ((AideSoignante) obj).getService().getCodeService() + "," + obj.getPassword().getPassword() + ","
                    + obj.getPrenom() + "," + obj.getNom() + "," + obj.getNom() + "," + obj.getSexe().toString() + "," + obj.getDateDeNaissance().toString() + obj.getInfoDeContact().getNumeroFixe() + "," + obj.getInfoDeContact().getEmail()
                    + ")";
        } else if (obj instanceof SecretaireMedicale) {
            this.query = "INSERT INTO personnel (typePersonnel, idPersonnel,idService,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + "SM," + obj.getIdPersonel() + "," + ((SecretaireMedicale) obj).getService().getCodeService() + "," + obj.getPassword().getPassword() + ","
                    + obj.getPrenom() + "," + obj.getNom() + "," + obj.getNom() + "," + obj.getSexe().toString() + "," + obj.getDateDeNaissance().toString() + obj.getInfoDeContact().getNumeroFixe() + "," + obj.getInfoDeContact().getEmail()
                    + ")";
        } else {//SA
            this.query = "INSERT INTO personnel (bureau,typePersonnel, idPersonnel,idService,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + ((SecretaireAdministratif) obj).getBureau() + ",SA," + obj.getIdPersonel() + "," + ((SecretaireAdministratif) obj).getService().getCodeService() + "," + obj.getPassword().getPassword() + ","
                    + obj.getPrenom() + "," + obj.getNom() + "," + obj.getNom() + "," + obj.getSexe().toString() + "," + obj.getDateDeNaissance().toString() + obj.getInfoDeContact().getNumeroFixe() + "," + obj.getInfoDeContact().getEmail()
                    + ")";
        }

        Statement stmt;
        try {
            stmt = this.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Personnel update(Personnel obj) {
        if (obj instanceof Medecin) {
            this.query = "UPDATE personnel SET niveauSpecialisation =" + ((Medecin) obj).getType().toString() + ",idService = " + ((Medecin) obj).getService().getCodeService()
                    + ",motDePasse =" + obj.getPassword().getPassword() + ",prenom =" + obj.getPrenom() + ",nomNaissance =" + obj.getNom() + ",nomUsage = " + obj.getNom() + ",sexe =" + obj.getSexe().toString()
                    + ",dateNaissance =" + obj.getDateDeNaissance().toString() + ",telephone =" + obj.getInfoDeContact().getNumeroFixe() + ",mail =" + obj.getInfoDeContact().getEmail();
        } else if (obj instanceof Infirmier) {
            this.query = "UPDATE personnel SET niveauSpecialisation =" + ((Infirmier) obj).getType().toString() + ",idService = " + ((Infirmier) obj).getService().getCodeService()
                    + ",motDePasse =" + obj.getPassword().getPassword() + ",prenom =" + obj.getPrenom() + ",nomNaissance =" + obj.getNom() + ",nomUsage = " + obj.getNom() + ",sexe =" + obj.getSexe().toString()
                    + ",dateNaissance =" + obj.getDateDeNaissance().toString() + ",telephone =" + obj.getInfoDeContact().getNumeroFixe() + ",mail =" + obj.getInfoDeContact().getEmail();
        } else if (obj instanceof AideSoignante) {
            this.query = "UPDATE personnel SET idService = " + ((AideSoignante) obj).getService().getCodeService()
                    + ",motDePasse =" + obj.getPassword().getPassword() + ",prenom =" + obj.getPrenom() + ",nomNaissance =" + obj.getNom() + ",nomUsage = " + obj.getNom() + ",sexe =" + obj.getSexe().toString()
                    + ",dateNaissance =" + obj.getDateDeNaissance().toString() + ",telephone =" + obj.getInfoDeContact().getNumeroFixe() + ",mail =" + obj.getInfoDeContact().getEmail();
        } else if (obj instanceof SecretaireMedicale) {
            this.query = "UPDATE personnel SET idService = " + ((SecretaireMedicale) obj).getService().getCodeService()
                    + ",motDePasse =" + obj.getPassword().getPassword() + ",prenom =" + obj.getPrenom() + ",nomNaissance =" + obj.getNom() + ",nomUsage = " + obj.getNom() + ",sexe =" + obj.getSexe().toString()
                    + ",dateNaissance =" + obj.getDateDeNaissance().toString() + ",telephone =" + obj.getInfoDeContact().getNumeroFixe() + ",mail =" + obj.getInfoDeContact().getEmail();
        } else {//SA
            this.query = "UPDATE personnel SET bureau = " + ((SecretaireAdministratif) obj).getBureau() + ",idService = " + ((SecretaireAdministratif) obj).getService().getCodeService()
                    + ",motDePasse =" + obj.getPassword().getPassword() + ",prenom =" + obj.getPrenom() + ",nomNaissance =" + obj.getNom() + ",nomUsage = " + obj.getNom() + ",sexe =" + obj.getSexe().toString()
                    + ",dateNaissance =" + obj.getDateDeNaissance().toString() + ",telephone =" + obj.getInfoDeContact().getNumeroFixe() + ",mail =" + obj.getInfoDeContact().getEmail();
        }

        Statement stmt;
        try {
            stmt = this.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Personnel delete(Personnel obj) {
        this.query = "DELETE FROM personnel WHERE idPersonnel = " + obj.getIdPersonel();

        Statement stmt;
        try {
            stmt = this.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

}
