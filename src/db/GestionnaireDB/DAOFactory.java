/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import nf.Adresse.Adresse;
import nf.GestionDexploitation.Lit;
import nf.GestionDexploitation.Personnel;
import nf.GestionDexploitation.Service;

/**
 *
 * @author Loïc
 */
public class DAOFactory {
    
    public static DAO<Personnel> getPersonelDAO(){
        return new PersonelDAO();
    }
    
    public static DAO<Service> getServiceDAO(){
        return new ServiceDAO();
    }
    
    public static DAO<Adresse> getAdresseDAO(){
        return new AdressePersonnelDAO();
    }
    
    public static DAO<Lit> getLitDAO(){
        return new LitDAO();
    }
}
