package Hopital.Personne;

import Contact.*;
import Hopital.Personne.Sexe;
import Adresse.*;
import GestionDexploitation.MotDePasse;
import java.util.ArrayList;

public abstract class Personnel {

    private String nom;
    private String prenom;
    private String idPersonel;
    private Sexe sexe;
    private Adresse adresse;
    private Date dateDeNaissance;
    private MotDePasse password;
    private Contact contact;
    public ArrayList myFicheDeSoins;

    public Personnel(String nom, String prenom, String idPersonel, Sexe sexe, Adresse adresse, Date dateDeNaissance, MotDePasse password, Contact contact, ArrayList myFicheDeSoins) {
        this.nom = nom;
        this.prenom = prenom;
        this.idPersonel = idPersonel;
        this.sexe = sexe;
        this.adresse = adresse;
        this.dateDeNaissance = dateDeNaissance;
        this.password = password;
        this.contact = contact;
        this.myFicheDeSoins = myFicheDeSoins;
    }

    
}
