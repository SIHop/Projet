/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

/**
 *
 * @author Deniz
 */
public class Prescription {

    private String prescription;
    public TypePrescription typePrescription;

    public Prescription(String observation, TypePrescription typePrescription) {
        this.prescription = observation;
        this.typePrescription = typePrescription;
    }

    public TypePrescription getTypePrescription() {
        return typePrescription;
    }

    public String getPrescription() {
        return prescription;
    }

}
