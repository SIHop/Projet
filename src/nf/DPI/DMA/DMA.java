package nf.DPI.DMA;

import java.util.ArrayList;

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
