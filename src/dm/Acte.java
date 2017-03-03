package dm;

/**
 *
 * @author Deniz
 */
class Acte {
    private int cout;
    
    /**
     * code de l'acte:
     * <br>
     * Chaque acte médical à un code bien spécifique à l'opération effectué sur
     * le patient.
     */
    private Code code;
    /**
     * coefficient de l'acte:
     * <br>
     * Chaque acte médical à un coefficient spécifique.
     */
    private int coef;
    /**
     * observation:
     * <br>
     * Chaque acte médical peut être suivie ou non d'une observation.
     * <br><br>
     * si celui ci est vide, le texte sera :
     * <br>
     * "Aucune observation"
     */
    private String observations;
    /**
     * Type de l'acte:
     * <br>
     * Chaque acte médical a un type :
     * <br>
     * "Thérapeutique" ou "Diagnostique"
     */
    private TypeActe typeActe;

    public Acte(Code code, int coef, String observations, TypeActe typeActe) {
        this.code = code;
        this.coef = coef;
        if (!observations.isEmpty()) {
            this.observations = observations;
        } else {
            this.observations = "Aucune observations";
        }
        this.typeActe = typeActe;
    }

    public Code getCode() {
        return code;
    }

    public int getCoef() {
        return coef;
    }

    public String getObservations() {
        return observations;
    }

    public TypeActe getTypeActe() {
        return typeActe;
    }
    
    public double calculerCout() {
        return code.calculerCout(coef);       
    }
    
    public String toString() {
        return "" + code.name() + " - " + typeActe.toString() + ", Coefficient : " + coef + ", Coût : " + calculerCout();
    }

    public int getCout() {
        return cout;
    }

}