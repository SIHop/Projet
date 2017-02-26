package Adresse;

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

}