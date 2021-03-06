package nf.DPI.DMA;

import db.GestionnaireDB.DAOFactory;
import db.GestionnaireDB.DpiDAO;
import db.GestionnaireDB.SejourDAO;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    private ArrayList<String> observation;

    private DMA dma = null;
    private DPI dpi = null;

    public Sejour(String numeroDeSejour, DateT dateDebut, Medecin medecinResponsable) {
        this.numeroDeSejour = numeroDeSejour;
        this.dateDebut = dateDebut;
        this.medecinResponsable = medecinResponsable;
        this.lFicheDeSoins = new ArrayList<>();
        this.enCours = true;
        this.facturer = false;
        this.observation = new ArrayList<>();
        this.natureDesPrestation = new ArrayList<>();
    }

    public Sejour(LettreDeSortie lettreDeSortie, String numeroDeSejour, ArrayList<String> natureDesPrestation, DateT dateDebut, DateT dateDeFin, Medecin medecinResponsable, ArrayList<FicheDeSoins> lFicheDeSoins, boolean enCours, boolean facturer, ArrayList<String> observation) {
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
    
    public void addObservation(String obs){
        this.observation.add(obs);
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

        String retour = "Numéro de séjour = " + this.numeroDeSejour + "\n"
                + " Nom d'usage : " + this.dpi.getNomUsage() + ", Nom de naissance :" + this.dpi.getNomNaissance() + "\n" + ", Prénom : " + this.dpi.getPrenom();
        if(!this.isEnCours()){
            if(this.isFacturer()){
                retour += " est facturé";
            }else{
                retour+= " n'est pas facturé";
            }
            
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
     * permet de connaître si le séjour est toujours en cours ou non
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
    public ArrayList<String> getObservation() {
        return observation;
    }

    public static int generateNumeroSejour(){
        SejourDAO sejDAO =(SejourDAO)DAOFactory.getSejourDAO();
        int maxSej = sejDAO.getMaxId();
        int annee = maxSej;
        while (annee > 100) {
            annee = annee / 10;
        }
        int mois = maxSej;
        mois -= annee * 10000000;
        while(mois>14){
            mois = mois/10;
                    }
        annee += 2000;
        int year = Year.now().getValue();
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        System.out.println("month = " + month + " mois = " + mois + " year = " + year + " année = " + annee);
        if(year==annee && month == mois){
            return maxSej + 1;
        } else {
            int retour = year - 2000;
            retour = retour * 10000000;
            retour = retour + month*100000;
            return retour+1;
        }
    }

    /**
     * @param facturer the facturer to set
     */
    public void setFacturer(boolean facturer) {
        this.facturer = facturer;
    }
    
    public int calculCoutTotal(){
        int total = 0;    
        for(FicheDeSoins fds : lFicheDeSoins){
            total += fds.calculerCoutFiche();
        }
        return total;
    }
}
