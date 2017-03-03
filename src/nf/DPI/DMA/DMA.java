package nf.DPI.DMA;

import nf.Adresse.Adresse;
import nf.DPI.DPI;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Lit;
import nf.GestionDexploitation.Sexe;
import java.util.ArrayList;
import java.util.Date;

public class DMA {

    private ArrayList<Sejour> listeDeSejour;
    private String IPP;

    public DMA(ArrayList<Sejour> listeDeSejour, String ipp) {
        this.listeDeSejour = listeDeSejour;
        this.IPP = ipp;
    }

    /**
     * @return the listeDeSejour
     */
    public ArrayList<Sejour> getListeDeSejour() {
        return listeDeSejour;
    }

    /**
     * @return the IPP
     */
    public String getIPP() {
        return IPP;
    }

}
