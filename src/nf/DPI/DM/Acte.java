package nf.DPI.DM;

import db.GestionnaireDB.DAOFactory;

/**
 *
 * @author Deniz
 */
public class Acte {

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
    
    private int idActe;
    private int idFicheDeSoins;

    public Acte(int idFicheDeSoins, Code code, int coef, String observations, TypeActe typeActe) {
        this.code = code;
        this.coef = coef;
        if (!observations.isEmpty()) {
            this.observations = observations;
        } else {
            this.observations = "Aucune observations";
        }
        this.typeActe = typeActe;
        this.idFicheDeSoins = idFicheDeSoins;
        this.idActe = DAOFactory.getActeDAO().getMaxId()+1;
        this.sauvegarder();
    }
    /**
     * Permet de sauvegarder les actes 
     */
    public void sauvegarder(){
        DAOFactory.getActeDAO().create(this);
    }
    
    //constructeur utilise par la db
    public Acte(int idActe, int idFicheDeSoins, Code code, int coef, String observations, TypeActe typeActe) {
        this.code = code;
        this.coef = coef;
        if (!observations.isEmpty()) {
            this.observations = observations;
        } else {
            this.observations = "Aucune observations";
        }
        this.typeActe = typeActe;
        this.idFicheDeSoins = idFicheDeSoins;
        this.idActe = idActe;
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
/**
 * affiche le code, le type, le coeficient et le coût de l'acte 
 * @return 
 */
    @Override
    public String toString() {
        return "" + code.name() + " - " + typeActe.toString() + ", Coefficient : " + coef + ", Coût : " + calculerCout();
    }

    public int getCout() {
        return cout;

    }

    /**
     * @return the idActe
     */
    public int getIdActe() {
        return idActe;
    }

    /**
     * @return the idFicheDeSoins
     */
    public int getIdFicheDeSoins() {
        return idFicheDeSoins;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * @param coef the coef to set
     */
    public void setCoef(int coef) {
        this.coef = coef;
    }

    /**
     * @param observations the observations to set
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    /**
     * @param typeActe the typeActe to set
     */
    public void setTypeActe(TypeActe typeActe) {
        this.typeActe = typeActe;
    }
}
