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
public class Patient extends Personne{
    Date arriveePatient;
    
    public Patient(Date dateDeNaissance, Adresse adresseDeNaissance, Sexe SEXE, Identite identite, Adresse adresse) {
        super(dateDeNaissance, adresseDeNaissance, SEXE, identite, adresse);
    }
    
    
}
