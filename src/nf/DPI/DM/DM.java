/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import db.GestionnaireDB.DAOFactory;
import java.util.ArrayList;
import java.util.Arrays;
import nf.DPI.DMA.Sejour;

/**
 *
 * @author quentin
 */
public class DM {

    private ArrayList<Sejour> lSejour;

    public DM(ArrayList<Sejour> lSejour) {
        this.lSejour = lSejour;
    }

    public ArrayList<Sejour> getlSejour() {
        return lSejour;
    }
    
    public Sejour getLastSejour(){
            Sejour sejour ;
            sejour = DAOFactory.getSejourDAO().find(new ArrayList<>(Arrays.asList("dateFin")),new ArrayList<>(Arrays.asList("")));
            return sejour;
    }

    @Override
    public String toString() {
        return "DM{" + "lSejour=" + lSejour + '}';
    }
    
    
}
