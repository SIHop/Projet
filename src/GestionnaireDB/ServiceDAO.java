/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import GestionDexploitation.Localisation;
import GestionDexploitation.Medecin;
import GestionDexploitation.Personnel;
import GestionDexploitation.RangMedecin;
import GestionDexploitation.Service;
import GestionDexploitation.Sexe;
import GestionDexploitation.TypeService;
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
public class ServiceDAO implements DAO<Service>{
    
    private String query = "";

    @Override
    public Service find(ArrayList<String> arg, ArrayList<String> val) {
        //Crée la requete pour recupére le personelle qui respecte tout les contrainte
        this.query = "SELECT * FROM service WHERE ";
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
                //ArrayList<String> argMed = new ArrayList<String>(); argMed.add("idResponsable");
                //ArrayList<String> valMed = new ArrayList<String>(); valMed.add(rs.getString("idRespnsable"));
                
                return new Service(rs.getString("idService"),rs.getString("nomService"),"1",new Localisation("bat", "eta", "couloir"), null, null, TypeService.valueOf(rs.getString("typeService")));
                                                                                       //rs.getString("idResponsable"),                                                                                                                  //litDAO.find()           
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
    public ArrayList<Service> findMultiple(ArrayList<String> arg, ArrayList<String> val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service create(Service obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service update(Service obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service delete(Service obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
