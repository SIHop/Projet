/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.hop;

import db.GestionnaireDB.DAO;
import db.GestionnaireDB.DAOFactory;
import nf.Adresse.Adresse;
import nf.Adresse.DateT;
import nf.DPI.DM.DM;
import nf.DPI.DMA.IPP;
import nf.DPI.DMA.NSS;
import nf.DPI.DPI;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Localisation;
import nf.GestionDexploitation.Location;
import nf.GestionDexploitation.Sexe;

/**
 *
 * @author quentin
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sexe sex;
        String sexe = "F";
        String nom = "Testeur";
        String prenom = "Tester";
        String ddn = "1997-06-10";
        DAO<DPI> dpiDAO = DAOFactory.getDpiDAO();
        if(sexe.equals("H")){
            sex = Sexe.HOMME;
        }else{
            sex = Sexe.FEMME;
        }
        DateT date = new DateT(ddn);
        date.toString();
        DPI p = new DPI("", nom, prenom, new Adresse("", "", 0, "", 0, "", ""), new IPP(0), date, new NSS(0), new InformationDeContact("", "", "", ""), new Location("", false, ' ', new Localisation("", 0, ""), "", 0), null, null, sex);
        System.out.println("" + p.toString());
        dpiDAO.create(p);
    }
    
}
