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
public enum TypePrescription {
    EXAMEN_SANGUIN("EXAMEN_SANGUIN"),
    EXAMEN_RADIOLOGIQUE("EXAMEN_RADIOLOGIQUE"),
    MEDICAMENT("MEDICAMENT"),
    CONSULTATION("CONSULTATION"); 
    
    private String labelle;

    private TypePrescription(String labelle) {
        this.labelle = labelle;
    }

    /**
     * @return the labelle
     */
    public String getLabelle() {
        return labelle;
    }

    
}