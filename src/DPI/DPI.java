package DPI;

import GestionDexploitation.InformationDeContact;
import GestionDexploitation.Sexe;
import Adresse.*;
import DPI.DM.*;
import DPI.DMA.*;
import GestionDexploitation.Lit;
import java.util.ArrayList;
import java.util.Date;

public class DPI {
    
    protected String nom;
    protected String prenom;
    protected Adresse adresse;
    protected IPP iPP;
    protected Date dateDeNaissance;
    protected NSS nSecuriteSocial;
    protected InformationDeContact infoDeContact;
    protected Lit lit;
    protected ArrayList myDM;
    protected ArrayList myDMA;
    protected Sexe sexe;

    public DPI(String nom, String prenom, Adresse adresse, IPP iPP, Date dateDeNaissance, NSS nSecuriteSocial, InformationDeContact infoDeContact, Lit lit, ArrayList myDM, ArrayList myDMA, Sexe sexe) {
        this.nom = nom;
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

    public void addDMA(DMA dma){
        myDMA.add(dma);
    }
    
    public void addDM(DM dm){
        myDM.add(dm);
    }

    public ArrayList getMyDM() {
        return myDM;
    }

    public void setMyDM(ArrayList myDM) {
        this.myDM = myDM;
    }

    public ArrayList getMyDMA() {
        return myDMA;
    }

    public void setMyDMA(ArrayList myDMA) {
        this.myDMA = myDMA;
    }
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    
    
    
}