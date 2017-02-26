/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import Adresse.Adresse;
import GestionDexploitation.Personnel;
import GestionDexploitation.Service;

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
}
