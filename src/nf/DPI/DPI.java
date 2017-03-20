package nf.DPI;

import java.util.Calendar;
import java.util.Locale;
import library.interfaces.Patient;
import library.interfaces.PatientLocation;
import nf.DPI.DMA.IPP;
import nf.DPI.DMA.NSS;
import nf.Adresse.Adresse;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Sexe;
import nf.GestionDexploitation.Location;
import nf.Adresse.DateT;
import nf.DPI.DM.DM;
import nf.DPI.DMA.DMA;

public class DPI {

    private String nomUsage;

    private Adresse adresse;
    private InformationDeContact infoDeContact;
    private Location lit;
    private DM myDM;
    private DMA myDMA;

    private IPP iPP;
    private final String prenom;
    private final String nomNaissance;
    private final DateT dateDeNaissance;
    private final NSS nSecuriteSocial;
    private final Sexe sexe;
    private final String lieuNaissance;

    public DPI(String nomNaissance, String nomUsage, String prenom, Adresse adresse, IPP iPP, DateT dateDeNaissance, NSS nSecuriteSocial, InformationDeContact infoDeContact, Location lit, DM myDM, DMA myDMA, Sexe sexe) {
        this.nomNaissance = nomNaissance;
        this.nomUsage = nomUsage;
        this.prenom = prenom;
        this.adresse = adresse;
        this.iPP = iPP;
        this.dateDeNaissance = dateDeNaissance;
        this.nSecuriteSocial = nSecuriteSocial;
        this.infoDeContact = infoDeContact;
        this.lit = lit;
        this.myDM = myDM;
        this.myDMA = myDMA;
        this.sexe = sexe;
        this.lieuNaissance = "";
    }

    public DPI(String nomNaissance, String nomUsage, String prenom, Adresse adresse, IPP iPP, DateT dateDeNaissance, NSS nSecuriteSocial, InformationDeContact infoDeContact, Location lit, DM myDM, DMA myDMA, Sexe sexe, String lieuNaissance) {
        this.nomNaissance = nomNaissance;
        this.nomUsage = nomUsage;
        this.prenom = prenom;
        this.adresse = adresse;
        this.iPP = iPP;
        this.dateDeNaissance = dateDeNaissance;
        this.nSecuriteSocial = nSecuriteSocial;
        this.infoDeContact = infoDeContact;
        this.lit = lit;
        this.myDM = myDM;
        this.myDMA = myDMA;
        this.sexe = sexe;
        this.lieuNaissance = lieuNaissance;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public IPP getiPP() {
        return iPP;
    }

    public DateT getDateDeNaissance() {
        return dateDeNaissance;
    }

    public NSS getnSecuriteSocial() {
        return nSecuriteSocial;
    }

    public InformationDeContact getInfoDeContact() {
        return infoDeContact;
    }

    public Location getLit() {
        return lit;
    }

    public DM getMyDM() {
        return myDM;
    }

    public DMA getMyDMA() {
        return myDMA;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setInfoDeContact(InformationDeContact infoDeContact) {
        this.infoDeContact = infoDeContact;
    }

    public void setLit(Location lit) {
        this.lit = lit;
    }

    public void setMyDM(DM myDM) {
        this.myDM = myDM;
    }

    public void setMyDMA(DMA myDMA) {
        this.myDMA = myDMA;
    }

    /**
     * @param iPP the iPP to set
     */
    public void setiPP(IPP iPP) {
        this.iPP = iPP;
    }

    /**
     * Permet la création d'un nouveau patient en prenant en compte sa date de
     * naissance, son prénom, sa localisation et son sexe si le patient a déjà
     * un lit : on a alors accès à l'identifiant, au batiment et à l'étage de la
     * localisation Les informations du patient sont affichés
     *
     * @return
     */
    public Patient dpiToPatient() {
        Patient p = new Patient(iPP.getIPP(), prenom, 'I');
        p.setBirth(this.dateDeNaissance.getC().getTime());
        if (this.myDMA.getListeDeSejour().get(this.myDMA.getListeDeSejour().size() - 1).isEnCours()) {
            p.setDateDicharge(null);
        } else {
            p.setDateDicharge(this.myDM.getLastSejour().getDateDeFin().getC().getTime());
        }
        Historique hist = Historique.getHistoriqueByIPP(iPP.getIPP());
        if (hist != null) {
            p.setDeath(true);
            p.setDeath(hist.getDateDeces().getC().getTime());
        } else {
            p.setDeath(false);
        }
        p.setFirstName(nomUsage);
        if (this.sexe == Sexe.FEMME) {
            p.setSex('F');
        } else {
            p.setSex('H');
        }
        PatientLocation patientLocation = new PatientLocation(p);
        p.setAssignedPatLocation(patientLocation);
        if (this.lit != null) {
            patientLocation.setBed(this.getLit().getIdLit());
            patientLocation.setBuilding(this.getLit().getLocalisation().getBatiment());
            patientLocation.setFloor(Integer.toString(this.getLit().getLocalisation().getEtage()));
            patientLocation.setStatus("o");
        } else {
            patientLocation.setBed("");
            patientLocation.setBuilding("");
            patientLocation.setFloor("");
            patientLocation.setStatus("");
        }
        patientLocation.setPersonLocationType("");
        patientLocation.setPointOfCare("");
        patientLocation.setRoom("");
        PatientLocation priorLocation = new PatientLocation(p);
        p.setPriorPatLocation(priorLocation);
        priorLocation.setBed("");
        priorLocation.setBuilding("");
        priorLocation.setFloor("");
        priorLocation.setPersonLocationType("");
        priorLocation.setPointOfCare("");
        priorLocation.setRoom("");
        priorLocation.setStatus("");
        return p;
    }

    /**
     * Permet de prendre en compte les informations du patient p crée en
     * considérant la date de naissance du patient et son sexe. Affiche le dpi
     * du patient
     *
     * @param p
     * @return
     */
    public static DPI patientToDpi(Patient p) {
        DateT d = new DateT(p.getBirth());
        Sexe sexe;
        switch (p.getCharSex()) {
            case 'F':
                sexe = Sexe.FEMME;
                break;
            default:
                sexe = Sexe.HOMME;
                break;
        }

        DPI dpi = new DPI(p.getFamillyName(), p.getFamillyName(), p.getFirstName(), null, new IPP(p.getID()), d, null, null, null, null, null, sexe);

        return dpi;
    }

    /**
     * renvoi informations du patient ainsi que son IPP
     *
     * @return
     */
    @Override
    public String toString() {
        return nomUsage + " " + prenom + ", " + sexe + ", né(e) le  " + dateDeNaissance + " IPP : " + iPP.getIPP();
    }

    /**
     * @return the lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

}
