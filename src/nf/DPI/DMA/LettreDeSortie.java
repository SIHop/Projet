package nf.DPI.DMA;

import nf.Adresse.Adresse;

public class LettreDeSortie {

    private final int idPersonnel;
    private final Adresse adressePatient;
    private final int numeroSejour;
    private final String lettre;

    public LettreDeSortie(int idPersonnel, Adresse adressePatient, int numeroSejour, String lettre) {
        this.idPersonnel = idPersonnel;
        this.adressePatient = adressePatient;
        this.numeroSejour = numeroSejour;
        this.lettre = lettre;
    }

    /**
     * @return the idPersonnel
     */
    public int getIdPersonnel() {
        return idPersonnel;
    }

    /**
     * @return the adressePatient
     */
    public Adresse getAdressePatient() {
        return adressePatient;
    }

    /**
     * @return the numeroSejour
     */
    public int getNumeroSejour() {
        return numeroSejour;
    }

    /**
     * @return the lettre
     */
    public String getLettre() {
        return lettre;
    }

}
