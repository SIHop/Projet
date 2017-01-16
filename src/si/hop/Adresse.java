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
class Adresse {
    String adresse1;
    String adresse2;
    String ville;
    String departement;
    int departementNombre;
    String Pays;

    public Adresse(String adresse1, String adresse2, String ville, String departement, int departementNombre, String Pays) {
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.ville = ville;
        this.departement = departement;
        this.departementNombre = departementNombre;
        this.Pays = Pays;
    }

    public Adresse(String ville, String departement, int departementNombre, String Pays) {
        this.ville = ville;
        this.departement = departement;
        this.departementNombre = departementNombre;
        this.Pays = Pays;
    }
    
    
}
