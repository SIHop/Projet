/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import GestionDexploitation.Personnel;

/**
 *
 * @author Loïc
 */
public class DAOFactory {
    
    public static DAO<Personnel> getPersonelDAO(){
        return new PersonelDAO();
    }
    
}
