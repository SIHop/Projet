package dm;

/**
 *
 * @author Deniz
 */
class Acte {
    private int cout;
    private int coef;
    private String observation; 
    private Code code;
    
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