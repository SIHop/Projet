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
class Acte {
    public int coef; 
    private String observation; 
    public Code code; 
    
    public Acte (int coef, String observation, Code code){
        this.coef = coef; 
        this.observation = observation; 
        this.code = code;
    }
    public Code getCode(){
        return code;
    }
    public int getCoef (){
        return coef;
    }
    public String getObservation(){
        return observation;
    }
    public String setObservation(String acteObservation){
        observation = acteObservation; 
        return acteObservation;
    }
}