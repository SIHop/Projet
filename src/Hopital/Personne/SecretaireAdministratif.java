package Hopital.Personne;

import Adresse.*;
import Contact.*;
import GestionDexploitation.MotDePasse;
import Hopital.Personne.Sexe;
import java.util.ArrayList;

public class SecretaireAdministratif extends Personnel {

    private String bureau;

    public SecretaireAdministratif(String bureau, String nom, String prenom, String idPersonel, Sexe sexe, Adresse adresse, Date dateDeNaissance, MotDePasse password, Contact contact, ArrayList myFicheDeSoins) {
        super(nom, prenom, idPersonel, sexe, adresse, dateDeNaissance, password, contact, myFicheDeSoins);
        this.bureau = bureau;
    }

    
}