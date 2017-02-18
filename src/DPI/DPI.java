package DPI;

import DPI.Personne.InformationDeContact;
import DPI.Personne.Sexe;
import Adresse.Adresse;
import java.util.ArrayList;
import java.util.Date;
import GestionDexploitation.Lit;
import DPI.DMA.NSS;
import DPI.DMA.Sejour;

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

    public DPI(String nom, String prenom, Adresse adresse, IPP iPP, Date dateDeNaissance, NSS nSecuriteSocial, Lit lit, ArrayList myDM, ArrayList myDMA, Sexe sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.iPP = iPP;
        this.dateDeNaissance = dateDeNaissance;
        this.nSecuriteSocial = nSecuriteSocial;
        this.lit = lit;
        this.myDM = myDM;
        this.myDMA = myDMA;
        this.sexe = sexe;
    }

    

}