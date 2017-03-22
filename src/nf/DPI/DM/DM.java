/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import db.GestionnaireDB.DAOFactory;
import db.GestionnaireDB.DmaDAO;
import java.util.ArrayList;
import java.util.Arrays;
import nf.DPI.DMA.IPP;
import nf.DPI.DMA.Sejour;


public class DM {

    private ArrayList<Sejour> lSejour;
    private IPP ipp;

    public DM(ArrayList<Sejour> lSejour,IPP ipp) {
        this.lSejour = lSejour;
        this.ipp=ipp;
    }

    public ArrayList<Sejour> getlSejour() {
        return lSejour;
    }
    /**
     * affiche le dernier séjour effectué par le patient 
     * @return 
     */
    public Sejour getLastSejour(){
            Sejour sejour ;
            sejour = ((DmaDAO)DAOFactory.getDmaDAO()).findSejourActuel(Integer.parseInt(ipp.toString()));
            return sejour;
    }
/**
 * affiche la liste des séjours du DM du patient 
 * @return 
 */
    @Override
    public String toString() {
        return "DM{" + "liste de séjour=" + lSejour + '}';
    }
    
    
}
