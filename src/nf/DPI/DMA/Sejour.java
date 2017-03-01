package nf.DPI.DMA;

import nf.GestionDexploitation.Medecin;
import nf.DPI.DM.DM;
import java.util.Date;

public class Sejour {

    private LettreDeSortie lettreDeSortie;
    private String numeroDeSejour;

    private DM natureDesPrestation;

    private Date dateDebut;
    private Date dateDeFin;
    private Medecin medecinResponsable;

    public Sejour(LettreDeSortie lettreDeSortie, String numeroDeSejour, DM natureDesPrestation, Date dateDebut, Date dateDeFin, Medecin medecinResponsable) {
        this.lettreDeSortie = lettreDeSortie;
        this.numeroDeSejour = numeroDeSejour;
        this.natureDesPrestation = natureDesPrestation;
        this.dateDebut = dateDebut;
        this.dateDeFin = dateDeFin;
        this.medecinResponsable = medecinResponsable;
    }

    

    public String getNumeroDeSejour() {
        return numeroDeSejour;
    }

    public void setNumeroDeSejour(String numeroDeSejour) {
        this.numeroDeSejour = numeroDeSejour;
    }

    public DM getNatureDesPrestation() {
        return natureDesPrestation;
    }

    public void setNatureDesPrestation(DM natureDesPrestation) {
        this.natureDesPrestation = natureDesPrestation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateDeFin() {
        return dateDeFin;
    }

    public void setDateDeFin(Date dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public Medecin getMedecinResponsable() {
        return medecinResponsable;
    }

    public void setMedecinResponsable(Medecin medecinResponsable) {
        this.medecinResponsable = medecinResponsable;
    }

    public LettreDeSortie getLettreDeSortie() {
        return lettreDeSortie;
    }

    public void setLettreDeSortie(LettreDeSortie lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
    }
    
    
  
}