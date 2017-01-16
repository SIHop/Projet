/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si.hop;

/**
 *
 * @author Quentin
 */
public class Personne {
    Date dateDeNaissance;
    Adresse adresseDeNaissance;
    Sexe SEXE;
    Identite identite;
    Adresse adresse;

    public Personne(Date dateDeNaissance, Adresse adresseDeNaissance, Sexe SEXE, Identite identite, Adresse adresse) {
        this.dateDeNaissance = dateDeNaissance;
        this.adresseDeNaissance = adresseDeNaissance;
        this.SEXE = SEXE;
        this.identite = identite;
        this.adresse = adresse;
    }
    
    
}
