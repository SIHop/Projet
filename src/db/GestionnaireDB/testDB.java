/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.GestionnaireDB;

import java.util.ArrayList;
import java.util.Arrays;
import nf.DPI.DM.Code;
import nf.DPI.DMA.DMA;
import nf.DPI.DMA.Sejour;

/**
 *
 * @author Loïc
 */
public class testDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String test = "bla;blabla; toto  ; tata";
        String annalyset = "1;2;3;4";
        ArrayList<String> resultats = new ArrayList<>(Arrays.asList(test.split("\\s*;\\s*")));
        ArrayList<String> annalyse = new ArrayList<>(Arrays.asList(annalyset.split("\\s*;\\s*")));
        for(String s : resultats){
             System.out.println(s);
        }
        
        ArrayList<String[]> listeResultats = new ArrayList<>();
        for (int i = 0; i < annalyse.size(); i++) {
                String acte_resultat[] = {annalyse.get(i), resultats.get(i)};
                listeResultats.add(acte_resultat);
            }
        
        for(String[] s : listeResultats){
            System.out.println(s[0] + ":" + s[1]);
        }
//        
//        String date = "1993-08-23";
//        DateT d = new DateT(date);
//        
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        System.out.println(dateFormat.format(d.getC().getTime()));
    }

}
