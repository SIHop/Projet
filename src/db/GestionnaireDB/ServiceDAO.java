/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import nf.GestionDexploitation.Location;
import nf.GestionDexploitation.Localisation;
import nf.GestionDexploitation.Service;
import nf.GestionDexploitation.TypeService;
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
public class ServiceDAO implements DAO<Service> {

    private String query = "";
    DAO<Location> litDAO = DAOFactory.getLitDAO();

    @Override
    public Service find(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer le service qui respecte toutes les contraintes
        this.query = "SELECT * FROM service WHERE ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                rs.first();
                //Recolte des informations pour instancier la liste de lit
                ArrayList<String> argL = new ArrayList<>();
                argL.add("idService");
                ArrayList<String> valL = new ArrayList<>();
                valL.add(rs.getString("idService"));

                return new Service(rs.getString("idService"), rs.getString("nomService"), rs.getString("idResponsable"), new Localisation(rs.getString("batiment"),rs.getInt("etage"), rs.getString("couloir")), litDAO.findMultiple(argL, valL), null, TypeService.valueOf(rs.getString("typeService")));
            } else {
                //System.out.println("Aucun résultat n'a été trouvé");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            //System.out.println("Pas de résultat correspondant");
        }
        return null;
    }

    @Override
    public ArrayList<Service> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        //Créer la requête pour récupérer les personels qui respecte toutes les contraintes
        this.query = "SELECT * FROM service where ";
        query += arg.get(0) + " = " + val.get(0);
        if (arg.size() > 1) {
            for (int i = 1; i < arg.size(); i++) {
                query += " && " + arg.get(i) + " = " + val.get(i);
            }
        }
        

        ArrayList<Service> retour = new ArrayList<>();

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    //Récolte des informations pour instancier la liste de lit
                    ArrayList<String> argL = new ArrayList<>();
                    argL.add("idService");
                    ArrayList<String> valL = new ArrayList<>();
                    valL.add(rs.getString("idService"));
                    retour.add(new Service(rs.getString("idService"), rs.getString("nomService"), "1", new Localisation("bat", 1, "couloir"), litDAO.findMultiple(argL, valL), null, TypeService.valueOf(rs.getString("typeService"))));
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
    public Service create(Service obj) {
        this.query = "INSERT INTO service (idService, idCentreDeSoin, typeService,nomService,idResponsable)"
                + " VALUES (" + obj.getCodeService() + ",1,'" + obj.getTypeService().toString() + "','" + obj.getNomService().replace("'", "''") + "'," + obj.getResponsable().getIdPersonel() + ")";
        
        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Service update(Service obj) {
        this.query = "UPDATE service SET typeService = '" + obj.getTypeService().toString() + "', nomService = '" + obj.getNomService().replace("'", "''") + "', idResponsable = " + obj.getResponsable().getIdPersonel() + " WHERE idService = " + obj.getCodeService();
        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Service delete(Service obj) {
        this.query = "DELETE FROM service WHERE idService = " + obj.getCodeService();

        Statement stmt;
        try {
            stmt = ServiceDAO.connect.createStatement();
            int rowEffected = stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public int getMaxId() {
        this.query = "SELECT max(idService) FROM service";

        Statement stmt;
        try {
            stmt = PersonnelDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            return Integer.parseInt(rs.getString("max(idService)"));
        } catch (SQLException ex) {
            Logger.getLogger(PersonnelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }
    
    public ArrayList<Service> findAll(){
        //Créer la requête pour récupérer les personels qui respecte toutes les contraintes
        this.query = "SELECT * FROM service";        
        
        

        ArrayList<Service> retour = new ArrayList<>();

        try {
            Statement stmt = ServiceDAO.connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    //Récolte des informations pour instancier la liste de lit
                    ArrayList<String> argL = new ArrayList<>();
                    argL.add("idService");
                    ArrayList<String> valL = new ArrayList<>();
                    valL.add(rs.getString("idService"));
                    retour.add(new Service(rs.getString("idService"), rs.getString("nomService"), "1", new Localisation("bat", 1, "couloir"), litDAO.findMultiple(argL, valL), null, TypeService.valueOf(rs.getString("typeService"))));
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

}
