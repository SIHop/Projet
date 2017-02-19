package DPI;

import DPI.Personne.*;
import Adresse.*;
import DPI.DM.*;
import java.util.ArrayList;
import java.util.Date;
import GestionDexploitation.Lit;
import DPI.DMA.*;

public class DPI {
    
    private String nom;
    private String prenom;
    private Adresse adresse;
    private IPP iPP;
    private Date dateDeNaissance;
    private NSS nSecuriteSocial;
    private InformationDeContact infoDeContact;
    private Lit lit;
    private ArrayList myDM;
    private ArrayList myDMA;
    private Sexe sexe;

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