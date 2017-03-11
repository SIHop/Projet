package nf.DPI.DMA;

import java.util.ArrayList;
import nf.GestionDexploitation.Medecin;
import java.util.Date;
import nf.Adresse.DateT;
import nf.DPI.DM.FicheDeSoins;

public class Sejour {

    private ArrayList<FicheDeSoins> lFicheDeSoins;
    private ArrayList<String> natureDesPrestation;
    private DateT dateDeFin;
    private LettreDeSortie lettreDeSortie;

    private final DateT dateDebut;
    private final Medecin medecinResponsable;
    private String numeroDeSejour;

    public Sejour(String numeroDeSejour, DateT dateDebut, Medecin medecinResponsable) {
        this.numeroDeSejour = numeroDeSejour;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
    }

    public Sejour(LettreDeSortie lettreDeSortie, String numeroDeSejour, ArrayList<String> natureDesPrestation, DateT dateDebut, DateT dateDeFin, Medecin medecinResponsable, ArrayList<FicheDeSoins> lFicheDeSoins) {
        this.lFicheDeSoins = lFicheDeSoins;
        this.natureDesPrestation = natureDesPrestation;
        this.dateDeFin = dateDeFin;
        this.lettreDeSortie = lettreDeSortie;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
        this.numeroDeSejour = numeroDeSejour;
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

    public DateT getDateDebut() {
        return dateDebut;
    }

    public DateT getDateDeFin() {
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

    public void setDateDeFin(DateT dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public void setLettreDeSortie(LettreDeSortie lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
    }

    @Override
    public String toString() {
        //return "Sejour{" + "lFicheDeSoins=" + lFicheDeSoins + ", natureDesPrestation=" + natureDesPrestation + ", dateDeFin=" + dateDeFin + ", lettreDeSortie=" + lettreDeSortie + ", dateDebut=" + dateDebut + ", medecinResponsable=" + medecinResponsable + ", numeroDeSejour=" + numeroDeSejour + '}';
        return "Numero de sejour = " + this.numeroDeSejour;
    }

    /**
     * @param numeroDeSejour the numeroDeSejour to set
     */
    public void setNumeroDeSejour(String numeroDeSejour) {
        this.numeroDeSejour = numeroDeSejour;
    }

}
