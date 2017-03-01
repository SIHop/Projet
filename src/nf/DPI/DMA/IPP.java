package nf.DPI.DMA;

import nf.DPI.DPI;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

public class IPP {

    private int IPP;

//    public IPP(DPI dpi) {
//        this.IPP = dpi.getiPP().generationIPP();
//    }

    /*
    IPP:
    AAxxxxx
    
     */
//    private int generationIPP() {
//        ArrayList<Integer> lIPP;
//        lIPP = PatientDAO.getListeIPP();
//        int maxIPP = Collections.max(lIPP);
//        int annee = maxIPP;
//        while (annee > 100) {
//            System.out.println(annee % 10);
//            annee = annee / 10;
//        }
//        annee += 2000;
//        int year = Year.now().getValue();
//        if(year==annee){
//            return maxIPP + 1;
//        } else {
//            year = year - 2000;
//            return year*100000+1;
//        }
//    }

}
