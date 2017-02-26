/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import GestionDexploitation.Medecin;
import GestionDexploitation.Personnel;
import java.util.ArrayList;

/**
 *
 * @author Loïc
 */
public class testDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAO<Personnel> personelDAO = DAOFactory.getPersonelDAO();
        ArrayList<String> arg = new ArrayList<String>();
        ArrayList<String> val = new ArrayList<String>();
        arg.add("idPersonnel"); 
        val.add("1");
        
        arg.add("idService");
        val.add("38300111");
        
        Personnel p = personelDAO.find(arg, val);
        Medecin m = (Medecin)p;
        System.out.println(m.toString());
        
    }
    
}
