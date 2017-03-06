package nf.Adresse;

public class Adresse {

  private String pays;
  private String ville;
  private int codePostal;
  private String nomVoie;
  private int numeroVoie;
  private String typeVoie;
  private String complement;

    public Adresse(String pays, String ville, int codePostal, String nomVoie, int numeroVoie, String typeVoie, String complement) {
        this.pays = pays;
        this.ville = ville;
        this.codePostal = codePostal;
        this.nomVoie = nomVoie;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.complement = complement;
    }

    
  

    /**
     * @return the pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @return the codePostal
     */
    public int getCodePostal() {
        return codePostal;
    }

    /**
     * @return the nomVoie
     */
    public String getNomVoie() {
        return nomVoie;
    }

    /**
     * @return the numeroVoie
     */
    public int getNumeroVoie() {
        return numeroVoie;
    }

    /**
     * @return the typeVoie
     */
    public String getTypeVoie() {
        return typeVoie;
    }

    /**
     * @return the complement
     */
    public String getComplement() {
        return complement;
    }
    
  @Override
    public String toString(){
        return this.pays + " : " + this.numeroVoie + " " + this.typeVoie + " " + this.nomVoie + " " + this.complement + " " + this.codePostal + " " + this.ville;
    }

    /**
     * @param pays the pays to set
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * @param nomVoie the nomVoie to set
     */
    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    /**
     * @param numeroVoie the numeroVoie to set
     */
    public void setNumeroVoie(int numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    /**
     * @param typeVoie the typeVoie to set
     */
    public void setTypeVoie(String typeVoie) {
        this.typeVoie = typeVoie;
    }

    /**
     * @param complement the complement to set
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

}