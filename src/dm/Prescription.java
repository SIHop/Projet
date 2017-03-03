/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

/**
 *
 * @author Deniz
 */
class Prescription {
    private String observation; 
    public TypePrescription typePrescription; 
    
    public Prescription (String observation, TypePrescription typePrecription){
        this.observation = observation;
        this.typePrescription = typePrecription;
    }

    Prescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public String getObservation(){
        return observation;
    }
    public String setObservation(String pObservation){
        observation = pObservation; 
        return pObservation;
    }
      public TypePrescription getTypePrecription(){
        return typePrescription;
    }
    
}
