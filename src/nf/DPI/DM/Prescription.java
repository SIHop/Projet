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

    private String observation;
    public TypePrescription typePrescription;

    public Prescription(String observation, TypePrescription typePrescription) {
        this.observation = observation;
        this.typePrescription = typePrescription;
    }

    public String getObservation() {
        return observation;
    }

    public TypePrescription getTypePrescription() {
        return typePrescription;
    }

}
