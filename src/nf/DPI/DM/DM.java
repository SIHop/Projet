/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import java.util.ArrayList;
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

    @Override
    public String toString() {
        return "DM{" + "lSejour=" + lSejour + '}';
    }
    
    
}
