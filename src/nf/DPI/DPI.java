package nf.DPI;

import nf.DPI.DMA.IPP;
import nf.DPI.DMA.NSS;
import nf.Adresse.Adresse;
import nf.GestionDexploitation.InformationDeContact;
import nf.GestionDexploitation.Sexe;
import nf.GestionDexploitation.Lit;
import java.util.ArrayList;
import java.util.Date;
import nf.DPI.DM.DM;
import nf.DPI.DMA.DMA;

public class DPI {
    
    protected String nomNaissance;
    protected String nomUsage;
    protected String prenom;
    protected Adresse adresse;
    protected IPP iPP;
    protected Date dateDeNaissance;
    protected NSS nSecuriteSocial;
    protected InformationDeContact infoDeContact;
    protected Lit lit;
    protected DM myDM;
    protected DMA myDMA;
    protected Sexe sexe;

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

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public IPP getiPP() {
        return iPP;
    }

    public void setiPP(IPP iPP) {
        this.iPP = iPP;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public NSS getnSecuriteSocial() {
        return nSecuriteSocial;
    }

    public void setnSecuriteSocial(NSS nSecuriteSocial) {
        this.nSecuriteSocial = nSecuriteSocial;
    }

    public InformationDeContact getInfoDeContact() {
        return infoDeContact;
    }

    public void setInfoDeContact(InformationDeContact infoDeContact) {
        this.infoDeContact = infoDeContact;
    }

    public Lit getLit() {
        return lit;
    }

    public void setLit(Lit lit) {
        this.lit = lit;
    }

    public DM getMyDM() {
        return myDM;
    }

    public void setMyDM(DM myDM) {
        this.myDM = myDM;
    }

    public DMA getMyDMA() {
        return myDMA;
    }

    public void setMyDMA(DMA myDMA) {
        this.myDMA = myDMA;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    
    
    
    
}