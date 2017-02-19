package Hopital.Personne;

import Contact.*;
import Adresse.*;
import GestionDexploitation.MotDePasse;
import GestionDexploitation.RangMedecin;
import GestionDexploitation.Service;
import Hopital.Personne.Sexe;
import java.util.ArrayList;

public class Medecin extends Personnel {

    private String nSP;
    private RangMedecin type;
    private Service service;

    public Medecin(String nSP, RangMedecin type, Service service, String nom, String prenom, String idPersonel, Sexe sexe, Adresse adresse, Date dateDeNaissance, MotDePasse password, Contact contact, ArrayList myFicheDeSoins) {
        super(nom, prenom, idPersonel, sexe, adresse, dateDeNaissance, password, contact, myFicheDeSoins);
        this.nSP = nSP;
        this.type = type;
        this.service = service;
    }
}
