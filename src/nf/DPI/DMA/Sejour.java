package nf.DPI.DMA;

import java.util.ArrayList;
import nf.GestionDexploitation.Medecin;
import java.util.Date;
import nf.DPI.DM.FicheDeSoins;

public class Sejour {

    private ArrayList<FicheDeSoins> lFicheDeSoins;
    private ArrayList<String> natureDesPrestation;
    private Date dateDeFin;
    private LettreDeSortie lettreDeSortie;
    
    private final Date dateDebut;
    private final Medecin medecinResponsable;
    private final String numeroDeSejour;

    public Sejour(String numeroDeSejour, Date dateDebut, Medecin medecinResponsable) {
        this.numeroDeSejour = numeroDeSejour;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
    }

    public ArrayList<FicheDeSoins> getlFicheDeSoins() {
        return lFicheDeSoins;
    }

    public LettreDeSortie getLettreDeSortie() {
        return lettreDeSortie;
    }

    public String getNumeroDeSejour() {
        return numeroDeSejour;
    }

    public ArrayList<String> getNatureDesPrestation() {
        return natureDesPrestation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateDeFin() {
        return dateDeFin;
    }

    public Medecin getMedecinResponsable() {
        return medecinResponsable;
    }

    public void addFicheDeSoin(FicheDeSoins ficheDeSoin) {
        this.getlFicheDeSoins().add(ficheDeSoin);
    }

    public void addNatureDesPrestation(String natureDesPrestations) {
        this.getNatureDesPrestation().add(natureDesPrestations);
    }

    public void setDateDeFin(Date dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public void setLettreDeSortie(LettreDeSortie lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
    }

    
    
    
  
}