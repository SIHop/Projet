/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import db.GestionnaireDB.DAOFactory;
import java.util.ArrayList;

/**
 *
 * @author quentin
 */
public class Resultat {

    private final ArrayList<String[]> listeResultats;
    private int idResultat;
    private int idFicheDeSoins;
    private int idPrescription;

    public Resultat(int idPrescription, int idFicheDeSoins, ArrayList<String> annalyse, ArrayList<String> resultats) {
        listeResultats = new ArrayList<>();
        if (annalyse.size() == resultats.size()) {
            for (int i = 0; i < annalyse.size(); i++) {
                String acte_resultat[] = {annalyse.get(i), resultats.get(i)};
                listeResultats.add(acte_resultat);
            }

        }
        this.idFicheDeSoins = idFicheDeSoins;
        this.idPrescription = idPrescription;
        this.idResultat = DAOFactory.getResultatDAO().getMaxId() + 1;
    }
    
    //constructeur utilise par la db
    public Resultat(int idResultat,int idPrescription, int idFicheDeSoins, ArrayList<String> annalyse, ArrayList<String> resultats) {
        listeResultats = new ArrayList<>();
        if (annalyse.size() == resultats.size()) {
            for (int i = 0; i < annalyse.size(); i++) {
                String acte_resultat[] = {annalyse.get(i), resultats.get(i)};
                listeResultats.add(acte_resultat);
            }

        }
        this.idFicheDeSoins = idFicheDeSoins;
        this.idPrescription = idPrescription;
        this.idResultat = idResultat;
    }

    @Override
    public String toString() {
        return "Resultat{" + "listeResultats=" + getListeResultats() + '}';
    }

    /**
     * @return the listeResultats
     */
    public ArrayList<String[]> getListeResultats() {
        return listeResultats;
    }

    /**
     * @return the idresultat
     */
    public int getIdResultat() {
        return idResultat;
    }

    /**
     * @return the idFicheDeSoins
     */
    public int getIdFicheDeSoins() {
        return idFicheDeSoins;
    }

    /**
     * @return the idPrescription
     */
    public int getIdPrescription() {
        return idPrescription;
    }

}
