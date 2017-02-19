package Hopital.Personne;

import Adresse.*;
import Contact.*;
import GestionDexploitation.MotDePasse;
import GestionDexploitation.Service;
import Hopital.Personne.Sexe;
import java.util.ArrayList;

public class AideSoignante extends Personnel {

    private Service service;

    public AideSoignante(Service service, String nom, String prenom, String idPersonel, Sexe sexe, Adresse adresse, Date dateDeNaissance, MotDePasse password, Contact contact, ArrayList myFicheDeSoins) {
        super(nom, prenom, idPersonel, sexe, adresse, dateDeNaissance, password, contact, myFicheDeSoins);
        this.service = service;
    }

    
    
}