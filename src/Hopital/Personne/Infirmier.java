package Hopital.Personne;

import Adresse.*;
import Contact.*;
import GestionDexploitation.MotDePasse;
import GestionDexploitation.Service;
import GestionDexploitation.TypeInfirmier;
import Hopital.Personne.Sexe;
import java.util.ArrayList;

public class Infirmier extends Personnel {

    private Service service;
    private TypeInfirmier type;

    public Infirmier(Service service, TypeInfirmier type, String nom, String prenom, String idPersonel, Sexe sexe, Adresse adresse, Date dateDeNaissance, MotDePasse password, Contact contact, ArrayList myFicheDeSoins) {
        super(nom, prenom, idPersonel, sexe, adresse, dateDeNaissance, password, contact, myFicheDeSoins);
        this.service = service;
        this.type = type;
    }
}