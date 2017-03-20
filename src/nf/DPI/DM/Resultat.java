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

    /**
     * constructeur permettant de créer un liste de résultat contenant les analyses et les résultats pour chacune d'entre d'elles 
     * @param idPrescription
     * @param idFicheDeSoins
     * @param annalyse
     * @param resultats 
     */
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
        DAOFactory.getResultatDAO().create(this);
    }

    //constructeur utilise par la db
    public Resultat(int idResultat, int idPrescription, int idFicheDeSoins, ArrayList<String> annalyse, ArrayList<String> resultats) {
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
/**
 * permet d'afficher la liste des résultats pour chaque analyse
 * @return 
 */
    @Override
    public String toString() {

        String retour = "Resultat   ";
        for(String[] s : listeResultats){
            retour += " -   Examen : " + s[0] + "  -   Resultat : " + s[1];

        }
        retour  += "|   idResultat=" + idResultat + "|  idPrescription=" + idPrescription ;
        return retour;
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

    /**
     * @param idPrescription the idPrescription to set
     */
    public void setIdPrescription(int idPrescription) {
        this.idPrescription = idPrescription;
    }

}
