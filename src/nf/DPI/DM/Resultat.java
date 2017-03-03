/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

import java.util.ArrayList;

/**
 *
 * @author quentin
 */
public class Resultat {

    private final ArrayList<String[]> listeResultats;

    public Resultat(ArrayList<Acte> actes, ArrayList<String> resultats) {
        listeResultats = new ArrayList<>();
        if (actes.size() == resultats.size()) {
            for (int i = 0; i < actes.size(); i++) {
                String acte_resultat[] = {actes.get(i).getCode().getLibelle(), resultats.get(i)};
                listeResultats.add(acte_resultat);
            }

        }
    }

    @Override
    public String toString() {
        return "Resultat{" + "listeResultats=" + listeResultats + '}';
    }

}
