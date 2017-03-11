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

    @Override
    public String toString() {
        String retour = "DMA{" + "IPP=" + IPP + " liste de sejour :" + '}';
        
            for(Sejour s : this.listeDeSejour) {
                if(s != null){
                    retour += " " + s.toString();
                }               
            }
        
        return retour;
    }

    /**
     * @return the IPP
     */
    public String getIPP() {
        return IPP;
    }

    /**
     * @param IPP the IPP to set
     */
    public void setIPP(String IPP) {
        this.IPP = IPP;
    }

}
