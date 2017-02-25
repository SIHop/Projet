package DPI.DMA;

import Adresse.Adresse;
import DPI.DPI;
import GestionDexploitation.InformationDeContact;
import GestionDexploitation.Lit;
import GestionDexploitation.Sexe;
import java.util.ArrayList;
import java.util.Date;

public class DMA extends DPI {

    private ArrayList listeDeSejour;
    public ArrayList myDPI;

    public DMA(ArrayList listeDeSejour, ArrayList myDPI, DPI dpi) {
        super(dpi.getNomNaissance(), dpi.getNomUsage(), dpi.getPrenom(), dpi.getAdresse(), dpi.getiPP(), dpi.getDateDeNaissance(), dpi.getnSecuriteSocial(), dpi.getInfoDeContact(), dpi.getLit(), dpi.getMyDM(), dpi.getMyDMA(), dpi.getSexe());
        this.listeDeSejour = listeDeSejour;
        this.myDPI = myDPI;
    }

}
