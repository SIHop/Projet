package nf.DPI.DMA;

import db.GestionnaireDB.DAOFactory;
import java.util.ArrayList;
import java.util.Arrays;
import nf.GestionDexploitation.Medecin;
import nf.Adresse.DateT;
import nf.DPI.DM.FicheDeSoins;
import nf.DPI.DPI;

public class Sejour {

    private ArrayList<FicheDeSoins> lFicheDeSoins;
    private ArrayList<String> natureDesPrestation;
    private DateT dateDeFin;
    private LettreDeSortie lettreDeSortie;

    private final DateT dateDebut;
    private final Medecin medecinResponsable;
    private String numeroDeSejour;
    private boolean enCours;

    public Sejour(String numeroDeSejour, DateT dateDebut, Medecin medecinResponsable) {
        this.numeroDeSejour = numeroDeSejour;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
    }

    public Sejour(LettreDeSortie lettreDeSortie, String numeroDeSejour, ArrayList<String> natureDesPrestation, DateT dateDebut, DateT dateDeFin, Medecin medecinResponsable, ArrayList<FicheDeSoins> lFicheDeSoins, boolean enCours) {
        this.lFicheDeSoins = lFicheDeSoins;
        this.natureDesPrestation = natureDesPrestation;
        this.dateDeFin = dateDeFin;
        this.lettreDeSortie = lettreDeSortie;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
        this.numeroDeSejour = numeroDeSejour;
        this.enCours = enCours;
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
        if(dateDeFin == null){
            this.setEnCours(true);
        }else{
            this.setEnCours(false);
        }
        this.dateDeFin = dateDeFin;
    }

    public void setLettreDeSortie(LettreDeSortie lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
    }

    @Override
    public String toString() {
        //return "Sejour{" + "lFicheDeSoins=" + lFicheDeSoins + ", natureDesPrestation=" + natureDesPrestation + ", dateDeFin=" + dateDeFin + ", lettreDeSortie=" + lettreDeSortie + ", dateDebut=" + dateDebut + ", medecinResponsable=" + medecinResponsable + ", numeroDeSejour=" + numeroDeSejour + '}';
        DMA dma = DAOFactory.getDmaDAO().find(new ArrayList<>(Arrays.asList("numeroSejour")), new ArrayList<>(Arrays.asList(this.numeroDeSejour)));
        String ipp = dma.getIPP();
        DPI dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(ipp)));

        return "Numero de sejour = " + this.numeroDeSejour + "\n"
                +"Nom Usage"+dpi.getNomUsage()+"Nom Naissance"+dpi.getNomNaissance()+"\n"+"prénom"+dpi.getPrenom();
    }

    /**
     * @param numeroDeSejour the numeroDeSejour to set
     */
    public void setNumeroDeSejour(String numeroDeSejour) {
        this.numeroDeSejour = numeroDeSejour;
    }

    /**
     * @return the enCours
     */
    public boolean isEnCours() {
        return enCours;
    }

    /**
     * @param enCours the enCours to set
     */
    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

}
