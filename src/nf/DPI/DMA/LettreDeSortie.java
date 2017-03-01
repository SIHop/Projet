package nf.DPI.DMA;

import nf.Adresse.Adresse;

public class LettreDeSortie {

    private int idPersonnel;
    private Adresse adressePatient;
    private DMA patient;
    private String lettre;

    public LettreDeSortie(int idPersonnel, Adresse adressePatient, DMA patient, String lettre) {
        this.idPersonnel = idPersonnel;
        this.adressePatient = adressePatient;
        this.patient = patient;
        this.lettre = lettre;
    }

}
