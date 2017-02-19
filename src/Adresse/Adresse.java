package Adresse;

public class Adresse {

    private String pays;
    private String ville;
    private int codePostal;
    private String nomVoie;
    private int numeroVoie;
    private TypeVoie typeVoie;
    private String complement;

    public Adresse(String pays, String ville, int codePostal, String nomVoie, int numeroVoie, TypeVoie typeVoie, String complement) {
        this.pays = pays;
        this.ville = ville;
        this.codePostal = codePostal;
        this.nomVoie = nomVoie;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.complement = complement;
    }

    


}