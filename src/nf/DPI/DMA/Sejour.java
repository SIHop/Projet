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
    private boolean facturer;
    private String observation;

    private DMA dma = null;
    private DPI dpi = null;

    public Sejour(String numeroDeSejour, DateT dateDebut, Medecin medecinResponsable) {
        this.numeroDeSejour = numeroDeSejour;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
    }

    public Sejour(LettreDeSortie lettreDeSortie, String numeroDeSejour, ArrayList<String> natureDesPrestation, DateT dateDebut, DateT dateDeFin, Medecin medecinResponsable, ArrayList<FicheDeSoins> lFicheDeSoins, boolean enCours, boolean facturer, String observation) {
        this.lFicheDeSoins = lFicheDeSoins;
        this.natureDesPrestation = natureDesPrestation;
        this.dateDeFin = dateDeFin;
        this.lettreDeSortie = lettreDeSortie;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
        this.numeroDeSejour = numeroDeSejour;
        this.enCours = enCours;
        this.facturer = facturer;
        this.observation = observation;
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
/**
 * Permet d'ajouter une fiche de soins à la liste de fiches de soins 
 * @param ficheDeSoin 
 */
    public void addFicheDeSoin(FicheDeSoins ficheDeSoin) {
        this.getlFicheDeSoins().add(ficheDeSoin);
    }
/**
 * Permet d'ajouter une nature de prestation à la liste des natures des prestation
 * @param natureDesPrestations 
 */
    public void addNatureDesPrestation(String natureDesPrestations) {
        this.getNatureDesPrestation().add(natureDesPrestations);
    }

    public void setDateDeFin(DateT dateDeFin) {
        if (dateDeFin == null) {
            this.setEnCours(true);
        } else {
            this.setEnCours(false);
        }
        this.dateDeFin = dateDeFin;
    }

    public void setLettreDeSortie(LettreDeSortie lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
    }
/**
 * permet d'afficher le numéro de séjour, le nom d'usage, nom de naissance et le prénom du patient
 * @return 
 */
    @Override
    public String toString() {
        //return "Sejour{" + "lFicheDeSoins=" + lFicheDeSoins + ", natureDesPrestation=" + natureDesPrestation + ", dateDeFin=" + dateDeFin + ", lettreDeSortie=" + lettreDeSortie + ", dateDebut=" + dateDebut + ", medecinResponsable=" + medecinResponsable + ", numeroDeSejour=" + numeroDeSejour + '}';
        if (this.dma == null) {
            this.dma = DAOFactory.getDmaDAO().find(new ArrayList<>(Arrays.asList("numeroSejour")), new ArrayList<>(Arrays.asList(this.numeroDeSejour)));
            String ipp = dma.getIPP();
            this.dpi = DAOFactory.getDpiDAO().find(new ArrayList<>(Arrays.asList("IPP")), new ArrayList<>(Arrays.asList(ipp)));
        }

        String retour = "Numero de sejour = " + this.numeroDeSejour + "\n"
                + " Nom Usage " + this.dpi.getNomUsage() + " Nom Naissance " + this.dpi.getNomNaissance() + "\n" + " prénom " + this.dpi.getPrenom();
        if(!this.isEnCours()){
            retour += " est facturer  : " + this.isFacturer();
        }
        
        return retour;
    }

    /**
     * @param numeroDeSejour the numeroDeSejour to set
     */
    public void setNumeroDeSejour(String numeroDeSejour) {
        this.numeroDeSejour = numeroDeSejour;
    }

    /**
     * permet de confirmer ou non si le séjour est toujours en cours ou non
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

    /**
     * permet de savoir si le séjour est facturé ou non 
     * @return the facturer
     */
    public boolean isFacturer() {
        return facturer;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return observation;
    }

}
