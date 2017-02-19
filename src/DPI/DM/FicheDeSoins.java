package DPI.DM;

import GestionDexploitation.Personnel;
import java.util.ArrayList;
import GestionDexploitation.Service;
import DPI.DMA.Sejour;

public class FicheDeSoins {

    private String resultat;
    private Sejour sejour;
    private ArrayList  listeActes;
    private Service service;
    private ArrayList  presctiption;
    private Personnel createur;

    public FicheDeSoins(String resultat, Sejour sejour, ArrayList listeActes, Service service, ArrayList presctiption, Personnel createur) {
        this.resultat = resultat;
        this.sejour = sejour;
        this.listeActes = listeActes;
        this.service = service;
        this.presctiption = presctiption;
        this.createur = createur;
    }

}