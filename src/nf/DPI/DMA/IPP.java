package nf.DPI.DMA;

import db.GestionnaireDB.DpiDAO;
import nf.DPI.DPI;
import java.time.Year;
import java.util.ArrayList;

public class IPP {

    private final int IPP;
    
   
    public IPP(int ipp){
        if(ipp==0){
            this.IPP=generationIPP();
        } else {
            this.IPP=ipp;
        }
    }
    /**
     * permet de générer un IPP automatiquement en prenant en compte ceux déjà existant et l'année. 
     * @return 
     */
    private int generationIPP() {
        
        DpiDAO listeDPI = new DpiDAO();
        int maxIPP = listeDPI.getMaxId();
        int annee = maxIPP;
        while (annee > 100) {
            
            annee = annee / 10;
        }
        annee += 2000;
        int year = Year.now().getValue();
        if(year==annee){
            return maxIPP + 1;
        } else {
            year = year - 2000;
            return year*100000+1;
        }
    }

    /**
     * @return the IPP
     */
    public int getIPP() {
        return IPP;
    }
    public String toString() {
        return Integer.toString(IPP);
    }
    

}
