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
import nf.GestionDexploitation.Lit;
import nf.Adresse.DateT;
import nf.DPI.DM.DM;
import nf.DPI.DMA.DMA;

public class DPI {

    private String nomUsage;

    @Override
    public String toString() {
        return "DPI{" + "nomUsage=" + nomUsage + ", adresse=" + adresse + ", infoDeContact=" + infoDeContact + ", lit=" + lit + ", myDM=" + myDM + ", myDMA=" + myDMA + ", iPP=" + iPP.getIPP() + ", prenom=" + prenom + ", nomNaissance=" + nomNaissance + ", dateDeNaissance=" + dateDeNaissance + ", nSecuriteSocial=" + nSecuriteSocial + ", sexe=" + sexe + '}';
    }
    private Adresse adresse;
    private InformationDeContact infoDeContact;
    private Lit lit;
    private DM myDM;
    private DMA myDMA;

    private IPP iPP;
    private final String prenom;
    private final String nomNaissance;
    private final DateT dateDeNaissance;
    private final NSS nSecuriteSocial;
    private final Sexe sexe;

    public DPI(String nomNaissance, String nomUsage, String prenom, Adresse adresse, IPP iPP, DateT dateDeNaissance, NSS nSecuriteSocial, InformationDeContact infoDeContact, Lit lit, DM myDM, DMA myDMA, Sexe sexe) {
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

    public Lit getLit() {
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

    public void setLit(Lit lit) {
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
     *
     */
    public Patient dpiToPatient() {
        Patient p = new Patient(this.getiPP().getIPP(), this.getNomUsage(), 'I');
        
        p.setBirth(this.getDateDeNaissance().getC().getTime());
        p.setFirstName(this.getPrenom());
        p.setAssignedPatLocation(new PatientLocation(p));
        
        char sex = 'X';
        switch (this.getSexe().ordinal()) {
            case 1:
                sex = 'H';
                break;
            case 0:
                sex = 'F';
                break;
        }
        if (sex != 'X') {
            p.setSex(sex);
        }
        if (this.getLit() != null) {
            p.getAssignedPatLocation().setBed(this.getLit().getIdentifient());
            p.getAssignedPatLocation().setBuilding(this.getLit().getLocalisation().getBatiment());
            p.getAssignedPatLocation().setFloor(Integer.toString(this.getLit().getLocalisation().getEtage()));
        }
        
//        p.setDeath(false);
//        
//        p.setDateDicharge(Calendar.getInstance(Locale.FRANCE).getTime());
//        p.setDeath(Calendar.getInstance(Locale.FRANCE).getTime());
//        p.setPriorPatLocation(null);
        
        
        return p;
    }

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

}
