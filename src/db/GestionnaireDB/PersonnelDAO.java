/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import nf.Adresse.DateT;
import nf.GestionDexploitation.AideSoignante;
import nf.GestionDexploitation.Infirmier;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Medecin;
import nf.GestionDexploitation.MotDePasse;
import nf.GestionDexploitation.Personnel;
import nf.GestionDexploitation.RangMedecin;
import nf.GestionDexploitation.SecretaireAdministratif;
import nf.GestionDexploitation.SecretaireMedicale;
import nf.GestionDexploitation.Service;
import nf.GestionDexploitation.Sexe;
import nf.GestionDexploitation.TypeInfirmier;
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
public class PersonnelDAO implements DAO<Personnel> {

    String query = "";

    @Override
    public Personnel find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer le personel qui respecte toutes les contraintes
        this.query = "SELECT * FROM personnel where ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        try {
            Statement stmt = PersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.next();
                return this.instancier(rs);
            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    /**
     * Fonction qui instancie correctement le personnel selon son type
     * (medecin, infimier,etc.)
     *
     * @return Personnel correctement instancié
     */
    private Personnel instancier(ResultSet rs) throws SQLException {
        DAO<Service> serviceDAO = DAOFactory.getServiceDAO();
        AdressePersonnelDAO adresseDAO = DAOFactory.getAdressePersonnelDAO();


        String type = rs.getString("typePersonnel");

        ArrayList<String> argSer = new ArrayList<>();
        argSer.add("idService");
        ArrayList<String> valSer = new ArrayList<>();
        valSer.add(rs.getString("idService"));

        ArrayList<String> argAdr = new ArrayList<>();
        argAdr.add("idPersonnel");
        ArrayList<String> valAdr = new ArrayList<>();
        valAdr.add(rs.getString("idPersonnel"));
        
        String bureau = rs.getString("bureau");
        if(bureau == null){
            bureau = "";
        }

        if (type.equalsIgnoreCase("Medecin")) {
            return new Medecin("123", RangMedecin.valueOf(rs.getString("niveauSpecialisation")), serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")),rs.getString("identifiantHygie"), new MotDePasse(rs.getString("motDePasse"), false), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"));
        } else if (type.equalsIgnoreCase("Infirmier")) {
            return new Infirmier(serviceDAO.find(argSer, valSer), TypeInfirmier.valueOf(rs.getString("niveauSpecialisation")), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")),rs.getString("identifiantHygie"), new MotDePasse(rs.getString("motDePasse"), false), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"));
        } else if (type.equalsIgnoreCase("AS")) {
            return new AideSoignante(serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), rs.getString("identifiantHygie"),new MotDePasse(rs.getString("motDePasse"), false), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"));
        } else if (type.equalsIgnoreCase("SM")) {
            return new SecretaireMedicale(serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")), rs.getString("identifiantHygie"),new MotDePasse(rs.getString("motDePasse"), false), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"));
        } else//SA
        {
            return new SecretaireAdministratif(bureau, serviceDAO.find(argSer, valSer), rs.getString("nomUsage"), rs.getString("prenom"), rs.getString("idPersonnel"), Sexe.valueOf(rs.getString("sexe")), adresseDAO.find(argAdr, valAdr), new DateT(rs.getString("dateNaissance")),rs.getString("identifiantHygie"), new MotDePasse(rs.getString("motDePasse"), false), new InformationDeContact(rs.getString("telephone"), "00", rs.getString("mail"), "00"));

        }
    }

    @Override
    public ArrayList<Personnel> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer les personels qui respecte toutes les contraintes
        this.query = "SELECT * FROM personnel where ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        ArrayList<Personnel> retour = new ArrayList<>();

        try {
            Statement stmt = PersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(this.instancier(rs));
                }
            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return retour;
    }

    @Override
    public Personnel create(Personnel obj) {
        if (obj instanceof Medecin) {
            this.query = "INSERT INTO personnel (niveauSpecialisation, typePersonnel, idPersonnel,idService,identifiantHygie,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + " VALUES ('"
                    + ((Medecin) obj).getType().toString() + "'," + "'Medecin'," + obj.getIdPersonel() + "," + ((Medecin) obj).getService().getCodeService() + ",'" +obj.getIdentifient() +"','" + obj.getPassword().getMdp()+ "','"
                    + obj.getPrenom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getSexe().toString() + "','" + obj.getDateDeNaissance().toString() + "','" + obj.getInfoDeContact().getNumeroFixe() + "','" + obj.getInfoDeContact().getEmail()
                    + "')";
        } else if (obj instanceof Infirmier) {
            this.query = "INSERT INTO personnel (niveauSpecialisation, typePersonnel, idPersonnel,idService,identifiantHygie,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ('"
                    + ((Infirmier) obj).getType().toString() + "','Infirmier'," + obj.getIdPersonel() + "," + ((Infirmier) obj).getService().getCodeService() + ",'" +obj.getIdentifient() +"','" +  obj.getPassword().getMdp()+ "','"
                    + obj.getPrenom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getSexe().toString() + "','" + obj.getDateDeNaissance().toString() + ",'" + obj.getInfoDeContact().getNumeroFixe() + "','" + obj.getInfoDeContact().getEmail()
                    + "')";

        } else if (obj instanceof AideSoignante) {
            this.query = "INSERT INTO personnel (typePersonnel, idPersonnel,idService,identifiantHygie,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + "'AS'," + obj.getIdPersonel() + "," + ((AideSoignante) obj).getService().getCodeService() + ",'" +obj.getIdentifient() +"','" +  obj.getPassword().getMdp() + "','"
                    + obj.getPrenom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getSexe().toString() + "','" + obj.getDateDeNaissance().toString() + "','" + obj.getInfoDeContact().getNumeroFixe() + "','" + obj.getInfoDeContact().getEmail()
                    + "')";
        } else if (obj instanceof SecretaireMedicale) {
            this.query = "INSERT INTO personnel (typePersonnel, idPersonnel,idService,identifiantHygie,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ("
                    + "'SM'," + obj.getIdPersonel() + "," + ((SecretaireMedicale) obj).getService().getCodeService() + ",'" +obj.getIdentifient() +"','" +  obj.getPassword().getMdp() + "','"
                    + obj.getPrenom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getSexe().toString() + "','" + obj.getDateDeNaissance().toString() + "','" + obj.getInfoDeContact().getNumeroFixe() + "','" + obj.getInfoDeContact().getEmail()
                    + "')";
        } else {//SA
            this.query = "INSERT INTO personnel (bureau,typePersonnel, idPersonnel,idService,identifiantHygie,motDePasse,prenom,nomNaissance,nomUsage,sexe,dateNaissance,telephone,mail)"
                    + "VALUES ('"
                    + ((SecretaireAdministratif) obj).getBureau() + "','SA'," + obj.getIdPersonel() + "," + ((SecretaireAdministratif) obj).getService().getCodeService() + ",'" +obj.getIdentifient() +"','" +  obj.getPassword().getMdp() + "','"
                    + obj.getPrenom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getNom().replace("'", "''") + "','" + obj.getSexe().toString() + "','" + obj.getDateDeNaissance().toString() + "','" + obj.getInfoDeContact().getNumeroFixe() + "','" + obj.getInfoDeContact().getEmail()
                    + "')";
        }
        
        

        Statement stmt;
        try {
            stmt = PersonnelDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Personnel update(Personnel obj) {
        if (obj instanceof Medecin) {
            this.query = "UPDATE personnel SET niveauSpecialisation = '" + ((Medecin) obj).getType().toString() + "',idService = " + ((Medecin) obj).getService().getCodeService() +", identifiantHygie = '"+obj.getIdentifient()
                    + "',motDePasse = '" + obj.getPassword().getMdp() + "',prenom ='" + obj.getPrenom().replace("'", "''") + "',nomNaissance ='" + obj.getNom().replace("'", "''") + "',nomUsage = '" + obj.getNom() + "',sexe ='" + obj.getSexe().toString()
                    + "',dateNaissance ='" + obj.getDateDeNaissance().toString() + "',telephone ='" + obj.getInfoDeContact().getNumeroFixe() + "',mail ='" + obj.getInfoDeContact().getEmail() + "' WHERE idPersonnel = " + obj.getIdPersonel();
        } else if (obj instanceof Infirmier) {
            this.query = "UPDATE personnel SET niveauSpecialisation ='" + ((Infirmier) obj).getType().toString() + "',idService = " + ((Infirmier) obj).getService().getCodeService()+", identifiantHygie = '"+obj.getIdentifient()
                    + "',motDePasse ='" + obj.getPassword().getMdp() + "',prenom ='" + obj.getPrenom().replace("'", "''") + "',nomNaissance ='" + obj.getNom().replace("'", "''") + "',nomUsage = '" + obj.getNom().replace("'", "''") + "',sexe ='" + obj.getSexe().toString()
                    + "',dateNaissance ='" + obj.getDateDeNaissance().toString() + "',telephone ='" + obj.getInfoDeContact().getNumeroFixe() + "',mail ='" + obj.getInfoDeContact().getEmail() + "' WHERE idPersonnel = " + obj.getIdPersonel();
        } else if (obj instanceof AideSoignante) {
            this.query = "UPDATE personnel SET idService = " + ((AideSoignante) obj).getService().getCodeService()+", identifiantHygie = '"+obj.getIdentifient()
                    + "',motDePasse ='" + obj.getPassword().getMdp() + "',prenom ='" + obj.getPrenom().replace("'", "''") + ",nomNaissance ='" + obj.getNom().replace("'", "''") + "',nomUsage = '" + obj.getNom().replace("'", "''") + "',sexe ='" + obj.getSexe().toString()
                    + "',dateNaissance ='" + obj.getDateDeNaissance().toString() + "',telephone ='" + obj.getInfoDeContact().getNumeroFixe() + "',mail ='" + obj.getInfoDeContact().getEmail() + "' WHERE idPersonnel = " + obj.getIdPersonel();
        } else if (obj instanceof SecretaireMedicale) {
            this.query = "UPDATE personnel SET idService = " + ((SecretaireMedicale) obj).getService().getCodeService()+", identifiantHygie = '"+obj.getIdentifient()
                    + "',motDePasse ='" + obj.getPassword().getMdp() + "',prenom ='" + obj.getPrenom().replace("'", "''") + "',nomNaissance ='" + obj.getNom().replace("'", "''") + "',nomUsage = '" + obj.getNom().replace("'", "''") + "',sexe ='" + obj.getSexe().toString()
                    + "',dateNaissance ='" + obj.getDateDeNaissance().toString() + "',telephone ='" + obj.getInfoDeContact().getNumeroFixe() + "',mail ='" + obj.getInfoDeContact().getEmail() + "' WHERE idPersonnel = " + obj.getIdPersonel();
        } else {//SA
            this.query = "UPDATE personnel SET bureau = " + ((SecretaireAdministratif) obj).getBureau() + ",idService = " + ((SecretaireAdministratif) obj).getService().getCodeService()+", identifiantHygie = '"+obj.getIdentifient()
                    + "',motDePasse ='" + obj.getPassword().getMdp() + "',prenom ='" + obj.getPrenom().replace("'", "''") + "',nomNaissance ='" + obj.getNom().replace("'", "''") + "',nomUsage = '" + obj.getNom().replace("'", "''") + "',sexe ='" + obj.getSexe().toString()
                    + "',dateNaissance ='" + obj.getDateDeNaissance().toString() + "',telephone ='" + obj.getInfoDeContact().getNumeroFixe() + "',mail ='" + obj.getInfoDeContact().getEmail() + "' WHERE idPersonnel = " + obj.getIdPersonel();
        }

        
        Statement stmt;
        try {
            stmt = PersonnelDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Personnel delete(Personnel obj) {
        this.query = "DELETE FROM personnel WHERE idPersonnel = " + obj.getIdPersonel();

        Statement stmt;
        try {
            stmt = PersonnelDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idPersonnel) FROM personnel";

        Statement stmt;
        try {
            stmt = PersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(idPersonnel)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public ArrayList<Personnel> findAll(){
        //Créer la requête pour récupérer les personels qui respecte toutes les contraintes
        this.query = "SELECT * FROM personnel";
        

        ArrayList<Personnel> retour = new ArrayList<>();

        try {
            Statement stmt = PersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    retour.add(this.instancier(rs));
                }
            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return retour;
    }
}
