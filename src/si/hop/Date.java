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
public class Date {
    int jour;
    int mois;
    int annee;

    int heure;
    int minute;
    
    String jourSemaine;

    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public Date(int jour, int mois, int annee, String jourSemaine) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.jourSemaine = jourSemaine;
    }

    public Date(int jour, int mois, int annee, int heure, int minute) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
    }

    public Date(int jour, int mois, int annee, int heure, int minute, String jourSemaine) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
        this.jourSemaine = jourSemaine;
    }
    
    
    
}
