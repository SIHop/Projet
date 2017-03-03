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
class Prescription {

    private String observation;
    public TypePrescription typePrescription;

    public Prescription(String observation, TypePrescription typePrecription) {
        this.observation = observation;
        this.typePrescription = typePrecription;
    }

    public String getObservation() {
        return observation;
    }

    public TypePrescription getTypePrecription() {
        return typePrescription;
    }

}
