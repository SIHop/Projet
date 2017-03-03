package nf.DPI.DM;

/**
 *
 * @author Deniz
 */
public class Acte {
    private int cout;
    private int coef;
    private String observation; 
    private Code code;

    public Acte(int cout, int coef, String observation, Code code) {
        this.cout = cout;
        this.coef = coef;
        this.observation = observation;
        this.code = code;
    }

    public int getCout() {
        return cout;
    }

    public int getCoef() {
        return coef;
    }

    public String getObservation() {
        return observation;
    }

    public Code getCode() {
        return code;
    }
}