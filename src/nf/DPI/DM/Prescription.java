/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import db.GestionnaireDB.DAOFactory;

/**
 *
 * @author Deniz
 */
public class Prescription {

    private String prescription;
    private String observation;
    private TypePrescription typePrescription;
    private int idPrescription;
    private int idFicheDeSoins;

    public Prescription(int idFicheDeSoins, String prescription, String observation, TypePrescription typePrescription) {
        this.prescription = prescription;
        this.observation = observation;
        this.typePrescription = typePrescription;
        this.idFicheDeSoins = idFicheDeSoins;
        this.idPrescription = DAOFactory.getPrescriptionDAO().getMaxId() + 1;

    }

    //Constructeur utilisé par la db
    public Prescription(int idPrescription, int idFicheDeSoins, String prescription, String observation, TypePrescription typePrescription) {
        this.prescription = prescription;
        this.observation = observation;
        this.typePrescription = typePrescription;
        this.idFicheDeSoins = idFicheDeSoins;
        this.idPrescription = idPrescription;
    }

    public TypePrescription getTypePrescription() {
        return typePrescription;
    }

    public String getPrescription() {
        return prescription;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @return the idPrescription
     */
    public int getIdPrescription() {
        return idPrescription;
    }

    /**
     * @return the idFicheDeSoins
     */
    public int getIdFicheDeSoins() {
        return idFicheDeSoins;
    }
    /**
     * affiche le numéro de la prescription, le numéro de la fiche de soins, les observations faites ainsi que le type de prescription demandé
     * @return 
     */
    public String toString(){
        return "id "+ this.idPrescription + " fiche de soins :" + this.idFicheDeSoins + " prescription : "+ this.getPrescription()+ " observation : " +this.observation+ " type de prescription : " + this.typePrescription.toString() ;
    }

    /**
     * @param prescription the prescription to set
     */
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * @param typePrescription the typePrescription to set
     */
    public void setTypePrescription(TypePrescription typePrescription) {
        this.typePrescription = typePrescription;
    }

    /**
     * @param idFicheDeSoins the idFicheDeSoins to set
     */
    public void setIdFicheDeSoins(int idFicheDeSoins) {
        this.idFicheDeSoins = idFicheDeSoins;
    }

}
