package Hopital.DPI.DM;

import Hopital.Personne.Personnel;
import java.util.ArrayList;
import GestionDexploitation.Service;
import Hopital.DPI.DMA.Sejour;

public class FicheDeSoins {

  private String resultat;

      private Sejour sejour;
    /**
   * 
   * @element-type Acte
   */
  private ArrayList  listeActes;
    private Service service;
    /**
   * 
   * @element-type Prescription
   */
  private ArrayList  presctiption;
    private Personnel createur;

}