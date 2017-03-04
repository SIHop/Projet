package nf.DPI;

import nf.DPI.DMA.IPP;
import nf.DPI.DMA.NSS;
import nf.Adresse.Adresse;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Sexe;
import nf.GestionDexploitation.Lit;
import java.util.Date;
import nf.DPI.DM.DM;
import nf.DPI.DMA.DMA;

public class DPI {
    
    private String nomUsage;
    private Adresse adresse;
    private InformationDeContact infoDeContact;
    private Lit lit;
    private DM myDM;
    private DMA myDMA;
    
    private final IPP iPP;
    private final String prenom;
    private final String nomNaissance;
    private final Date dateDeNaissance;
    private final NSS nSecuriteSocial;
    private final Sexe sexe;

    public DPI(String nomNaissance, String nomUsage, String prenom, Adresse adresse, IPP iPP, Date dateDeNaissance, NSS nSecuriteSocial, InformationDeContact infoDeContact, Lit lit, DM myDM, DMA myDMA, Sexe sexe) {
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

    public Date getDateDeNaissance() {
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
    
}