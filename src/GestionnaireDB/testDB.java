/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireDB;

import GestionDexploitation.Medecin;
import GestionDexploitation.Personnel;
import GestionDexploitation.SecretaireMedicale;
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
        val.add("1");
        
        Personnel p = personelDAO.find(arg, val);
        SecretaireMedicale sm = (SecretaireMedicale)p;
        
       
        System.out.println(sm.getAdresse().getPays() + " " + sm.getAdresse().getCodePostal() + " " + sm.getAdresse().getVille() + " " + sm.getAdresse().getNumeroVoie() + " "+ sm.getAdresse().getTypeVoie() + " "+ sm.getAdresse().getNomVoie() + " " + sm.getAdresse().getComplement());
        
       System.out.println(sm.getNom() + " " + sm.getPrenom() + " " + sm.getIdPersonel() + " du service : " + sm.getService().getCodeService() + " vivant à  "+ sm.getAdresse().getVille());



//        
//        String date = "1993-08-23";
//        DateT d = new DateT(date);
//        
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        System.out.println(dateFormat.format(d.getC().getTime()));
        
        
      
    }

}
