package Hopital.DPI.DMA;

import Adresse.Adresse;
import Contact.Contact;
import GestionDexploitation.Lit;
import Hopital.DPI.*;
import Hopital.Personne.Sexe;
import java.util.ArrayList;
import java.util.Date;

public class DMA extends DPI{

    private ArrayList listeDeSejour;
    private ArrayList myDPI;

    public DMA(String nom, String prenom, Adresse adresse, IPP iPP, Date dateDeNaissance, NSS nSecuriteSocial, Contact infoDeContact, Lit lit, ArrayList myDM, ArrayList myDMA, Sexe sexe) {
        super(nom, prenom, adresse, iPP, dateDeNaissance, nSecuriteSocial, infoDeContact, lit, myDM, myDMA, sexe);
    }

    public DMA(String nom, String prenom, Adresse adresse, IPP iPP, Date dateDeNaissance, NSS nSecuriteSocial, Lit lit, ArrayList myDM, ArrayList myDMA, Sexe sexe) {
        super(nom, prenom, adresse, iPP, dateDeNaissance, nSecuriteSocial, lit, myDM, myDMA, sexe);
    }

    

}